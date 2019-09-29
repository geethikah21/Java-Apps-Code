package Week_1;

import java.util.*;
import java.io.*;

public class StackWithMax {
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

    /* this program extends the Stack interface by adding an additional method,
        max(), to it. Max finds the maximum element in the stack at a given time.
        The program reads input from the keyboard: the first line of input contains
        the number of queries, n. The next n lines contain a query, which can be
        either push, pop, or max. If the query is push, then a number will appear
        next to it on that line.
     */
    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>(); //stores the current stack
        ArrayList<Integer> max = new ArrayList<Integer>(); //stores the max value(s)
        int maxNum = Integer.MIN_VALUE;

        //processing each query
        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                //if the value being pushed onto the stack is greater than or equal
                //to the current maximum, add it to the front of the max ArrayList

                //if the operation is pop: if the number being popped is the same
                //as the value at the front of the ArrayList max (the current max value),
                //remove that value from the ArrayList as well. This makes the new
                //max value the value that's now at the front of the ArrayList

                //if the operation is max: print out the value at the front of the
                //ArrayList
                if(value >= maxNum) {
                    maxNum = value;
                    max.add(0, value);
                }
                stack.push(value);
            }
            else if ("pop".equals(operation)) {
                if(stack.peek().toString().equals(max.get(0).toString())) {
                    max.remove(0);
                }
                stack.pop();
            }
            else if ("max".equals(operation)) {
                System.out.println(max.get(0));
            }
        }
    }

    public void addToArray(int[] arr) {
        for(int i=0; i<arr.length-1; i++) {

        }
    }

    public void printList(int[] list) {
        for(int i=0; i<list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}