import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//this program prints out a musical scale given its starting note and a list of numbers
//representing the progression of the scale
//Problem #6 in UIL December 2016 Packet

public class Scale {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("scale.dat"));
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();
        ArrayList<String> notesTop = new ArrayList<String>();
        ArrayList<String> notesBottom = new ArrayList<String>();
        int[] order = {2,2,1,2,2,2,1};
        notesTop.add("A");
        notesTop.add("A#");
        notesTop.add("B");
        notesTop.add("C");
        notesTop.add("C#");
        notesTop.add("D");
        notesTop.add("D#");
        notesTop.add("E");
        notesTop.add("F");
        notesTop.add("F#");
        notesTop.add("G");
        notesTop.add("G#");
        notesBottom.add("A");
        notesBottom.add("Bb");
        notesBottom.add("B");
        notesBottom.add("C");
        notesBottom.add("Db");
        notesBottom.add("D");
        notesBottom.add("Eb");
        notesBottom.add("E");
        notesBottom.add("F");
        notesBottom.add("Gb");
        notesBottom.add("G");
        notesBottom.add("Ab");
        for(int i=0; i< numberOfDataSets; i++) {
            String startingNote = scan.nextLine();
            int numberOfNotesPrinted = 0;
            int currentIndex = notesTop.indexOf(startingNote);
            int currentIndexOrder = 0;

            if(startingNote.equals("G") || startingNote.equals("D") || startingNote.equals("A") || startingNote.equals("E") ||
                    startingNote.equals("B") || startingNote.equals("F#")) {
                //notes top
                System.out.print(startingNote + " ");
                currentIndex += order[currentIndexOrder];

                while(numberOfNotesPrinted < 7) {
                    if(currentIndex > notesTop.size()-1) {
                        currentIndex = 0;
                    }
                    System.out.print(notesTop.get(currentIndex) + " ");
                    currentIndexOrder++;
                    if(currentIndexOrder < order.length) {
                        currentIndex += order[currentIndexOrder];
                    }
                    numberOfNotesPrinted++;
                }
            }
            else if(startingNote.equals("F") || startingNote.equals("Bb") || startingNote.equals("Eb") || startingNote.equals("Ab") ||
                    startingNote.equals("Gb")) {
                //notes bottom
                System.out.print(startingNote + " ");
                currentIndex += order[currentIndexOrder];

                while(numberOfNotesPrinted < 7) {
                    if(currentIndex > notesBottom.size()-1) {
                        currentIndex = 0;
                    }
                    System.out.print(notesBottom.get(currentIndex) + " ");
                    currentIndexOrder++;
                    if(currentIndexOrder < order.length) {
                        currentIndex += order[currentIndexOrder];
                    }
                    numberOfNotesPrinted++;
                }
            }

            else {
                //either
                System.out.print(startingNote + " ");
                currentIndex += order[currentIndexOrder];

                while(numberOfNotesPrinted < 7) {
                    if(currentIndex > notesTop.size()-1) {
                        currentIndex = 0;
                    }
                    System.out.print(notesTop.get(currentIndex) + " ");
                    currentIndexOrder++;
                    if(currentIndexOrder < order.length) {
                        currentIndex += order[currentIndexOrder];
                    }
                    numberOfNotesPrinted++;
                }
            }
            System.out.println();
        }
    }
}