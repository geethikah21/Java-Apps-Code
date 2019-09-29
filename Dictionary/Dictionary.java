import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {


    public static void main(String[] args) throws IOException, FontFormatException {
        Scanner scan_chinese = new Scanner(new File("Chinese.dat"));
        Scanner scan_english = new Scanner(new File("English.dat"));
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Term> chineseTerms = new ArrayList<Term>();
        ArrayList<Term> englishTerms = new ArrayList<Term>();

        loadChineseTerms(scan_chinese, chineseTerms);
        loadEnglishTerms(scan_english, englishTerms);

        String lookAgain;
        do {
            query(keyboard, englishTerms, chineseTerms);
            System.out.println("Do you want to look up another term? Y/N");
            lookAgain = keyboard.nextLine();
        } while(lookAgain.equals("Y") || lookAgain.equals("y"));
    }

    public static void createFont(Font font) throws IOException, FontFormatException {
        font = Font.createFont(Font.TRUETYPE_FONT, new File("G5LISL1B.TTF")).deriveFont(12f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
    }

    public static void loadEnglishTerms(Scanner scan, ArrayList<Term> english) {
        int numberOfTerms = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<numberOfTerms; i++) {
            Term english_term = new English(scan.nextLine(), scan.nextLine(), scan.nextLine());
            english.add(english_term);
        }
    }

    public static void loadChineseTerms(Scanner scan, ArrayList<Term> chinese) {
        int numberOfTerms = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<numberOfTerms; i++) {
            Term chinese_term = new Chinese(scan.nextLine(), scan.nextLine(), scan.nextLine());
            chinese.add(chinese_term);
        }
    }

    public static int findIndexOfTermInOtherLanguageThatMatchesTerm(String term, String language, ArrayList<Term> english, ArrayList<Term> chinese) {
        if(language.equals("Chinese") || language.equals("chinese")) {
            for(int i=0; i<english.size(); i++) {
                if(term.equals(chinese.get(i).getName()) && english.get(i).getDefinition().equals(chinese.get(i).getDefinition())) {
                    return i;
                }
            }
        }
        else if(language.equals("English") || language.equals("english")) {
            for(int i=0; i<chinese.size(); i++) {
                if(term.equals(english.get(i).getName()) && chinese.get(i).getDefinition().equals(english.get(i).getDefinition())) {
                    return i;
                }
            }
        }
        else {
            System.out.println("can't find term because language was spelled incorrectly or isn't supported");
        }
        return -1;
    }

    public static void printChineseTerms(ArrayList<Term> chinese) {
        for(int i=0; i<chinese.size(); i++) {
            System.out.print(chinese.get(i).getName() + " " + chinese.get(i).getDefinition() + " " + ((Chinese)chinese.get(i)).getPinyin());
            System.out.println();
        }
    }

    public static void printEnglishTerms(ArrayList<Term> english) {
        for(int i=0; i<english.size(); i++) {
            System.out.print(english.get(i).getName() + " " + english.get(i).getDefinition() + " " + ((English)english.get(i)).getPronunciation());
            System.out.println();
        }
    }

    public static void query(Scanner keyboard, ArrayList<Term> englishTerms, ArrayList<Term> chineseTerms) {
        System.out.println("Enter term: ");
        String requestedTerm = keyboard.nextLine().toLowerCase();
        System.out.println("Enter language this term is in: ");
        String language = keyboard.nextLine();

        int index = findIndexOfTermInOtherLanguageThatMatchesTerm(requestedTerm, language, englishTerms, chineseTerms);

        if (language.equals("Chinese")) {
            System.out.println("In English, this term is: ");
            System.out.println("Name: " + englishTerms.get(index).getName());
            System.out.println("Definition: " + englishTerms.get(index).getDefinition());
            System.out.println("Pronunciation: " + ((English) englishTerms.get(index)).getPronunciation());
        }
        else {
            System.out.println("In Chinese, this term is: ");
            System.out.println("Character(s): " + chineseTerms.get(index).getName());
            System.out.println("Definition: " + chineseTerms.get(index).getDefinition());
            System.out.println("Pinyin: " + ((Chinese) chineseTerms.get(index)).getPinyin());
        }

        System.out.println("Do you want to create an example sentence? Y/N");
        String sentence = keyboard.nextLine();


        if(sentence.toLowerCase().equals("y")) {
            System.out.println("Enter sentence (without punctuation):");
            String example = keyboard.nextLine();
            System.out.println("Which language is this sentence in (English or Chinese)?");
            String languageSentence = keyboard.nextLine();

            if(languageSentence.equals("Chinese")) {
                System.out.println(chineseTerms.get(index).createExampleSentence(example));
            }
            else {
                System.out.println(englishTerms.get(index).createExampleSentence(example));
            }
        }
    }
}
