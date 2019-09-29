import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* This program formats a string for output given a line of input. Given a military
 * members' name, rank, and serial number, output a String as specified in problem
 * directions (see problem #5 on A+ January 1 2016 file for a further description
 * of problem). The inputs for these problems are in the form of data files.
 * */

class Person {
    private String name;
    private String rank;
    private int serialNumber;

    public Person() {
        name = "";
        rank = "";
        serialNumber = 0;
    }

    public Person(String name, String rank, int serialNumber) {
        this.name = name;
        this.rank = rank;
        this.serialNumber = serialNumber;
    }

    public String getName() { return name; }
    public String getRank() { return rank; }
    public int getSerialNumber() { return serialNumber; }

    public void setName(String name) { this.name = name; }
    public void setRank(String rank) { this.rank = rank; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }

    public String turnSerialNumberIntoWords(int serialNumber) {
        String word_number = "";
        String number = Integer.toString(serialNumber);
        for(int i=0; i<number.length(); i++) {
            word_number += " " + turnNumberIntoWords(Integer.parseInt(number.substring(i,i+1)));
        }
        return word_number;
    }

    public String turnNumberIntoWords(int number) {
        switch(number) {
            case 0: return "zero";
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            case 7: return "seven";
            case 8: return "eight";
            case 9: return "nine";
            default: return "";
        }
    }
}
public class NRS {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("nrs.dat"));
        String name = "";
        String rank = "";
        int serialNumber = 0;
        Person person = new Person(name, rank, serialNumber);
        while(scan.hasNext()) {
            name = scan.next() + " " + scan.next();
            person.setName(name);
            rank = scan.next();
            person.setRank(rank);
            serialNumber = Integer.parseInt(scan.next());
            person.setSerialNumber(serialNumber);
            System.out.println("My name is " + person.getName() + ", " + person.getRank()
            + ",");
            System.out.println("serial number" + person.turnSerialNumberIntoWords(person.getSerialNumber()) + "!");
        }
    }
}
