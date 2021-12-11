import java.util.ArrayList;

public class GraphNode {
    private final int key;
    private AdjacencyListNode outEdges;
    private AdjacencyListNode inEdges;
    public GraphNode(int key)
    {
        this.key = key;
        outEdges = new AdjacencyListNode();
        inEdges = new AdjacencyListNode();
    }
    public int getKey(){
        return this.key;
    }
    public int getOutDegree(){
        return outEdges.getLength();
    }
    public int getInDegree(){
        return inEdges.getLength();
    }
    public AdjacencyListNode getOutEdges()
    {
        return outEdges;
    }
    public AdjacencyListNode getInEdges()
    {
        return inEdges;
    }
}
