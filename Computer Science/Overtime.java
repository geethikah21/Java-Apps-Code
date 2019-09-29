import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* This program determines the total gross pay at a job for a week, given a line
   of 7 integers representing the number of hours worked for each day of the week.
   Some days are given a bonus, and there is a given hourly rate (see problem #6
   in A+ January 2016 file for further description of problem). The inputs for these
   problems are in the form of data files.
 */

public class Overtime {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> hours = new ArrayList<Integer>();
        Scanner scan = new Scanner(new File("overtime.dat"));
        double pay = 0.0;
        double totalPay = 0.0;
        int totalHours = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner scanner = new Scanner(line);
            while(scanner.hasNextInt()) {
                hours.add(scanner.nextInt());
            }
            for(int i=0; i<hours.size(); i++) {
                int hour = hours.get(i);
                totalHours += hour;
                pay += hour * 10;
                if(hour > 8) {
                    pay += (hour - 8) * 1.5;
                }
                if(i == 6) {
                    pay += 1.25 * pay;
                }
                if(i == 0) {
                    pay += 0.5 * pay;
                }
                totalPay += pay;
                pay = 0;
            }
            if(totalHours > 40) {
                totalPay += (totalHours - 40) * 2.50;
            }
            System.out.printf("$%.2f", Double.parseDouble(formatPay(totalPay)));
            System.out.println();
            totalPay = 0;
            totalHours = 0;
            hours.clear();
        }
    }

    public static String formatPay(double pay) {
        String payFormat = "";
        String payOrig = ""+pay;
        payFormat+=pay;
        if(payOrig.substring(payOrig.indexOf(".")+1).length() < 2) {
            payFormat+="0";
        }
        return payFormat;
    }
}
