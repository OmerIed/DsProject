public class AdjacencyListNode<T> {
    private T value;
    private AdjacencyListNode<T> next;
    private AdjacencyListNode<T> previous;
    public AdjacencyListNode(){
        this.next = null;
        this.value = null;
        this.previous = null;
    }
    public AdjacencyListNode(T item) {
        this.next = null;
        this.value = item;
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

    public void setNext(T item) {
        this.next = new AdjacencyListNode<T>(item);
        this.next.previous = this;
    }

    public void setPrevious(T item) {
        this.previous = new AdjacencyListNode<T>(item);
        this.previous.next = this;
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

    public void remove(AdjacencyListNode<T> item){
        if (item.previous != null)
        {
            item.previous.next = item.next;
            if(item.next != null){
                item.next.previous = item.previous;
            }
            item.next = null;
            item.previous = null;
        }
        else
        {
            if(item.next != null){
                item.next.previous = null;
            }
            item.next = null;
        }
        item.value = null;
    }
}
