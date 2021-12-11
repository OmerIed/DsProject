public class AdjacencyListNode {
    private GraphNode value;
    private AdjacencyListNode next;
    private AdjacencyListNode previous;
    public AdjacencyListNode(){
        this.next = null;
        this.value = null;
        this.previous = null;
    }
    public AdjacencyListNode(GraphNode node) {
        this.next = null;
        this.value = node;
        this.previous = null;
    }
    public GraphNode getCurrent() {
        return this.value;
    }

    public AdjacencyListNode getNext() {
        return this.next;
    }

    public AdjacencyListNode getPrevious() {
        return this.previous;
    }

    public void setCurrent(GraphNode current) {
        this.value = current;
    }

    public void setNext(GraphNode node) {
        this.next = new AdjacencyListNode(node);
        this.last = this.next;

    }

    public void setPrevious(GraphNode node) {
        this.previous = new AdjacencyListNode(node);
    }
    public int getLength()
    {
        if(this.value==null)
            return 0;
        int count = 1;
        AdjacencyListNode x = this.next;
        while (x != null){
            count++;
            x = x.next;
        }
        return count;
    }
    public boolean isEmpty(){
        return value == null;
    }
    public void remove(){
        if (this.previous != null)
        {
            this.previous.next = this.next;
            if(this.next != null){
                this.next.previous = this.previous;
            }
            this.next = null;
            this.previous = null;
        }
        else
        {
            if(this.next != null){
                this.next.previous = null;
            }
            this.next = null;
        }
        this.value = null;
    }
}
