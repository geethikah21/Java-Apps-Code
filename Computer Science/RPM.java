import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//this program converts a value in minutes per rotation to rotations per minute
//Problem #2 In UIL December 2016 Packet
public class RPM {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("rpm.dat"));
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<numberOfDataSets; i++) {
            double mpr = scan.nextDouble();
            double rpm = 1/mpr;
            System.out.printf("%.2f", rpm);
            System.out.println();
        }
    }
}