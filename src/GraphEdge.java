public class GraphEdge {
    private GraphNode from;
    private GraphNode to;
    private GraphEdge fromNext;
    private GraphEdge toNext;
    private GraphEdge fromPrev;
    private GraphEdge toPrev;
    GraphEdge(GraphNode from, GraphNode to)
    {
        this.from = from;
        this.to = to;
        to.getLast
        this.fromNext = null;
        this.fromPrev = null;
        this.toNext = null;
    }

    public

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }
}
