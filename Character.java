import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by geeth on 1/9/2019.
 */
public class Character
{
    private String letter;
    private TextToSpeech sound;

    public Character(String letter, TextToSpeech sound) {
        this.letter = letter;
        this.sound = sound;
    }

    public String getLetter() { return letter; }
    public TextToSpeech getSound() { return sound; }
    public void setLetter(String letter) { this.letter = letter; }
    public void setSound(TextToSpeech sound) { this.sound = sound; }
    public TextToSpeech playSound(TextToSpeech sound) {
        sound.setLanguage(Locale.ENGLISH);
        sound.addSpeech(letter, "tamil");
        sound.playEarcon(letter, );
    }
}
