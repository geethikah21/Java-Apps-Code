package Week_4;

import java.io.*;
import java.util.*;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    //private List<String> elems;
    private Element[] elems;

    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        //out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        if(wasFound) {
            System.out.println("yes");
        }
        else {
            System.out.println("no");
        }
         //out.flush();
    }

    private void processQuery(Query query) {

        //orig. code (given with assignment from Coursera)
        /*switch (query.type) {
            case "add":
                if (!elems.contains(query.s))
                    elems.add(0, query.s);
                break;
            case "del":
                if (elems.contains(query.s))
                    elems.remove(query.s);
                break;
            case "find":
                writeSearchResult(elems.contains(query.s));
                break;
            case "check":
                for (String cur : elems)
                    if (hashFunc(cur) == query.ind)
                        out.print(cur + " ");
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }*/

        //My implementation

        /* This program implements the chaining scheme of hashing. It is similar to the
            PhoneBook program (also in this GitHub folder), but the amount of
            memory used is much less. There are 4 queries in this version of the
            program: add, del, find, and check. The input consists of an integer m, the
            number of buckets used for hashing, and an integer n, the number of queries.
            The next n lines of the input detail each query's action.
            The output will be all of the find queries.
         */

        switch (query.type) {

            //adds the query's string to the bucket corresponding to its hash function
            case "add":
                if(!elems[hashFunc(query.s)].elems.contains(query.s)) {
                    elems[hashFunc(query.s)].elems.add(0, query.s);
                }
                break;

            //deletes the query's string from the bucket corresponding to its
            //hash function
            case "del":
                if(elems[hashFunc(query.s)].elems.contains(query.s)) {
                    elems[hashFunc(query.s)].elems.remove(query.s);
                }
                break;

            //sees if the hash table contains the given string
            case "find":
                writeSearchResult(elems[hashFunc(query.s)].elems.contains(query.s));
                break;

            //outputs the contents of one bucket (the query's index tells which one)
            case "check":
                for(int i=0; i<elems[query.ind].elems.size(); i++) {
                    System.out.print(elems[query.ind].elems.get(i) + " ");
                }
                System.out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        elems = new Element[bucketCount];
        for(int i=0; i<elems.length; i++) {
            elems[i] = new Element();
        }
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    class Element {
        private ArrayList<String> elems;

        public Element() {
            elems = new ArrayList<String>();
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
