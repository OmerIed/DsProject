public class GraphNode {
    private final int key;
    private AdjacencyListNode outFirst;
    private AdjacencyListNode outLast;
    private AdjacencyListNode inFirst;
    private AdjacencyListNode inLast;
    public GraphNode(int key)
    {
        this.key = key;
        outFirst = new AdjacencyListNode();
        outLast = outFirst;
        inFirst = new AdjacencyListNode();
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
    public AdjacencyListNode getOutEdges()
    {
        return outFirst;
    }
    public AdjacencyListNode getInEdges()
    {
        return inFirst;
    }
}
