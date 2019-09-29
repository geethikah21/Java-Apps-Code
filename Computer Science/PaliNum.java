import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/* This program determines whether a given number can become a paliNum (number is
   is same forward and backwards after 5 iterations of a set of operations (given
   in problem description). The program outputs the resulting number after the
   5 iterations (see problem #7 in A+ January 2016 file for further
   description of problem). The inputs for these problems are in the form of data files.
 */
public class PaliNum {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("pal.dat"));
        boolean printed = false;
        while(scan.hasNextInt()) {
            String forwardString = Integer.toString(scan.nextInt());
            String reverseString = reverseString(forwardString);
            for(int i=0; i<5; i++) {
                //System.out.println(i+1 + " " + " " + forwardString + " " + reverseString);
                if(forwardString.equals(reverseString)) {
                    System.out.println(forwardString);
                    printed = true;
                    break;
                }
                else {
                    BigInteger forward = new BigInteger(forwardString);
                    BigInteger reverse = new BigInteger(reverseString);
                    BigInteger result = forward.add(reverse);
                    forwardString = result.toString();
                    reverseString = reverseString(forwardString);
                }
            }
            if(!printed) {
                System.out.println(forwardString);
            }
            printed = false;
            //System.out.println("End");
        }
    }

    public static String reverseString(String forward_string) {
        String reverse = "";
        for(int i=0; i<forward_string.length(); i++) {
            reverse =  forward_string.charAt(i) + reverse;
        }
        return reverse;
    }
}
