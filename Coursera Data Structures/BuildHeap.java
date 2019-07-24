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

      for(int i = data.length/2; i>=0; i--) {
          siftDown(i);
      }

      //printArray(data);

    }

    public void siftDown(int i) {
        int maxIndex = i;
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;

        //System.out.println("i " + i + " left " + leftChild + " right " + rightChild);
        if(leftChild < data.length && data[leftChild] < data[maxIndex]) {
            maxIndex = leftChild;
            //System.out.println("left");
        }

        if(rightChild < data.length && data[rightChild] < data[maxIndex]) {
            //System.out.println("right");
            maxIndex = rightChild;
        }

        if(i != maxIndex) {
            //swap data[i] and data[maxIndex]
            swaps.add(new Swap(i, maxIndex));
            int temp = data[i];
            data[i] = data[maxIndex];
            data[maxIndex] = temp;
            //printArray(data);
            //System.out.println("Max index " + maxIndex + " i " + i);
            siftDown(maxIndex);
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
