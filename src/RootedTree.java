import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    private GraphNode root;
    public RootedTree(){
        this.root = null;
    }
    public void printByLayer(DataOutputStream out) throws IOException {
        // we save the root in a list
        AdjacencyListNode current_layer = new AdjacencyListNode(root);
        AdjacencyListNode next_layer = new AdjacencyListNode();
        AdjacencyListNode temp;
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
            for(GraphNode child: x.getOutEdges())
            {
                preorderAux(out, child);
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
