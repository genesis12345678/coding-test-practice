import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int r = Integer.parseInt(br.readLine());
        Node root = new Node(r);

        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }

            int num = Integer.parseInt(s);

            root.insert(num);
        }

        postOrder(root);
    }

    static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.println(node.num);
    }

    static class Node {
        int num;
        Node leftChild, rightChild;

        public Node(int num) {
            this.num = num;
        }

        public void insert(int node) {
            if (this.num > node) {

                if (this.leftChild == null) {
                    leftChild = new Node(node);
                } else {
                    leftChild.insert(node);
                }

            } else {

                if (this.rightChild == null) {
                    rightChild = new Node(node);
                } else {
                    rightChild.insert(node);
                }
            }
        }
    }
}
