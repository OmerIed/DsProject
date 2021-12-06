import java.util.ArrayList;

public class GraphNode {
    private int key;
    private ArrayList<GraphNode> outEdges;
    private ArrayList<GraphNode> inEdges;
    public GraphNode(int key)
    {
        this.key = key;
        outEdges = new ArrayList<GraphNode>();
        inEdges = new ArrayList<GraphNode>();
    }
    public int getKey(){
        return this.key;
    }
    public int getOutDegree(){
        return outEdges.size();
    }
    public int getInDegree(){
        return inEdges.size();
    }
    public ArrayList<GraphNode> getOutEdges()
    {
        return outEdges;
    }
    public ArrayList<GraphNode> getInEdges()
    {
        return inEdges;
    }
}
