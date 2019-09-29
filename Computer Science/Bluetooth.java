import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//this program takes an encoded string of characters and decodes it;
//two consecutive numbers represent a character in hexadecimal
//this hexadecimal string is converted to base ten and this number
//represents the ASCII value of the decoded character
//Problem #12 in UIL December 2016 Packet
class SpeakerName {

    private String encodedString;
    private String decodedString;

    public SpeakerName() {
        encodedString = "";
        decodedString = "";
    }

    public SpeakerName(String encodedString, String decodedString) {
        this.encodedString = encodedString;
        this.decodedString = decodedString;
    }

    public String getEncodedString() { return encodedString; }
    public String getDecodedString() { return decodedString; }
    public void setEncodedString(String encodedString) { this.encodedString = encodedString;}
    public void setDecodedString(String decodedString) { this.decodedString = decodedString;}

    public void decodeString(String hex) {
        int baseTenNumber = getBaseTenNumber(hex);
        char character = (char) baseTenNumber;
        decodedString += character;
    }

    public int getBaseTenNumber(String hex) {
        int number = 0;
        int currentPosition = hex.length()-1;
        for(int i=0; i<hex.length(); i++) {
            number += Math.pow(16, currentPosition) * getHexNumberInIndividualPosition(hex.substring(i,i+1));
            currentPosition--;
        }
        return number;
    }

    public int getHexNumberInIndividualPosition(String hex) {
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
            case "a":
            case "A":
                return 10;
            case "b":
            case "B":
                return 11;
            case "c":
            case "C":
                return 12;
            case "d":
            case "D":
                return 13;
            case "e":
            case "E":
                return 14;
            case "f":
            case "F":
                return 15;
            default: return 0;
        }
    }
}

public class Bluetooth {
    public static void main(String[] args) throws FileNotFoundException {
        SpeakerName speaker = new SpeakerName("", "");
        Scanner scan = new Scanner(new File("bluetooth.dat"));
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<numberOfDataSets; i++) {
            speaker.setEncodedString(scan.nextLine());
            for(int j=0; j<speaker.getEncodedString().length(); j+=2) {
                String currentStringToEndcode = speaker.getEncodedString().substring(j,j+2);
                speaker.decodeString(currentStringToEndcode);
            }
            System.out.println(speaker.getDecodedString());
            speaker.setDecodedString("");
        }
    }
}