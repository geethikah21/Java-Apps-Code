package Week_6;

import java.util.*;
import java.io.*;

public class is_bst_3 {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        /* This program determines whether or not a given binary tree is a valid binary
           search tree (if it was constructed correctly). The input consists of an integer n containing the number
			of nodes and the next n lines detail the index of each vertex's key, left,
			and right vertex (a -1 for left or right vertex means that there isn't a
			left or right vertex). The program outputs "correct" or "incorrect", which
			represents whether or not the binary search tree was built correctly.
			In this program, the left node must be strictly less than its parent node,
			and the right node must be strictly greater than its parent node.

			The way I approached this: I built the inorder traversal of the tree,
			and if the numbers are in strictly increasing order, then the tree is a valid
			binary search tree. If not, the tree is not a valid binary search
			tree.
         */
        boolean isBinarySearchTree() {
          // Implement correct algorithm here
            if(nodes == 0) {
                return true;
            }
            else {
                List<Integer> order = inOrder();
                for(int i=1; i<order.size(); i++) {
                    if(order.get(i-1) > order.get(i)) {
                        return false;
                    }
                }
            }
            return true;
        }

        //receives the ArrayList containing the in order traversal from the
        //recursive in order traversal method (below this method)
        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that

            if(nodes != 0) {
                inOrderTraversal(result, tree[0]);
            }

            return result;
        }

        //builds the ArrayList containing the in order traversal of the given
        //binary tree
        void inOrderTraversal(ArrayList<Integer> result, Node node) {
            if(node.left != -1) {
                inOrderTraversal(result, tree[node.left]);
            }
            result.add(node.key);
            if(node.right != -1) {
                inOrderTraversal(result, tree[node.right]);
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_3().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        //System.out.println(tree.inOrder());
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
