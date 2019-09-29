import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This program calculates the area of trapezoid given its top and bottom base lengths
   and its height (see problem #1 in file with Nov 2018 for a more detailed
   description of the problem). The input for these types of problems are in the
   form of data files.
 */
public class BeanTrapezoids {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("beantrapezoids.dat"));
        int numberOfLines = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfLines; i++) {
            //printing out area rounded to two decimal places:
            //(sum of top and bottom base * height) / 2
            System.out.printf("%.2f", calculateSumOfTopAndBottomBase(scan.nextDouble(), scan.nextDouble()) * scan.nextDouble() / 2);
            System.out.println();
        }
    }

    public static double calculateSumOfTopAndBottomBase(double t, double b) {
        return t + b;
    }

}