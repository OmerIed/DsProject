import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    private GraphNode root;
    public RootedTree(){
        this.root = null;
    }
    public void printByLayer(DataOutputStream out) throws IOException {
        // we save the root in a list
        AdjacencyListNode<GraphNode> current_layer = new AdjacencyListNode<GraphNode>(root);
        AdjacencyListNode<GraphNode> next_layer = new AdjacencyListNode<GraphNode>();
        AdjacencyListNode<GraphNode> next_layer_last = next_layer;
        AdjacencyListNode<GraphNode>  current_layer_start = current_layer;
        AdjacencyListNode<GraphNode> temp;
        //the loop adds the childs of the current layer nodes
        while (current_layer != null && !current_layer.isEmpty())
        {
            if(current_layer.getPrevious() == null)
            {
                current_layer_start = current_layer;
            }
            GraphNode node = current_layer.getCurrent();
            //GraphEdge edge = node.getOutFirst();
            GraphNode child = node.getLeftChild();
            while (child != null)
            {
                if(next_layer.isEmpty()) {
                    next_layer.setCurrent(child);
                    next_layer_last = next_layer;
                }
                else
                {
                    next_layer_last.setNext(child);
                    next_layer_last = next_layer_last.getNext();
                }
                child = child.getRightSibling();

            }

            out.writeBytes(String.valueOf(node.getKey()));
            current_layer = current_layer.getNext();
            if(current_layer != null && !current_layer.isEmpty())
            {
                out.writeBytes(",");
            }
            else
            {
                temp = current_layer;
                current_layer = next_layer;
                current_layer_start.remove(current_layer_start);
                next_layer = current_layer_start;
                if(current_layer!=null&&!current_layer.isEmpty())
                    out.writeBytes(System.lineSeparator());
            }
        }
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        preorderAux(out, root);
    }

    public void preorderAux(DataOutputStream out, GraphNode x) throws IOException {
        if(x == null){
            return;
        }
        else
        {
            if(x != root)
                out.writeBytes(",");
            out.writeBytes(String.valueOf(x.getKey()));
            //TODO MAKE SURE that the comma is not printed on the last node
            GraphNode child = x.getLeftChild();
            while (child != null)
            {
                preorderAux(out, child);
                child = child.getRightSibling();
            }
        }

    }

    public GraphNode getRoot() {
        return root;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }
}
