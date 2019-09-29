package Week_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables_2 {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables_2(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables_2(reader, writer).run();
        writer.writer.flush();
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }

        /* This program process a series of merge operations of n tables. The input
        *  consists of an integer n, the number of tables, and m, the number
         * of merge operations. The next m lines have the two tables involved
         * in each merge. The output is the maximum size of all of the tables
         * after each operation. */

        //given a node, find the root of the table and then designate the parent
        //of each node that was traversed as the root parent
        Table getParent() {
            // find super parent and compress path
            ArrayList<Table> traversedPath = new ArrayList<Table>();
            Table current = this;
            while(current.parent != current) {
                traversedPath.add(current);
                current = current.parent;
            }
            //rank = 1
            //make the parent of each traversed node the super parent and then chage
            //its rank to 1
            for(int i=0; i<traversedPath.size(); i++) {
                traversedPath.get(i).parent = current;
                traversedPath.get(i).rank = 1;
            }

            return current;
        }
    }

    int maximumNumberOfRows = -1;

    //merges two tables
    //merge the table with the lower rank (distance from parent node to the lowest node)
    //into the table with the higher rank
    //add the merging table's number of rows to the number
    // of rows of the table that isn't merging and make the non-merged table the parent
    //of the merged table

    //if rank of the two tables is the same, increase rank of the table that
    // received the merging table by 1

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        // merge two components here
        // use rank heuristic
        // update maximumNumberOfRows
        if(realDestination.rank > realSource.rank) {
            //merge source into destination
            realSource.parent = realDestination;
            realDestination.numberOfRows += realSource.numberOfRows;
            realSource.numberOfRows = 0;
            if(realDestination.numberOfRows > maximumNumberOfRows) {
                maximumNumberOfRows = realDestination.numberOfRows;
            }
        }
        else {
            //merge destination into source
            realDestination.parent = realSource;
            realSource.numberOfRows += realDestination.numberOfRows;
            realDestination.numberOfRows = 0;
            if(realSource.numberOfRows > maximumNumberOfRows) {
                maximumNumberOfRows = realSource.numberOfRows;
            }

            if(realDestination.rank == realSource.rank) {
                realSource.rank++;
            }
        }
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
