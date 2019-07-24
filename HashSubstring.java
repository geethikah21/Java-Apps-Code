package Week_4;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        int p = 1000000007; int x = 263;
        List<Integer> occurrences = new ArrayList<Integer>();
        long[] hashes = precomputeHashes(t, s.length(), p, x);
        long patternHash = polyHash(s, p, x);
        /*for (int i = 0; i + m <= n; ++i) {
	    boolean equal = true;
	    for (int j = 0; j < m; ++j) {
		if (s.charAt(j) != t.charAt(i + j)) {
		     equal = false;
 		    break;
		}
	    }
            if (equal)
                occurrences.add(i);
	    }*/

        for(int i=0; i + m <= n; i++) {
            //System.out.println(hashes[i] + " " + patternHash);
            if(hashes[i] == patternHash) {
                if(t.substring(i,m+i).equals(s)) {
                    occurrences.add(i);
                }
            }
        }
        return occurrences;
    }

    public static boolean areEqual(String t, String s) {
        int m = s.length(), n = t.length();
        for (int j = 0; j < m; ++j) {
            if (s.charAt(j) != t.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static long[] precomputeHashes(String text, int patternLength, int p, int x) {
        long[] hashes = new long[text.length() - patternLength + 1];
        hashes[text.length() - patternLength] = polyHash(text.substring(text.length() - patternLength, text.length()), p, x);
        long y = 1;
        for(int i=1; i<=patternLength; i++) {
            y = (((y * x) % p) + p) % p;
        }
        for(int j=text.length() - patternLength - 1; j>=0; j--) {
            hashes[j] = (((x*hashes[j+1] + text.charAt(j) - y*text.charAt(j + patternLength)) % p) + p) % p;
        }
        return hashes;
    }

    public static long polyHash(String s, int p, int x) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (((hash * x + s.charAt(i)) % p) + p) % p;
        return hash;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

