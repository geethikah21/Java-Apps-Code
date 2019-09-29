 public class English extends Term {
    private String pronunciation;

    public English(String name, String definition, String pronunciation) {
        super(name, definition);
        this.pronunciation = pronunciation;
    }

    public String getPronunciation() { return pronunciation; }

    public void setPronunciation(String pronunciation) {this.pronunciation = pronunciation;}

    public String createExampleSentence(String sentence) {
        return sentence + ".";
    }
}