package Week_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new ArrayList<Integer>();
    }

    /* this program simulates the processing of network packets as they come and leave.
        the inputs of the program include the size of the network buffer(the number of
        packets that can be processed at once) and the arrival/processing time
        of each incoming packet. If the network buffer is full when a packet arrives,
        that packet is dropped and isn't processed (a -1 is printed in the output). For a
        packet that comes when there is space in the buffer, the arrival time
        is printed
     */
    public Response Process(Request request) {
        // write your code here
        //calculate the time that the incoming packet is busy until
        int requestBusyUntil = request.arrival_time + request.process_time;

        //remove all the packets that will be finished processing when the current
        //packet being processed arrives
        while(!finish_time_.isEmpty() && finish_time_.get(0) <= request.arrival_time) {
            finish_time_.remove(0);
        }

        //if there are no packets being processed after removing all
        //packets that are finished processing, add finish time to arraylist
        //and then output the start time

        //if there are packets still being processed, check to see if the buffer is
        //full. If it is, then the current packet is dropped. Otherwise,
        //output the finish time of the last packet (before adding the current one)
        //to indicate the current packet's start time (called requestStartTime)
        //and sum the process time of the current packet with requestStartTime to
        //determine when the incoming packet (the passed parameter) will finish processing
        if(finish_time_.isEmpty()) {
            finish_time_.add(requestBusyUntil);
            return new Response(false, request.arrival_time);
        }
        else {
            if(finish_time_.size() == size_) {
                return new Response(false, -1);
            }
            else {
                int requestStartTime = finish_time_.get(finish_time_.size()-1);
                finish_time_.add(request.process_time + requestStartTime);
                return new Response(false, requestStartTime);
            }
        }
    }

    private int size_;
    private ArrayList<Integer> finish_time_;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
