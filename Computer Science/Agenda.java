/**
 * @(#)Agenda.java
 *
 *
 * @author
 * @version 1.00 2017/11/7
 */

import java.util.Scanner;
import java.io.*;

public class Agenda {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("agenda.dat"));
        int hours = 0;
        int minutes = 0;
        int endHour = 0;
        int endMinute = 0;
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfDataSets; i++) {
            int carSpeed = scan.nextInt();
            String startTime = scan.next() + " " + scan.next();
            //System.out.println(carSpeed);
            //System.out.println(startTime);
            scan.nextLine();

            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                if(line.substring(0,line.indexOf(",")).equals("GO HOME")) {
                    double number_home = Integer.parseInt(line.substring(line.lastIndexOf(" ")-2,line.lastIndexOf(" ")));
                    String milesOrMinutes_Home = line.substring(line.lastIndexOf(" ") + 1);
                    if(milesOrMinutes_Home.equals("MILES")) {
                        minutes += (number_home / carSpeed) * 60;
                    }
                    else {
                        minutes += number_home;
                    }
                    break;
                }
                else {
                    double number = Integer.parseInt(line.substring(line.lastIndexOf(" ")-2,line.lastIndexOf(" ")));
                    String milesOrMinutes = line.substring(line.lastIndexOf(" ") + 1);
                    if(milesOrMinutes.equals("MILES")) {
                        minutes += (number / carSpeed) * 60;
                    }
                    else {
                        minutes += number;
                    }
                }
            }
            hours = minutes / 60;
            minutes = minutes - (hours*60);
            endHour = Integer.parseInt(startTime.substring(0,2)) + hours;
            endMinute = Integer.parseInt(startTime.substring(3,5)) + minutes;
            if(endHour < 12) {
                if(Integer.toString(endHour).length() == 1) {
                    System.out.println("Joe will arrive home at " + "0" + endHour + ":" + endMinute +  " " + startTime.substring(6));
                }
                else {
                    System.out.println("Joe will arrive home at " + endHour + ":" + endMinute + " " + startTime.substring(6));
                }
            }
            else if(endHour > 12) {
                endHour -= 12;
                if(startTime.substring(6).equals("AM")) {
                    if(Integer.toString(endHour).length() == 1) {
                        System.out.println("Joe will arrive home at " + "0" + endHour + ":" + endMinute + " PM");
                    }
                    else {
                        System.out.println("Joe will arrive home at " + endHour + ":" + endMinute + " PM");
                    }
                }
                else {
                    if(Integer.toString(endHour).length() == 2) {
                        System.out.println("Joe will arrive home at " + "0" + endHour + ":" + endMinute + " AM");
                    }
                    else {
                        System.out.println("Joe will arrive home at " + endHour + ":" + endMinute + " AM");
                    }
                }
            }
            minutes = 0;

        }
    }
}