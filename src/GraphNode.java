public class GraphNode {
    private final int key;
    private GraphEdge outFirst;
    private GraphEdge outLast;
    private GraphEdge inFirst;
    private GraphEdge inLast;
    private GraphNode next;
    private GraphNode prev;
    public GraphNode(int key)
    {
        this.key = key;
        outFirst = null;
        outLast = null;
        inFirst = null;
        inLast = null;
        this.next = null;
        this.prev = null;
    }
    public int getKey(){
        return this.key;
    }
    public int getOutDegree(){
        GraphEdge edge = outFirst;
        int count
        while (edge!=null)
        {

        }
        return outFirst.getLength();
    }
    public int getInDegree(){
        return inFirst.getLength();
    }
    public GraphEdge getOutEdges()
    {
        return outFirst;
    }
    public GraphEdge getInEdges()
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

    public GraphEdge getInLast() {
        return inLast;
    }

    public GraphEdge getOutLast() {
        return outLast;
    }

    public void setInLast(GraphEdge inLast) {
        this.inLast = inLast;
    }

    public void setOutLast(GraphEdge outLast) {
        this.outLast = outLast;
    }

    public GraphEdge getInFirst() {
        return inFirst;
    }

    public GraphEdge getOutFirst() {
        return outFirst;
    }

    public void setInFirst(GraphEdge inFirst) {
        this.inFirst = inFirst;
    }

    public void setOutFirst(GraphEdge outFirst) {
        this.outFirst = outFirst;
    }
}
