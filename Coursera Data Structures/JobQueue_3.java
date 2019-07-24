package Week_3;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;

class Job2 implements Comparable<Job2> {
    int threadId = 0;
    long timeLeft = 0;

    public Job2(int threadId, long timeLeft) {
        this.threadId = threadId;
        this.timeLeft = timeLeft;
    }

    public void setTimeLeft(long timeLeft) { this.timeLeft = timeLeft; }

    public int getThreadId() { return threadId; }
    public long getTimeLeft() { return timeLeft; }

    public int compareTo(Job2 other) {
        if(this.timeLeft < other.timeLeft) {
            return -1;
        }
        else if(this.timeLeft > other.timeLeft) {
            return 1;
        }
        else {
            if(this.threadId < other.threadId) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}

public class JobQueue_3 {
    private int numWorkers;
    private long[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue_3().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new long[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        /*assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }*/

        PriorityQueue<Job2> threads = new PriorityQueue<Job2>();
        int nextJob = 0;

        while(threads.size() < numWorkers) {
            threads.add(new Job2(nextJob, jobs[nextJob]));
            System.out.println(nextJob + " " + 0);
            nextJob++;
        }
        while(nextJob < jobs.length) {
            //System.out.println("min time " + minTime);
            //printTimePassed(threads);
            //threads.peek().timeLeft -= minTime;
            System.out.println(threads.peek().threadId + " " + threads.peek().timeLeft);
            Job2 polled = threads.poll();
            //threads.add(new Job2(threads.peek().threadId, threads.peek().timePassed, jobs[nextJob]));
            threads.add(new Job2(polled.threadId,polled.timeLeft + jobs[nextJob]));
            nextJob++;
        }
    }

    public void printArray(Job2[] tree) {
        System.out.println("array print");
        for(int k=0; k<tree.length; k++) {
            System.out.print(tree[k].threadId + " ");
        }
        System.out.println();
    }

    public void printTimePassed(PriorityQueue<Job2> queue) {
        System.out.println("start time passed print");
        Iterator<Job2> it = queue.iterator();
        while(it.hasNext()) {
            Job2 next = it.next();
            System.out.println("id " + next.threadId + " tl " + next.timeLeft);
        }
        System.out.println("End time passed print");
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        //writeResponse();
        out.close();
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
