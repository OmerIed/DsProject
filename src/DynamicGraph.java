public class DynamicGraph {
    GraphNode firstNode;
    GraphNode lastNode;
    public DynamicGraph()
    {
        firstNode = null;
        lastNode = null;
    }
    public GraphNode insertNode(int nodeKey){
        GraphNode node = new GraphNode(nodeKey);
        if(firstNode == null)
        {
            firstNode = node;
            lastNode = firstNode;
        }
        else
        {
            lastNode.setNext(node);
            lastNode = node;
        }
        return  lastNode;
    }
    public void deleteNode(GraphNode node){
        if(node.getInDegree() == 0 && node.getOutDegree() == 0 ){
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            node.setNext(null);
            node.setPrev(null);
        }

    }
    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge edge = new GraphEdge(from, to);
        return edge;
    }
    public void deleteEdge(GraphEdge edge) {
        if (edge == edge.getTo().getInFirst()) {
            if (edge.getToNext() != null) {
                edge.getTo().setInFirst(edge.getToNext());
                edge.getToNext().setToPrev(null);
            } else {
                edge.getTo().setInFirst(null);
                edge.getTo().setInLast(null);
            }
        } else if (edge == edge.getTo().getInLast()) {
            if (edge.getToPrev() != null) {
                edge.getTo().setInLast(edge.getToPrev());
                edge.getToPrev().setToNext(null);
            } else {
                edge.getTo().setInLast(null);
                edge.getTo().setInFirst(null);
            }
        } else {
            edge.getToPrev().setToNext(edge.getToNext());
            edge.getToNext().setToPrev(edge.getToPrev());
        }

        if (edge == edge.getFrom().getOutFirst()) {
            if (edge.getFromNext() != null) {
                edge.getFrom().setOutFirst(edge.getFromNext());
                edge.getFromNext().setFromPrev(null);
            } else {
                edge.getFrom().setOutFirst(null);
                edge.getFrom().setOutLast(null);
            }
        } else if (edge == edge.getFrom().getOutLast()) {
            if (edge.getFromPrev() != null) {
                edge.getFrom().setOutLast(edge.getFromPrev());
                edge.getFromPrev().setFromNext(null);
            } else {
                edge.getFrom().setOutLast(null);
                edge.getFrom().setOutFirst(null);
            }
        } else {
            edge.getFromPrev().setFromNext(edge.getFromNext());
            edge.getFromNext().setFromPrev(edge.getFromPrev());
        }
        edge.setTo(null);
        edge.setToNext(null);
        edge.setFromPrev(null);
        edge.setFromNext(null);
        edge.setFromPrev(null);
        edge.setFrom(null);
    }


    public RootedTree scc(){
        RootedTree tree = new RootedTree();
        tree.setRoot(new GraphNode(0));
        DynamicGraph dg = this.createCopy();
        AdjacencyListNode<GraphNode> u_start = dfs(this.firstNode);
        AdjacencyListNode<GraphNode> u = u_start;
        while (u != null)
        {
            if(u.getNext()!=null)
                u.getCurrent().setNext(u.getNext().getCurrent());
            else
                u.getCurrent().setNext(null);
            u = u.getNext();
        }
        transposeGraph(u_start.getCurrent());
        u = dfs(u_start.getCurrent());
        GraphNode node = u.getCurrent();
        while (node != null)
        {
            node.setOutFirst(null);
            node.setOutLast(null);
            node.setInLast(null);
            node.setInFirst(null);
            node = node.getNext();
        }
        node = u.getCurrent();
        while (node != null)
        {
            GraphNode parent;
            if(node.getParent() == null)
                parent = tree.getRoot();
            else
                parent = node.getParent();
            GraphEdge edge = new GraphEdge(parent,node);
            //GraphEdge lastOut = parent.getOutLast();
            //edge.setFromPrev(lastOut);
            //if(lastOut != null && lastOut != edge)
                //lastOut.setFromNext(edge);
            //parent.setOutLast(edge);
           // GraphEdge lastIn = node.getInLast();
            //edge.setToPrev(lastIn);
            //if(lastIn != null)
                //lastIn.setToNext(edge);
            //node.setInLast(edge);
            node = node.getNext();
        }
        this.firstNode = dg.firstNode;
        this.lastNode = dg.lastNode;
        return tree;
    }
    public DynamicGraph createCopy()
    {
        GraphNode node = firstNode;
        DynamicGraph dg = new DynamicGraph();
        dg.firstNode = firstNode.copyEdgesAndKey();
        GraphNode new_node = dg.firstNode;
        while (node != null)
        {
            node = node.getNext();
            if(node != null)
            {
                new_node.setNext(node.copyEdgesAndKey());
                new_node = new_node.getNext();
            }
        }
        dg.lastNode = new_node;
        return dg;
    }
    public void transposeGraph(GraphNode g)
    {
        // this function doesnt fully transposes the graph; it only makes the in edges into out edges.
        GraphNode node = g;
        while (node != null)
        {
            node.setOutFirst(node.getInFirst());
            node.setOutLast(node.getInLast());
            GraphEdge edge = node.getOutFirst();
            while (edge != null)
            {
                GraphNode tmp = edge.getFrom();
                edge.setFrom(edge.getTo());
                edge.setTo(tmp);
                edge.setFromNext(edge.getToNext());
                edge = edge.getFromNext();
            }

            node = node.getNext();
        }
    }
    public AdjacencyListNode<GraphNode> dfs(GraphNode g)
    {
        // u_start is the beginning of the new Graph in the order of the discovery.
        // GraphNode u_start = g.copyEdgesAndKey();
        GraphNode graph_node = g;
        AdjacencyListNode<GraphNode> newOrderNodes = new AdjacencyListNode<GraphNode>();
        AdjacencyListNode<GraphNode> curNode = newOrderNodes;
        newOrderNodes.setCurrent(graph_node);
        graph_node.setColor("w");
        graph_node.setParent(null);
        graph_node = graph_node.getNext();
        while(graph_node != null)
        {
            graph_node.setColor("w");
            graph_node.setParent(null);
            graph_node = graph_node.getNext();
        }
        graph_node = g;
        //GraphNode u = u_start;
        while(graph_node != null)
        {
            if(graph_node.getColor() == "w"){
                if(graph_node != newOrderNodes.getCurrent())
                {
                    //GraphNode u_next = graph_node.copyEdgesAndKey();
                    curNode.setNext(graph_node);
                    curNode = curNode.getNext();
                    // u.setNext(u_next);
                    // u = u_next;
                }
                curNode = dfs_visit(graph_node, curNode);
            }
            graph_node = graph_node.getNext();
        }
        return newOrderNodes;
    }

    private AdjacencyListNode<GraphNode> dfs_visit(GraphNode graph_node, AdjacencyListNode<GraphNode> curNode)
    {
        graph_node.setColor("g");
        GraphEdge edge = graph_node.getOutFirst();
        while (edge != null)
        {
            GraphNode nextNode = edge.getTo();
            if(nextNode.getColor() == "w")
            {
                //GraphNode u_next = nextNode.copyEdgesAndKey();
                //u_next.setParent(u);
                curNode.setNext(nextNode);
                nextNode.setParent(curNode.getCurrent());
                curNode = curNode.getNext();
                //u.setNext(u_next);
                //u = u_next;
                curNode = dfs_visit(nextNode, curNode);
            }
            edge = edge.getFromNext();
        }
        graph_node.setColor("b");
        return curNode;
    }
    public RootedTree bfs(GraphNode source)
    {
        return null;
    }
    //private bfs_init(GraphNode source,)

}
