import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This program encodes a given letter to another. The encoding process depends on
   the original letter (see problem #12 in A+ January 2016 file for further
   description of problem). The inputs for these problems are in the form of data files.
 */
public class ThatsAWrap {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("wrap.dat"));
        while(scan.hasNext()) {
            char letter = (char)(scan.next().charAt(0));
            int numericalValueOfLetter = letter - 64;
            int numericalValueOfNewLetter = encodeLetter(numericalValueOfLetter);
            if(numericalValueOfNewLetter < 26) {
                System.out.println(letter + "==>" + (char)(numericalValueOfNewLetter + 64));
            }
            else {
                System.out.println(letter + "==>" + (char)(wrapAround(numericalValueOfNewLetter)+64));
            }
        }
    }

    public static int encodeLetter(int numericalValueOfLetter) {
        int newLetterValue = 0;
        switch(numericalValueOfLetter) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                newLetterValue = numericalValueOfLetter * 2; break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                newLetterValue = ((numericalValueOfLetter % 3) * 5); break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                newLetterValue = ((numericalValueOfLetter / 4) * 8); break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                newLetterValue = (addDigits(numericalValueOfLetter) * 10); break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                newLetterValue = (findSecondLargestFactorLessThanItself(numericalValueOfLetter) * 12);
        }

        return newLetterValue;
    }

    public static int addDigits(int numericalValueOfLetter) {
        String letterString = Integer.toString(numericalValueOfLetter);
        int sum = 0;

        for(int i=0; i<letterString.length(); i++) {
            sum += Integer.parseInt(letterString.substring(i,i+1));
        }
        return sum;
    }

    public static int findSecondLargestFactorLessThanItself(int numericalValueOfLetter) {
        int maximumFactor = 0;
        for(int i=1; i<numericalValueOfLetter; i++) {
            if(numericalValueOfLetter % i == 0) {
                maximumFactor = i;
            }
        }
        return maximumFactor;
    }

    public static int wrapAround(int numericalValueOfLetter) {
        int remainder = numericalValueOfLetter % 26;
        return remainder;
    }
}
