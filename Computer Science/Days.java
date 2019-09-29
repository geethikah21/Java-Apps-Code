import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This program determines the amount of days that have gone by between two given
   days in the same year (see problem #1 in A+ January 2016 file for further
   description of problem). The inputs for these problems are in the form of data files.
 */

class Day {
    private int month;
    private int day;
    private int year;

    public Day() {
        month = 0;
        day = 0;
        year = 0;
    }

    public Day(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public int getMonth() { return month; }

    public int getDay() { return day; }

    public int getYear() { return year; }

    public void setMonth(int month) {this.month = month;}

    public void setDay(int day) {this.day = day;}

    public void setYear(int year) {this.year = year;}

    public String formatDate(int month, int day, int year) {
        String formattedYear = ""+year;
        String formattedMonth = "";
        String formattedDay = "";
        if(month < 10) {
            formattedMonth = "0"+month;
        }
        else {
            formattedMonth = ""+month;
        }

        if(day < 10) {
            formattedDay = "0"+day;
        }
        else {
            formattedDay = ""+day;
        }

        return formattedYear + "-" + formattedMonth + "-" + formattedDay;
    }

}
public class Days {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("days.dat"));
        Day day1 = null;
        Day day2 = null;
        int numberOfDays = 0;
        while(scan.hasNextLine()) {

            //day1
            String line1 = scan.nextLine();
            Scanner scanner1 = new Scanner(line1);
            if(day1 == null) {
                day1 = new Day(scanner1.nextInt(), scanner1.nextInt(), scanner1.nextInt());
            }
            else {
                day1.setMonth(scanner1.nextInt());
                day1.setDay(scanner1.nextInt());
                day1.setYear(scanner1.nextInt());
            }

            //day 2
            String line2 = scan.nextLine();
            Scanner scanner2 = new Scanner(line2);
            if(day2 == null) {
                day2 = new Day(scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt());
            }
            else {
                day2.setMonth(scanner2.nextInt());
                day2.setDay(scanner2.nextInt());
                day2.setYear(scanner2.nextInt());
            }

            if(day1.getMonth() == day2.getMonth()) {
                numberOfDays = day2.getDay() - day1.getDay();
            }
            else {
                for(int i=day1.getMonth(); i<=day2.getMonth(); i++) {
                    int startIndex = 0;
                    int endIndex = 0;
                    if(i == day1.getMonth()) {
                        startIndex = day1.getDay();
                    }
                    else {
                        startIndex = 1;
                    }
                    if(i == day2.getMonth()) {
                        endIndex = day2.getDay();
                    }
                    else {
                        endIndex = numberOfDaysInMonth(i, day1.getYear());
                    }

                    if(i == day2.getMonth()) {
                        numberOfDays += (endIndex - startIndex);
                    }
                    else {
                        numberOfDays += (endIndex - startIndex + 1);
                    }
                }
            }

            System.out.println("There are " + numberOfDays + " days gone by from " +
                    day1.formatDate(day1.getMonth(), day1.getDay(), day1.getYear()) + " to " +
                        day2.formatDate(day2.getMonth(), day2.getDay(), day2.getYear()) + ".");
            //System.out.println("End Set");
            numberOfDays = 0;
        }
    }
    public static int numberOfDaysInMonth(int month, int year) {
        switch(month) {
            case 1: return 31;
            case 2:
                if(year % 4 == 0) {
                    return 29;
                }
                else {
                    return 28;
                }
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            default: return 0;
        }
    }
}