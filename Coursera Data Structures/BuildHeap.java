package Week_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    //printing out the response
    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      /*for (int i = 0; i < data.length; ++i) {
        for (int j = i + 1; j < data.length; ++j) {
          if (data[i] > data[j]) {
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
          }
        }
      }*/

      /* This program generates a min-heap from an array using HeapSort. The input
         is a series of integers representing the original array. The output of this
         program is the sequence of swaps used to generate the min-heap (print 0
         if array is already sorted into a min-heap format).
       */

      //from the middle of the array to the first index, call siftDown on these
      //indices (these are parent nodes)
      for(int i = data.length/2; i>=0; i--) {
          siftDown(i);
      }

    }

    /* This method takes a parent node index i and its left/right children,
     * designated as 2*i + 1 and 2*i + 2 respectively.*/
    public void siftDown(int i) {
        int minIndex = i;
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;

        //since this is a min-heap, the maximum value will be at the top
        //and every child node has to be less than or equal to its parent.
        //Thus, if the left child is less than the parent, then the minIndex
        //becomes the index of the left child.
        if(leftChild < data.length && data[leftChild] < data[minIndex]) {
            minIndex = leftChild;
        }

        //if the right child is less than the left child (if above if statement was
        //true) or the parent (if above if statement was false), then the min
        //index becomes the index of the rightChild.
        if(rightChild < data.length && data[rightChild] < data[minIndex]) {
            minIndex = rightChild;
        }

        //if min index isn't still the parent node (i), then swap the parent
        //and the data in minIndex and then continue to sift the minIndex node
        //down until it's greater than their left/right children
        if(i != minIndex) {
            //swap data[i] and data[minIndex]
            swaps.add(new Swap(i, minIndex)); //add the swap to the swaps ArrayList
            int temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
            siftDown(minIndex);
        }
    }

    public void printArray(int[] tree) {
        for(int k=0; k<tree.length; k++) {
            System.out.print(tree[k] + " ");
        }
        System.out.println();
    }


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
