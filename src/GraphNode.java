public class GraphNode {
    private final int key;
    private AdjacencyListNode<GraphEdge> outFirst;
    private AdjacencyListNode<GraphEdge> outLast;
    private AdjacencyListNode<GraphEdge> inFirst;
    private AdjacencyListNode<GraphEdge> inLast;
    public GraphNode(int key)
    {
        this.key = key;
        outFirst = new AdjacencyListNode<GraphEdge>();
        outLast = outFirst;
        inFirst = new AdjacencyListNode<GraphEdge>();
        inLast = inFirst;
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
}
