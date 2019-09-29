package Week_3;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;

//class for each job; contains the threadId of thread that will process the job
//and the amount of processing time left
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

    //method that sorts the jobs in the correct order (order described in main class)
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

    /* This program simulates the processing of a list of jobs in parallel.
       Given input with the number of threads, number of jobs, m, and a list of
       processing times of each job, the output will have m lines, each line having
       the number of the thread that will process that job and the time at which
       that thread will start processing it.
     */
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

        //priority queue which stores the threadId of the thread that will process
        //that job, and the processing time left for that job
        //the priority queue is sorted first by the amount of processing time left
        // (indicating that this one will be the next free thread),
        //then by threadId if the amount of processing time left is the same
        //(lowest number thread will take the next job if both threads are empty at
        //the time)
        PriorityQueue<Job2> threads = new PriorityQueue<Job2>();
        int nextJob = 0;

        //when the program starts, fill the thread queue with numWorkers-1 jobs and
        //output start time of 0
        while(threads.size() < numWorkers) {
            threads.add(new Job2(nextJob, jobs[nextJob]));
            System.out.println(nextJob + " " + 0);
            nextJob++;
        }

        //process the rest of the jobs:
        //output the threadId of the front thread and the start time of
        //the processing of that job
        //then remove the front element of the queue and replace it with a job with
        //the same threadId, but with the starting time being the sum of the polled job's
        //processing time and the next job's processing time (this symbolizes that
        //the next job will start after the thread completes the previous job).
        while(nextJob < jobs.length) {
            System.out.println(threads.peek().threadId + " " + threads.peek().timeLeft);
            Job2 polled = threads.poll();
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
