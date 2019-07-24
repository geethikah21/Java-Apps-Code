package Week_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        boolean success = true;
        int errorPosition = -1;

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next,position));
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if(opening_brackets_stack.isEmpty()) {
                    success = false;
                    errorPosition = position+1;
                    break;
                }
                Bracket top = opening_brackets_stack.pop();

                if(top.Match(next) == false) {
                    success = false;
                    errorPosition = position+1;
                    break;
                }
            }
        }

        // Printing answer, write your code here
        if(opening_brackets_stack.size() == text.length()) {
            System.out.println(opening_brackets_stack.get(0).position+1);
        }
        else if(!success) {
            System.out.println(errorPosition);
        }
        else {
            if(opening_brackets_stack.isEmpty()) {
                System.out.println("Success");
            }
            else {
                System.out.println(opening_brackets_stack.pop().position+1);
            }
        }
    }
}