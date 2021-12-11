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
        AdjacencyListNode<GraphNode> temp;
        //the loop adds the childs of the current layer nodes
        while (!current_layer.isEmpty())
        {
            GraphNode node = current_layer.getCurrent();
            GraphEdge edge = node.getInFirst();
            while (edge != null)
            {
                GraphNode child = edge.getTo();
                if(next_layer.isEmpty()) {
                    next_layer.setCurrent(child);
                    next_layer_last = next_layer;
                }
                else
                {
                    next_layer_last.setNext(child);
                    next_layer_last = next_layer_last.getNext();
                }
                edge = edge.getFromNext();

            }
            out.writeInt(node.getKey());
            current_layer = current_layer.getNext();
            if(!current_layer.isEmpty())
            {
                out.writeUTF(",");
            }
            else
            {
                temp = current_layer;
                current_layer = next_layer;
                next_layer = temp;
                out.writeUTF("\n");
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
            out.writeInt(x.getKey());
            //TODO MAKE SURE that the comma is not printed on the last node
            out.writeUTF(",");
            GraphEdge edge = x.getOutFirst();
            if (edge!=null)
            while (edge != null)
            {
                preorderAux(out, edge.getTo());
                edge = edge.getFromNext();
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
