package Week_6;

import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		/* This program outputs the pre-order, in-order, and post-orders of a given
			binary tree. The input consists of an integer n containing the number
			of nodes and the next n lines detail the index of each vertex's key, left,
			and right vertex (a -1 for left or right vertex means that there isn't a
			left or right vertex). The program outputs the in-order traversal first,
			then the pre-order traversal, then the post-order.
		 */

		//creates the list that will contain the in order traversal and passes it to
		//the recursive in order method
		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that

			inOrderTraversal(result, 0);
                        
			return result;
		}

		//creates the list that will contain the pre order traversal and passes it to
		//the recursive pre order method
		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that

			preOrderTraversal(result, 0);
                        
			return result;
		}

		//creates the list that will contain the post order traversal and passes it to
		//the recursive post order method
		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that

			postOrderTraversal(result, 0);

			return result;
		}

		//builds the in order traversal ArrayList recursively
		void inOrderTraversal(ArrayList<Integer> result, int index) {
			if(index == -1) {
				return;
			}

			inOrderTraversal(result, left[index]);
			result.add(key[index]);
			inOrderTraversal(result, right[index]);

		}

		//builds the pre order traversal ArrayList recursively
		void preOrderTraversal(ArrayList<Integer> result, int index) {
			if(index == -1) {
				return;
			}

			result.add(key[index]);
			preOrderTraversal(result, left[index]);
			preOrderTraversal(result, right[index]);
		}

		//builds the post order traversal ArrayList recursively
		void postOrderTraversal(ArrayList<Integer> result, int index) {
			if(index == -1) {
				return;
			}

			postOrderTraversal(result, left[index]);
			postOrderTraversal(result, right[index]);
			result.add(key[index]);
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
