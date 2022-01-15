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
            lastNode.setNext(node);
            lastNode = node;
        }
        return lastNode;
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
            } //else {
//                edge.getTo().setInFirst(null);
//                edge.getTo().setInLast(null);
//            }
        }
        else if (edge == inlast) {
            if (edge.getToPrev() != null) {
                edge.getTo().setInLast(edge.getToPrev());
                edge.getToPrev().setToNext(null);
                if (edge.getTo().getInLast().getToPrev() == null){
                    edge.getTo().setInFirst(edge.getTo().getInLast());
                }
           }
            //else {
//                edge.getTo().setInLast(null);
//                edge.getTo().setInFirst(null);
//            }
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
            } //else {
//                edge.getFrom().setOutFirst(null);
//                edge.getFrom().setOutLast(null);
//            }
        }
        else if (edge == outlast) {
            if (edge.getFromPrev() != null) {
                edge.getFrom().setOutLast(edge.getFromPrev());
                edge.getFromPrev().setFromNext(null);
                if (edge.getFrom().getOutLast().getFromPrev() == null){
                    edge.getFrom().setOutFirst(edge.getFrom().getOutLast());
                }
            }// else {
//                edge.getFrom().setOutLast(null);
//                edge.getFrom().setOutFirst(null);
//            }
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
        //DynamicGraph dg = this.createCopy();
        cleanTree(this.firstNode);
        GraphNode u_node = this.firstNode;
        AdjacencyListNode<GraphNode> u_start = new AdjacencyListNode<>(u_node);
        AdjacencyListNode<GraphNode> u = u_start;
        u_node = u_node.getNext();
        while (u_node!=null)
        {
            u.setNext(u_node);
            u = u.getNext();
            u_node = u_node.getNext();
        }
        u_start = dfs(u_start);
        //u = u_start;
//        while (u != null) {
//            if (u.getNext() != null)
//                u.getCurrent().setNext(u.getNext().getCurrent());
//            else
//                u.getCurrent().setNext(null);
//            u = u.getNext();
//        }
        transposeGraph(u_start);
        u = dfs(u_start);
        transposeGraph(u_start);
        GraphNode node = this.firstNode;
        //GraphNode gPi = node.copyEdgesAndKey();
        //GraphNode newNode = gPi;
        //GraphNode prevNode = null;
        while (node != null) {
            node.setLeftChild(null);
            node.setRightSibling(null);
            /*newNode.setOutFirst(null);
            newNode.setOutLast(null);
            newNode.setInLast(null);
            newNode.setInFirst(null);
            newNode.setPrev(prevNode);
            prevNode = newNode;
            node = node.getNext();
            if(node != null)
                newNode = node.copyEdgesAndKey();*/
            node = node.getNext();

        }
        //node = u.getCurrent();
        //newNode = gPi;
        return getRootedTree(u_start, tree);
    }
    private RootedTree getRootedTree(AdjacencyListNode<GraphNode> node, RootedTree tree) {
        while (node != null) {
            GraphNode parent;
            if (node.getCurrent().getParent() == null )
                parent = tree.getRoot();
            else
                parent = node.getCurrent().getParent();
            if (parent.getLastChild() != null) {
                parent.getLastChild().setRightSibling(node.getCurrent());
                parent.setLastChild(node.getCurrent());
            }
            else
            {
                parent.setLastChild(node.getCurrent());
                parent.setLeftChild(node.getCurrent());
            }
//            parent.setLeftChild(node);
            node = node.getNext();
        }
        return tree;
    }
    public DynamicGraph createCopy() {
        GraphNode node = firstNode;
        DynamicGraph dg = new DynamicGraph();
        dg.firstNode = firstNode.copyEdgesAndKey();
        GraphNode new_node = dg.firstNode;
        while (node != null) {
            node = node.getNext();
            if (node != null) {
                new_node.setNext(node.copyEdgesAndKey());
                new_node = new_node.getNext();
            }
        }
        dg.lastNode = new_node;
        return dg;
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

                /*GraphEdge toNext = edge.getToNext();
                GraphEdge toPrev = edge.getToPrev();
                if(edge.getToNext() != null && edge.getToNext().getToPrev() != edge)
                    edge.getToNext().setToPrev(edge);
                if(toNext != null && toNext.getTo() != node)
                {
                    edge.setToNext(edge.getFromNext());
                    edge.setFromNext(toNext);
                    if(toNext.getFromPrev() != edge)
                        toNext.setFromPrev(edge);
                }
                if(toPrev != null && toPrev.getTo() != node)
                {
                    edge.setToPrev(edge.getFromPrev());
                    if(edge.getToPrev() != null && edge.getToPrev().getToNext() != edge)
                        edge.getToPrev().setToNext(edge);
                    edge.setFromPrev(toPrev);
                    if(toPrev.getFromNext() != edge)
                        toPrev.setFromNext(edge);
                }*/

                edge = edge.getFromNext();
            }
            /*edge = node.getOutFirst();
            while (edge != null)
            {
                if(edge.getFrom() != node)
                {
                    GraphNode tmp = edge.getTo();
                    edge.setTo(edge.getFrom());
                    edge.setFrom(tmp);
                }

                GraphEdge fromNext = edge.getFromNext();
                GraphEdge fromPrev = edge.getFromPrev();

                if(fromNext != null && fromNext.getTo() != node)
                {
                    edge.setFromNext(edge.getToNext());
                    edge.setToNext(fromNext);
                }
                if(fromPrev != null && fromPrev.getTo() != node)
                {
                    edge.setFromPrev(edge.getToPrev());
                    edge.setToPrev(fromPrev);
                }
                edge = edge.getFromNext();
            }*/
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

    public AdjacencyListNode<GraphNode> dfs(AdjacencyListNode<GraphNode> g) {
        // u_start is the beginning of the new Graph in the order of the discovery.
        // GraphNode u_start = g.copyEdgesAndKey();
        AdjacencyListNode<GraphNode> graph_node = g;
        AdjacencyListNode<GraphNode> newOrderNodes = new AdjacencyListNode<GraphNode>();
        AdjacencyListNode<GraphNode> curNode = newOrderNodes;
        //newOrderNodes.setCurrent(graph_node);
        graph_node.getCurrent().setColor("w");
        graph_node.getCurrent().setParent(null);
        graph_node = graph_node.getNext();
        while (graph_node != null) {
            graph_node.getCurrent().setColor("w");
            graph_node.getCurrent().setParent(null);
            graph_node = graph_node.getNext();
        }
        graph_node = g;
        //GraphNode u = u_start;
        while (graph_node != null) {
            if (graph_node.getCurrent().getColor() == "w") {
//                if (graph_node != newOrderNodes.getCurrent()) {
//                    //curNode.setNext(graph_node);
//                    curNode.setPrevious(graph_node);
//                    curNode = curNode.getPrevious();
//                    //curNode = curNode.getNext();
//                }
                curNode = dfs_visit(graph_node.getCurrent(), curNode);
            }
            graph_node = graph_node.getNext();
        }
        return curNode;
    }

    private AdjacencyListNode<GraphNode> dfs_visit(GraphNode graph_node, AdjacencyListNode<GraphNode> curNode) {
        graph_node.setColor("g");
        GraphEdge edge = graph_node.getOutFirst();
        while (edge != null) {
            GraphNode nextNode = edge.getTo();
            if (nextNode.getColor() == "w") {
                //GraphNode u_next = nextNode.copyEdgesAndKey();
                //u_next.setParent(u);
                //curNode.setNext(nextNode);
                nextNode.setParent(edge.getFrom());
                //curNode = curNode.getNext();
                //u.setNext(u_next);
                //u = u_next;
                curNode = dfs_visit(nextNode, curNode);
            }
            edge = edge.getFromNext();
        }
        graph_node.setColor("b");
        if(curNode.isEmpty()){
            curNode.setCurrent(graph_node);
        }
        else {
            curNode.setPrevious(graph_node);
            curNode = curNode.getPrevious();
        }
        return curNode;
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
                if (child.getColor() == "w"){
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
//    public RootedTree bfs(GraphNode source) {
//
//        AdjacencyListNode<GraphNode> qtail = bfs_init(source);
//        AdjacencyListNode<GraphNode> cur_node = qtail;
//        AdjacencyListNode<GraphNode> disc_order_start = new AdjacencyListNode<>();
//        while (cur_node != null && !cur_node.isEmpty()){
//            GraphNode graphNode = cur_node.getCurrent();
//            if(qtail.getPrevious() != null)
//            {
//                qtail = qtail.getPrevious();
//                qtail.setNext(null);
//            }
//            else
//            {
//                qtail = new AdjacencyListNode<>();
//                cur_node = qtail;
//            }
//            GraphEdge edge = graphNode.getOutFirst();
//            while (edge!=null){
//                GraphNode next = edge.getTo();
//                if(next.getColor() == "w"){
//                    if(disc_order_start.isEmpty())
//                    {
//                        disc_order_start.setCurrent(next);
//                    }
//                    else
//                    {
//                        disc_order_start.setPrevious(next);
//                        disc_order_start = disc_order_start.getPrevious();
//                    }
//
//
//                    next.setColor("g");
//                    next.setParent(graphNode);
//                    if(graphNode.getLeftChild() ==null)
//                    {
//                        graphNode.setLeftChild(next);
//                        graphNode.setLastChild(next);
//                    }
//                    else
//                    {
//                        graphNode.getLastChild().setRightSibling(next);
//                    }
//                    if(cur_node.isEmpty())
//                    {
//                        cur_node.setCurrent(next);
//                    }
//                    else
//                    {
//                        cur_node.setPrevious(next);
//                        cur_node = cur_node.getPrevious();
//                    }
//                }
//                edge = edge.getFromNext();
//            }
//            graphNode.setColor("b");
//        }
//        AdjacencyListNode<GraphNode> node = disc_order_start;
//        RootedTree tree = new RootedTree();
//        tree.setRoot(source);
////        while (node != null && !node.isEmpty()) {
////            GraphNode parent;
////            if (node.getCurrent().getParent() != null ) {
////                parent = node.getCurrent().getParent();
////                if (parent.getLastChild() != null) {
////                    parent.getLastChild().setRightSibling(node.getCurrent());
////                    parent.setLastChild(node.getCurrent());
////                }
////                else
////                {
////                    parent.setLastChild(node.getCurrent());
////                    parent.setLeftChild(node.getCurrent());
////                }
////            }
////            node = node.getNext();
////        }
//        return tree;
//    }



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
