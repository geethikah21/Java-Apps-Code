import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This program outputs the number of times the string "bean" appears in a given string
   (see problem #4 in file with Nov 2018 for a more detailed
   description of the problem). The input for these types of problems are in the
   form of data files.
 */
public class BeanParser {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("beanparser.dat"));
        int numberOfSets = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfSets; i++) {
            String line = scan.nextLine();
            System.out.println(findBean(line));
        }
    }

    public static void printArray(String[] array) { //prints out the array (for debugging purposes)
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
            System.out.println();
        }
    }

    //finds the number of instances of the word "bean" in the string
    //looks through the string and if there are all for letters of bean in a row, this counts as an
    //instance of the word
    public static int findBean(String line) {
        String letterToLookFor = "b";
        int numberOfInstances = 0;
        int numberOfLettersInARow = 0;
        for(int i=0; i<line.length(); i++) {
            switch(numberOfLettersInARow) {
                case 0:
                    if(line.substring(i,i+1).equals("b")) {
                        numberOfLettersInARow++;
                    }
                    break;
                case 1:
                    if(line.substring(i,i+1).equals("e")) {
                        numberOfLettersInARow++;
                    }
                    break;
                case 2:
                    if(line.substring(i,i+1).equals("a")) {
                        numberOfLettersInARow++;
                    }
                    break;
                case 3:
                    if(line.substring(i,i+1).equals("n")) {
                        numberOfLettersInARow++;
                    }
                    numberOfInstances++;
                    numberOfLettersInARow = 0;
                    break;
                default: break;
            }
        }
        return numberOfInstances;
    }
}