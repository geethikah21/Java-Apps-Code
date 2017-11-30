import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.out;

public class Factors {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("factors.dat"));
        int numberOfSets = scan.nextInt();
        scan.nextLine();
        while(scan.hasNextInt()) {
            int number = scan.nextInt();
            int factorial = factorial(number);
            int numFactors = 0;

            for(int j=1; j<=factorial; j++) {
                if(factorial % j == 0) {
                    numFactors++;
                }
            }
            out.println(numFactors);

        }
    }
    public static int factorial(int number) {
        int factorial = number;
        for(int i=number-1; i>=1; i--) {
            factorial*=i;
        }
        return factorial;
    }
}
