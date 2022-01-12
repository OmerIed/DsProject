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
        // inserting edge to head of the list from and to nodes.
        if(from.getOutFirst() != null) {
            from.getOutFirst().setFromPrev(this);
        }
        else
        {
            from.setOutLast(this);
        }
        this.setFromNext(from.getOutFirst());
        from.setOutFirst(this);
        this.setFromPrev(null);

        if(to.getInFirst() != null)
        {
            to.getInFirst().setToPrev(this);
        }
        else
        {
            to.setInLast(this);
        }
        this.setToNext(to.getInFirst());
        to.setInFirst(this);
        this.setToPrev(null);

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
