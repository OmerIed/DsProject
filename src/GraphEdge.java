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
        setFromPrev(from.getInLast());
        setFromNext(null);
        setToNext(null);
        setToPrev(to.getOutLast());
        to.setInLast(this);
        from.setOutLast(this);
    }

    public void setFromNext(GraphEdge fromNext) {
        this.fromNext = fromNext;
    }

    public GraphEdge getFromNext() {
        return fromNext;
    }

    public GraphEdge getFromPrev() {
        return fromPrev;
    }

    public GraphEdge getToNext() {
        return toNext;
    }

    public GraphEdge getToPrev() {
        return toPrev;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public void setFromPrev(GraphEdge fromPrev) {
        this.fromPrev = fromPrev;
    }

    public void setTo(GraphNode to) {
        this.to = to;
    }

    public void setToNext(GraphEdge toNext) {
        this.toNext = toNext;
    }

    public void setToPrev(GraphEdge toPrev) {
        this.toPrev = toPrev;
    }

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }
}
