import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.out;

public class Polygon {

    public static void main(String[] args) throws FileNotFoundException{
	// write your code here
        Scanner scan = new Scanner(new File("polygon.dat"));
        int numberOfSets = scan.nextInt();
        scan.nextLine();
        double area = 0.0;
        double height = 0.0;
        double sum_int = 0.0;
        double angle_triangle = 0.0;

        while(scan.hasNextInt()) {
            double sides = scan.nextInt();
            double length = scan.nextInt();

            if(sides == 3.0) {
                sum_int = 180.0;
                angle_triangle = sum_int / (sides * 2);
                height = (length / 2) / Math.tan(Math.toRadians(angle_triangle));
                area = length * height / 2;
            }
            else if(sides == 4.0) {
                area = Math.pow(length, 2);
            }
            else {
                sum_int = 180 * (sides-2);
                angle_triangle = sum_int / (sides * 2);
                height = (length / 2) * Math.tan(Math.toRadians(angle_triangle));
                //area = 0.5 * height * (sides * length);
                area = sides * (length * height / 2);
            }

            out.printf("%.3f", area);
            out.println();
        }
    }
}
