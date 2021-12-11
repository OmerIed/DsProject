public class AdjacencyListNode<T> {
    private T value;
    private AdjacencyListNode<T> next;
    private AdjacencyListNode<T> previous;
    public AdjacencyListNode(){
        this.next = null;
        this.value = null;
        this.previous = null;
    }
    public AdjacencyListNode(T node) {
        this.next = null;
        this.value = node;
        this.previous = null;
    }
    public T getCurrent() {
        return this.value;
    }

    public AdjacencyListNode<T> getNext() {
        return this.next;
    }

    public AdjacencyListNode<T> getPrevious() {
        return this.previous;
    }

    public void setCurrent(T current) {
        this.value = current;
    }

    public void setNext(T node) {
        this.next = new AdjacencyListNode<T>(node);
    }

    public void setPrevious(T node) {
        this.previous = new AdjacencyListNode<T>(node);
    }
    public int getLength()
    {
        if(this.value==null)
            return 0;
        int count = 1;
        AdjacencyListNode<T> x = this.next;
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
