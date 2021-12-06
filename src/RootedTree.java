import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class RootedTree {
    private GraphNode root;
    public RootedTree(){
        this.root = null;
    }
    public void printByLayer(DataOutputStream out) throws IOException {
        // we save the root in a linked list
        LinkedList<GraphNode> current_layer = new LinkedList<GraphNode>();
        current_layer.addFirst(root);
        LinkedList<GraphNode> next_layer = new LinkedList<GraphNode>();
        LinkedList<GraphNode> temp;
        //the loop adds the childs of the current layer nodes
        while (!current_layer.isEmpty())
        {
            GraphNode node = current_layer.pollLast();
            for(GraphNode child: node.getOutEdges())
            {
                next_layer.addFirst(child);
            }
            out.writeInt(node.getKey());
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
    public void preorderPrint(DataOutputStream out){

    }

    public GraphNode getRoot() {
        return root;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }
}
