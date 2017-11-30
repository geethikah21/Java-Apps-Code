import java.util.Scanner;
import java.io.*;

public class Voltage {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("voltage.dat"));
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfDataSets; i++) {
            String line1 = scan.nextLine();
            String line2 = scan.nextLine();
            String value1 = line1.substring(0,1);
            double number1 = Double.parseDouble(line1.substring(line1.indexOf("=") + 2));
            String value2 = line2.substring(0,1);
            double number2 = Double.parseDouble(line2.substring(line2.indexOf("=") + 2));

            switch(value1) {
                case "V":
                    if(value2.equals("I")) {
                        System.out.printf("V = %.3f ", number1);
                        System.out.printf("I = %.3f ", number2);
                        System.out.printf("R = %.3f ", number1 / number2);
                        System.out.printf("P = %.3f", number1 * number2);
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("R")) {
                        System.out.printf("V = %.3f ", number1);
                        System.out.printf("I = %.3f ", number1 / number2);
                        System.out.printf("R = %.3f ", number2);
                        System.out.printf("P = %.3f", (number1 / number2) * number1);
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("P")) {
                        System.out.printf("V = %.3f ", number1);
                        System.out.printf("I = %.3f ", number2 / number1);
                        System.out.printf("R = %.3f ", number1 / (number2 / number1));
                        System.out.printf("P = %.3f", (number2 / number1) * number1);
                        System.out.println();
                        break;
                    }
                case "I":
                    if(value2.equals("R")) {
                        System.out.printf("V = %.3f ", number1 * number2);
                        System.out.printf("I = %.3f ", number1);
                        System.out.printf("R = %.3f ", number2);
                        System.out.printf("P = %.3f", number1 * (number1 * number2));
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("V")) {
                        System.out.printf("V = %.3f ", number2);
                        System.out.printf("I = %.3f ", number1);
                        System.out.printf("R = %.3f ", number2 / number1);
                        System.out.printf("P = %.3f",  number1 * number2);
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("P")) {
                        System.out.printf("V = %.3f ", number2 / number1);
                        System.out.printf("I = %.3f ", number1);
                        System.out.printf("R = %.3f ", (number2 / number1) / number1);
                        System.out.printf("P = %.3f",  number2);
                        System.out.println();
                        break;
                    }
                case "R":
                    if(value2.equals("I")) {
                        System.out.printf("V = %.3f ", number1 * number2);
                        System.out.printf("I = %.3f ", number2);
                        System.out.printf("R = %.3f ", number1);
                        System.out.printf("P = %.3f",  number2 * (number1 * number2));
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("P")) {
                        System.out.printf("V = %.3f ", Math.sqrt(number2 * number1));
                        System.out.printf("I = %.3f ", Math.sqrt(number2 / number1));
                        System.out.printf("R = %.3f ", number1);
                        System.out.printf("P = %.3f",  number2);
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("V")) {
                        System.out.printf("V = %.3f ", number2);
                        System.out.printf("I = %.3f ", number2 / number1);
                        System.out.printf("R = %.3f ", number1);
                        System.out.printf("P = %.3f",  (number2 / number1) * number2);
                        System.out.println();
                        break;
                    }
                case "P":
                    if(value2.equals("V")) {
                        System.out.printf("V = %.3f ", number2);
                        System.out.printf("I = %.3f ", number1 / number2);
                        System.out.printf("R = %.3f ", number2 / (number1 / number2));
                        System.out.printf("P = %.3f",  number1);
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("I")) {
                        System.out.printf("V = %.3f ", number1 / number2);
                        System.out.printf("I = %.3f ", number2);
                        System.out.printf("R = %.3f ", (number1 / number2) / number2);
                        System.out.printf("P = %.3f",  number1);
                        System.out.println();
                        break;
                    }
                    else if(value2.equals("R")) {
                        System.out.printf("V = %.3f ", Math.sqrt(number1 * number2));
                        System.out.printf("I = %.3f ", Math.sqrt(number1 / number2));
                        System.out.printf("R = %.3f ", number2);
                        System.out.printf("P = %.3f",  number1);
                        System.out.println();
                        break;
                    }
            }
        }
    }
}
