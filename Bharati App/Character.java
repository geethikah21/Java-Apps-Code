package com.example.geeth.learnbharati;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;

import com.example.geeth.learnbharati.R;
import com.example.geeth.learnbharati.Stage1_Main;

import java.util.Locale;

/**
 * Created by geeth on 1/9/2019.
 */
 public class Character
{
    private String letter;
    private String type;
    private MediaPlayer sound;
    private int source;
    private Context classToCreateSound;

    public Character() {
        letter = "";
        type = "";
        sound = null;
        source = 0;
        classToCreateSound = null;
    }

    public Character(String letter, String type, MediaPlayer sound, int source, Context classToCreateSound) {
        this.letter = letter;
        this.type = type;
        this.sound = sound;
        this.source = source;
        this.classToCreateSound = classToCreateSound;
    }

    public void createSound() {
        sound = MediaPlayer.create(classToCreateSound,source);
    }

    public String getLetter() { return letter; }
    public MediaPlayer getSound() { return sound; }
    public void setLetter(String letter) { this.letter = letter; }
    public void setSound(MediaPlayer sound) { this.sound = sound; }
    public String getType() { return type; }
    public void setType(String type) {this.type = type;}
}