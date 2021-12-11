public class DynamicGraph {
    AdjacencyListNode<GraphEdge> edgeList;
    AdjacencyListNode<GraphNode> vertexList;

    public DynamicGraph()
    {
        AdjacencyListNode<GraphEdge> edgeList = new AdjacencyListNode<>();
        AdjacencyListNode<GraphNode> vertexList = new AdjacencyListNode<>();

    }
    public GraphNode insertNode(int nodeKey){
        GraphNode node = new GraphNode(nodeKey);
        vertexList.setNext(node);
        return vertexList.getCurrent();

    }
    public void deleteNode(GraphNode node){
        node.getInDegree() !=

    }
    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        e

    }
    public void deleteEdge(GraphEdge edge) {

    }
    public  RootedTree scc(){

    }
    public RootedTree bfs(GraphNode source)
}
