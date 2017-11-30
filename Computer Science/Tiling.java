import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tiling {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("tiling.dat"));
        int numberOfSets = scan.nextInt();
        scan.nextLine();

        while(scan.hasNextInt()) {
            int length = scan.nextInt();
            int width = scan.nextInt();
            int square_side = 0;

            if(length < width) {
                for(int i=2; i<=length; i++) {
                    if(length % i == 0 && width % i == 0) {
                        square_side = i;
                    }
                }
            }
            else {
                for(int j=2; j<=width; j++) {
                    if(width % j == 0 && length % j == 0) {
                        square_side = j;
                    }
                }
            }
            System.out.println(square_side);

        }
    }
}
