public class DynamicGraph {
    GraphNode firstNode;
    GraphNode lastNode;
    AdjacencyListNode<GraphEdge> firstEdge;
    AdjacencyListNode<GraphEdge> lastEdge;

    public DynamicGraph() {
        firstNode = null;
        lastNode = null;
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode node = new GraphNode(nodeKey);
        if (firstNode == null) {
            firstNode = node;
            lastNode = firstNode;
        } else {
            firstNode.setPrev(node);
            firstNode = node;
        }
        return firstNode;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree() == 0 && node.getOutDegree() == 0) {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            node.setNext(null);
            node.setPrev(null);
        }


    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        GraphEdge edge = new GraphEdge(from, to);
        if (lastEdge == null) {
            lastEdge = new AdjacencyListNode<>(edge);
            firstEdge = lastEdge;
        } else {
            lastEdge.setNext(edge);
            lastEdge = lastEdge.getNext();
        }
        return edge;
    }

    public void deleteEdge(GraphEdge edge) {
        GraphEdge infirst = edge.getTo().getInFirst();
        GraphEdge inlast = edge.getTo().getInLast();
        GraphEdge outlast = edge.getFrom().getOutLast();
        GraphEdge outfirst = edge.getFrom().getOutFirst();
        if(edge.getTo() == null && edge.getFrom() ==null)
            return;
        else if (edge == infirst && edge == inlast) {
            edge.getTo().setInFirst(null);
            edge.getTo().setInLast(null);
        } else if (edge == infirst){
            if (edge.getToNext() != null) {
                edge.getTo().setInFirst(edge.getToNext());
                edge.getToNext().setToPrev(null);
                if (edge.getTo().getInFirst().getToNext() == null){
                    edge.getTo().setInLast(edge.getTo().getInFirst());
                }
            }
        }
        else if (edge == inlast) {
            if (edge.getToPrev() != null) {
                edge.getTo().setInLast(edge.getToPrev());
                edge.getToPrev().setToNext(null);
                if (edge.getTo().getInLast().getToPrev() == null){
                    edge.getTo().setInFirst(edge.getTo().getInLast());
                }
           }

        } else {
            edge.getToPrev().setToNext(edge.getToNext());
            edge.getToNext().setToPrev(edge.getToPrev());
            if (edge.getToNext().getToPrev() == null){
                edge.getTo().setInFirst(edge.getToNext());
            }
            if (edge.getToPrev().getToNext() == null){
                edge.getTo().setInLast(edge.getToPrev());
            }
        }

        if (edge == outfirst && edge == outlast) {
            edge.getFrom().setOutFirst(null);
            edge.getFrom().setOutLast(null);
        } else if(edge == outfirst)
        {
            if (edge.getFromNext() != null) {
                edge.getFrom().setOutFirst(edge.getFromNext());
                edge.getFromNext().setFromPrev(null);
                if (edge.getFrom().getOutFirst().getFromNext() == null){
                    edge.getFrom().setOutLast(edge.getFrom().getOutFirst());
                }
            }
        }
        else if (edge == outlast) {
            if (edge.getFromPrev() != null) {
                edge.getFrom().setOutLast(edge.getFromPrev());
                edge.getFromPrev().setFromNext(null);
                if (edge.getFrom().getOutLast().getFromPrev() == null){
                    edge.getFrom().setOutFirst(edge.getFrom().getOutLast());
                }
            }
        } else {
            edge.getFromPrev().setFromNext(edge.getFromNext());
            edge.getFromNext().setFromPrev(edge.getFromPrev());
            if (edge.getFromNext().getFromPrev() == null){
                edge.getFrom().setOutFirst(edge.getFromNext());
            }
            if (edge.getFromPrev().getFromNext() == null){
                edge.getFrom().setOutLast(edge.getFromPrev());
            }
        }
        edge.setTo(null);
        edge.setToNext(null);
        edge.setFromPrev(null);
        edge.setFromNext(null);
        edge.setToPrev(null);
        edge.setFrom(null);
    }



    public RootedTree scc() {
        RootedTree tree = new RootedTree();
        tree.setRoot(new GraphNode(0));
        cleanTree(this.firstNode);

        AdjacencyListNode<GraphNode> u_start = dfsReturnOrder();

        transposeGraph(u_start);
        dfsWithOrder(u_start);
        transposeGraph(u_start);
        return getRootedTree(u_start, tree);
    }
    private RootedTree getRootedTree(AdjacencyListNode<GraphNode> node, RootedTree tree) {
        GraphNode parent = tree.getRoot();
        while (node != null) {
            if (node.getCurrent().getParent() == null ) {
                node.getCurrent().setParent(parent);
                if (parent.getLastChild() != null) {
                    parent.getLastChild().setRightSibling(node.getCurrent());
                    parent.setLastChild(node.getCurrent());
                } else {
                    parent.setLastChild(node.getCurrent());
                    parent.setLeftChild(node.getCurrent());
                }
            }
            node = node.getNext();
        }
        return tree;
    }


    public void transposeGraph(AdjacencyListNode<GraphNode> g) {
        AdjacencyListNode<GraphNode> node = g;
        while (node != null) {
            GraphEdge outFirst = node.getCurrent().getOutFirst();
            GraphEdge outLast = node.getCurrent().getOutLast();
            node.getCurrent().setOutFirst(node.getCurrent().getInFirst());
            node.getCurrent().setOutLast(node.getCurrent().getInLast());
            node.getCurrent().setInFirst(outFirst);
            node.getCurrent().setInLast(outLast);
            GraphEdge edge = node.getCurrent().getInFirst();
            while (edge != null) {
                GraphNode tmp = edge.getFrom();
                edge.setFrom(edge.getTo());
                edge.setTo(tmp);
                edge = edge.getFromNext();
            }

            node = node.getNext();
        }
        node = g;
        while (node != null) {
            GraphEdge edge = node.getCurrent().getInFirst();
            while (edge != null) {
                GraphEdge fromNext = edge.getFromNext();
                GraphEdge fromPrev = edge.getFromPrev();
                edge.setFromNext(edge.getToNext());
                edge.setFromPrev(edge.getToPrev());
                edge.setToNext(fromNext);
                edge.setToPrev(fromPrev);
                edge = edge.getToNext();
            }

            node = node.getNext();
        }
    }
    public AdjacencyListNode<GraphNode> dfsReturnOrder(){
        AdjacencyListNode<GraphNode> queue = new AdjacencyListNode<>();
        GraphNode g = this.firstNode;
        while (g!=null){
            g.setColor("w");
            g.setParent(null);
            g = g.getNext();
        }
        g = this.firstNode;
        while (g!=null){
            if(g.getColor().equals("w"))
            {
                queue = dfsReturnOrderVisit(queue, g);
            }
            g = g.getNext();
        }
        return queue;
    }
    public AdjacencyListNode<GraphNode> dfsReturnOrderVisit(AdjacencyListNode<GraphNode> queue, GraphNode g){
        g.setColor("g");
        GraphEdge edge = g.getOutFirst();
        while (edge != null) {
            GraphNode nextNode = edge.getTo();
            if (nextNode.getColor().equals("w")) {
                queue = dfsReturnOrderVisit(queue, nextNode);
            }
            edge = edge.getFromNext();
        }
        g.setColor("b");
        if(queue.isEmpty()){
            queue.setCurrent(g);
        }
        else {
            queue.setPrevious(g);
            queue = queue.getPrevious();
        }
        return queue;
    }
    public void dfsWithOrder(AdjacencyListNode<GraphNode> g) {
        AdjacencyListNode<GraphNode> graph_node = g;
        graph_node.getCurrent().setColor("w");
        graph_node.getCurrent().setParent(null);
        graph_node = graph_node.getNext();
        while (graph_node != null) {
            graph_node.getCurrent().setColor("w");
            graph_node.getCurrent().setParent(null);
            graph_node.getCurrent().setLeftChild(null);
            graph_node.getCurrent().setLastChild(null);
            graph_node = graph_node.getNext();
        }
        graph_node = g;
        while (graph_node != null) {
            if (graph_node.getCurrent().getColor() == "w") {
                dfs_visit(graph_node.getCurrent());
            }
            graph_node = graph_node.getNext();
        }

    }

    private void dfs_visit(GraphNode graph_node) {
        graph_node.setColor("g");
        GraphEdge edge = graph_node.getOutFirst();
        while (edge != null) {
            GraphNode nextNode = edge.getTo();
            if (nextNode.getColor().equals("w")) {
                dfs_visit(nextNode);
                nextNode.setParent(edge.getFrom());
                if (edge.getFrom().getLastChild() != null) {
                    edge.getFrom().getLastChild().setRightSibling(nextNode);
                    edge.getFrom().setLastChild(nextNode);
                }
                else
                {
                    edge.getFrom().setLastChild(nextNode);
                    edge.getFrom().setLeftChild(nextNode);
                }
            }
            edge = edge.getFromNext();
        }
        graph_node.setColor("b");
    }
    public void cleanTree(GraphNode node){
        while (node!=null)
        {
            node.setRightSibling(null);
            node.setLeftChild(null);
            node.setLastChild(null);
            node.setParent(null);
            node = node.getNext();
        }
    }
    public RootedTree bfs(GraphNode source)
    {
        AdjacencyListNode<GraphNode> head = bfs_init(source);
        AdjacencyListNode<GraphNode> tail = head;
        while(head != null){
            GraphNode parent = head.getCurrent();
            GraphEdge edge = parent.getOutFirst();
            while (edge!=null)
            {
                GraphNode child = edge.getTo();
                if (child.getColor().equals("w")){
                    child.setColor("g");
                    child.setParent(parent);
                    tail.setNext(child);
                    tail = tail.getNext();
                    if(parent.getLastChild() != null)
                    {
                        parent.getLastChild().setRightSibling(child);
                        parent.setLastChild(child);
                    }
                    else{
                        parent.setLeftChild(child);
                        parent.setLastChild(child);
                    }
                }

                edge = edge.getFromNext();
            }
            parent.setColor("b");
            head = head.getNext();
        }
        RootedTree tree = new RootedTree();
        tree.setRoot(source);
        return tree;
    }
    private AdjacencyListNode<GraphNode> bfs_init(GraphNode source) {
        GraphNode node = this.firstNode;
        while (node != null){
            node.setColor("w");
            node.setParent(null);
            node.setRightSibling(null);
            node.setLastChild(null);
            node.setLeftChild(null);
            node = node.getNext();
        }
        source.setParent(null);
        source.setColor("g");
        AdjacencyListNode<GraphNode> queue = new AdjacencyListNode<>(source);
        return queue;
    }

}
