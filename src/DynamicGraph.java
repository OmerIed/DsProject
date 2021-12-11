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
        if(node.getInDegree() == 0 && node.getOutDegree() == 0 ){
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

    }
    public GraphEdge insertEdge(GraphNode from, GraphNode to){


    }
    public void deleteEdge(GraphEdge edge) {


    }
    public  RootedTree scc(){

    }
    public RootedTree bfs(GraphNode source)
}
