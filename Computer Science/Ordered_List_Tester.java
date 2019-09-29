import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Ordered list tester I wrote to test whether or not
// I knew the order of musical tempo terms (how fast a piece is playing)
//from slowest to fastest.
//When a user inputs a term, the program checks to see if the term inputted
// by the user matches the term in that position in the data file
//Ex: if the user is inputting the second term in the list, the program will
// check to see if the second term in the file matches the one that the user inputted
//if not, the user is prompted to try again

public class Ordered_List_Tester {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File("ordered_list.dat"));
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> orderedList = new ArrayList<String>();
        while(fileReader.hasNextLine()) {
            orderedList.add(fileReader.nextLine());
        }
        for(int i=0; i<orderedList.size(); i++) {
            System.out.println("Enter term " + (i+1) + ": ");
            String input = keyboard.nextLine();
            while(!(input.equals(orderedList.get(i)))) {
                System.out.println("Try again");
                input = keyboard.nextLine();
            }
            System.out.println("Correct!");
            System.out.println();
        }
        System.out.println("Correct order: ");
        printList(orderedList);
    }

    public static void printList(ArrayList<String> list) {
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}