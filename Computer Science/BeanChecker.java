import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This program outputs either "YES" or "NO" depending on whether a given bean's
   name has an e in it and if its weight is odd (see problem #11 in file with Nov 2018
   for a more detailed description of the problem). The input for these types of problems
   are in the form of data files.
 */
public class BeanChecker {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("beanchecker.dat"));
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfDataSets; i++) {
            String name = scan.next();
            int weight = scan.nextInt();
            if(nameContainsE(name) && weightIsOdd(weight)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

    //determines if the bean's name contains the letter e, uppercase or lowercase
    public static boolean nameContainsE(String name) {
        if(name.contains("e") || name.contains("E")) {
            return true;
        }
        return false;
    }

    //determines if the bean's weight is odd
    public static boolean weightIsOdd(int weight) {
        if(weight % 2 == 1) {
            return true;
        }
        return false;
    }
}