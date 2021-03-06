public class GraphNode {
    private final int key;
    private GraphEdge outFirst;
    private GraphEdge outLast;
    private GraphEdge inFirst;
    private GraphEdge inLast;
    private GraphNode next;
    private GraphNode prev;
    private GraphNode parent;
    private GraphNode leftChild;
    private GraphNode rightSibling;
    private GraphNode lastChild;
    private String color;
    public GraphNode(int key)
    {
        this.key = key;
        outFirst = null;
        outLast = null;
        inFirst = null;
        inLast = null;
        this.parent = null;
        this.next = null;
        this.prev = null;
        this.color = null;
        this.leftChild = null;
        this.rightSibling = null;
        this.lastChild = null;
    }
    public int getKey(){
        return this.key;
    }
    public int getOutDegree(){
        GraphEdge edge = outFirst;
        int count = 0;
        while (edge!=null)
        {
            count++;
            edge = edge.getFromNext();
        }
        return count;
    }
    public int getInDegree(){
        GraphEdge edge = inFirst;
        int count = 0;
        while (edge!=null)
        {
            count++;
            edge = edge.getToNext();
        }
        return count;
    }

    public void setNext(GraphNode next) {
        this.next = next;
        // preventing infinity loop
        if(next!=null && next.getPrev() != this)
            next.setPrev(this);
    }

    public GraphNode getNext() {
        return next;
    }

    public GraphNode getPrev() {
        return prev;
    }

    public void setPrev(GraphNode prev) {
        this.prev = prev;
        // preventing infinity loop
        if(prev!=null&&prev.getNext() != this)
            prev.setNext(this);
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public GraphNode getParent() {
        return parent;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public GraphNode getLeftChild() {
        return leftChild;
    }

    public GraphNode getRightSibling() {
        return rightSibling;
    }

    public void setLeftChild(GraphNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightSibling(GraphNode rightSibling) {
        this.rightSibling = rightSibling;
    }

    public void setLastChild(GraphNode lastChild) {
        this.lastChild = lastChild;
    }

    public GraphNode getLastChild() {
        return lastChild;
    }
}
