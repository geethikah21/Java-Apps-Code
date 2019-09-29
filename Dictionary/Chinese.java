 public class Chinese extends Term {
    private String pinyin;

    public Chinese(String name, String definition, String pinyin) {
        super(name, definition);
        this.pinyin = pinyin;
    }

    public String getPinyin() { return pinyin; }

    public void setPinyin(String pinyin) {this.pinyin = pinyin; }

     public String createExampleSentence(String sentence) {
         return sentence + "。";
     }

}
