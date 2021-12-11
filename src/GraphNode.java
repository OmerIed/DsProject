public class GraphNode {
    private final int key;
    private AdjacencyListNode<GraphEdge> outFirst;
    private AdjacencyListNode<GraphEdge> outLast;
    private AdjacencyListNode<GraphEdge> inFirst;
    private AdjacencyListNode<GraphEdge> inLast;
    private GraphNode next;
    private GraphNode prev;
    public GraphNode(int key)
    {
        this.key = key;
        outFirst = new AdjacencyListNode<GraphEdge>();
        outLast = outFirst;
        inFirst = new AdjacencyListNode<GraphEdge>();
        inLast = inFirst;
        this.next = null;
        this.prev = null;
    }
    public int getKey(){
        return this.key;
    }
    public int getOutDegree(){
        return outFirst.getLength();
    }
    public int getInDegree(){
        return inFirst.getLength();
    }
    public AdjacencyListNode<GraphEdge> getOutEdges()
    {
        return outFirst;
    }
    public AdjacencyListNode<GraphEdge> getInEdges()
    {
        return inFirst;
    }

    public void setNext(GraphNode next) {
        this.next = next;
    }

    public GraphNode getNext() {
        return next;
    }

    public GraphNode getPrev() {
        return prev;
    }

    public void setPrev(GraphNode prev) {
        this.prev = prev;
    }
}
