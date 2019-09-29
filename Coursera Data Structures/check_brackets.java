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

/* This program checks to see if a given string of opening and closing brackets
    contains matching closing brackets for every opening bracket. In order to solve this,
    a stack is used to store all of the opening brackets in the string. Once a closing
    bracket is found, the topmost opening bracket is popped off of the stack. If the
    type of opening bracket doesn't match the closing one, then it is known that
    the string of brackets are not properly matched. Else, the program continues
    to run.
 */
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

            //if next character in string is an opening bracket, push onto stack
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next,position));
            }

            //if next is a closing bracket, then pop top opening bracket off stack
            //and check to see if it is the same type as the closing bracket
            //if they don't, the final output is the position in the string in which
            //the brackets stop matching (this is stored in errorPosition)
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