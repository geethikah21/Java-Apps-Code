import java.util.Scanner;
import java.io.*;

public class Lights {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("lights.dat"));

        int numberOfDataSets = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfDataSets; i++) {
            String sequence = scan.next();
            String newSeq = "";
            int numberOfOps = Integer.parseInt(scan.next());
            scan.nextLine();
            for(int j=0; j<numberOfOps; j++) {
                String op = scan.nextLine();
                switch(op) {
                    case "FLIP ALL":
                        for(int k=0; k<sequence.length(); k++) {
                            if(sequence.charAt(k) == '1') {
                                newSeq += '0';
                            }
                            else {
                                newSeq += '1';
                            }
                        }
                        break;
                }
            }
        }
    }
}
