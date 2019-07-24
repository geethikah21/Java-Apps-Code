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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> max = new ArrayList<Integer>();
        int maxNum = Integer.MIN_VALUE;

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                if(value >= maxNum) {
                    maxNum = value;
                    max.add(0, value);
                }
                stack.push(value);
            } else if ("pop".equals(operation)) {
                /*stack.peek().toString().equals(max.get(0).toString())*/
                /*stack.peek() == max.get(0)*/
                System.out.println(stack.peek() + " " + max.get(0));
                if(stack.peek().toString().equals(max.get(0).toString())) {
                    max.remove(0);
                }
                System.out.println("popping " + stack.peek());
                stack.pop();
            } else if ("max".equals(operation)) {
                //System.out.println(Collections.max(stack));
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

/*for(int i=0; i<numberOfQueriesBeforeMax; i++) {
                    if(max_stack.isEmpty()) {
                        max_stack.push(temp_stack.pop());
                    }
                    else {
                        if(temp_stack.peek() > max_stack.peek()) {
                            max_stack.push(temp_stack.pop());
                        }
                        else {
                            temp_stack.pop();
                        }
                    }
                }
                System.out.println(max_stack.pop());
                temp_stack = stack;*/
