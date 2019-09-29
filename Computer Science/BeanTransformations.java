import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/* Given a number, this program outputs a corresponding number that represents the
   original number after it undergoes a series of transformations that are outlined in
   the problem description (see problem #6 in file with Nov 2018 for a more detailed
   description of the problem). The input for these types of problems are in the
   form of data files.
 */
public class BeanTransformations {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("beantransformations.dat"));
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < numberOfDataSets; i++) {
            while(scan.hasNextInt()) {
                int number = scan.nextInt();
                BigInteger bigNumber = new BigInteger(Integer.toString(number));
                String binary = bigNumber.toString(2);
                binary = padWithZeroes(binary);
                int numberOfOnesInEveryFour = 0;
                String onesString = "";

                for (int j = 0; j < binary.length(); j++) {
                    if (binary.substring(j, j + 1).equals("1")) {
                        numberOfOnesInEveryFour++;
                    }
                    if ((j + 1) % 4 == 0) {
                        onesString += numberOfOnesInEveryFour;
                        numberOfOnesInEveryFour = 0;
                    }
                    if(j == binary.length()-1 && (j + 1) % 4 != 0) {
                        onesString += numberOfOnesInEveryFour;
                    }
                }
                System.out.println(convertHexToBaseTen(onesString));
            }
        }
    }

    public static int convertHexToBaseTen(String hex) {
        int position = hex.length()-1;
        int baseTen = 0;
        for(int i=0; i<hex.length(); i++) {
            baseTen +=  getHexNumber(hex.substring(i,i+1)) * Math.pow(16,position);
            position--;
        }
        return baseTen;
    }

    public static int getHexNumber(String hex) {
        switch(hex) {
            case "0": return 0;
            case "1": return 1;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "a": return 10;
            case "b": return 11;
            case "c": return 12;
            case "d": return 13;
            case "e": return 14;
            case "f": return 15;
            default: return 0;
        }
    }

    public static String padWithZeroes(String binary) {
        while(binary.length() % 4 != 0) {
            binary = "0" + binary;
        }
        return binary;
    }
}