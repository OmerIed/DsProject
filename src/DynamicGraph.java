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


    public  RootedTree scc(){

    }
    public RootedTree bfs(GraphNode source)
}
