package com.example.geeth.learnbharati;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage2_Main extends AppCompatActivity {
    String language;
    String type;

    Button audioButton;
    Button choice1;
    Button choice2;
    Button choice3;
    Button choice4;

    Typeface tamil_font;
    Typeface bharati_font;

    ArrayList<Character> characters;
    ArrayList<Character> subsetOfCharactersToUse;

    MediaPlayer sound;

    Button[] choiceButtons;
    int[] randomNums;

    int answerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2__main);

        language = getIntent().getStringExtra("language");
        type = getIntent().getStringExtra("type");

        audioButton = (Button) findViewById(R.id.audioButton);
        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        choice4 = (Button) findViewById(R.id.choice4);

        bharati_font = Typeface.createFromAsset(getAssets(), "fonts/SundarBharati-Regular.otf");
        tamil_font = Typeface.createFromAsset(getAssets(), "fonts/baamini.ttf");

        choiceButtons = new Button[] {choice1, choice2, choice3, choice4};
        characters = new ArrayList<Character>();

        if(language.equals("Tamil")) {
            characters.add(new Character("அ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஆ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("இ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஈ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("உ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஊ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("எ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஏ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஐ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஒ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஓ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஔ", "vowel", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("க", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ங", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஜ", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ஞ", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ட", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ண", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ப", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("த", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ந", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ம", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ய", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ர", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ல", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("வ", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ழ", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ள", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ற", "consonant", sound, R.raw.ding, Stage2_Main.this));
            characters.add(new Character("ன", "consonant", sound, R.raw.ding, Stage2_Main.this));
        }
        else {
            characters.add(new Character("அ", "vowel", sound, R.raw.ding, Stage2_Main.this));
        }

        createSounds(characters);

        subsetOfCharactersToUse = getSubsetOfCharacters(type);

        randomNums = pickFourRandomNumbers(subsetOfCharactersToUse.size()-1);

        answerIndex = (int)(Math.random()*(randomNums.length-1));

        for(int i=0; i<choiceButtons.length; i++) {
            choiceButtons[i].setTypeface(bharati_font);
            choiceButtons[i].setText(subsetOfCharactersToUse.get(randomNums[i]).getLetter());
        }

        Toast.makeText(getBaseContext(), "Answer index: " + answerIndex, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Character> getSubsetOfCharacters(String type) {
        ArrayList<Character> subset = new ArrayList<Character>();

        for(int i=0; i<characters.size(); i++) {
            if(characters.get(i).getType().equals(type)) {
                subset.add(characters.get(i));
            }
        }

        return subset;
    }

    public int[] pickFourRandomNumbers(int largestNumber) {
        int[] randomNumbers = new int[4];

        for(int i=0; i<randomNumbers.length; i++) {
            randomNumbers[i] = (int)(Math.random()*largestNumber);
            for(int j=0; j<i; j++) {
                if(randomNumbers[j]==randomNumbers[i]) {
                    randomNumbers[i] = (int)(Math.random()*largestNumber);
                }
            }
        }

        return randomNumbers;
    }

    public ArrayList<Character> createSounds(ArrayList<Character> letters) {

        for(int i=0; i<letters.size(); i++) {
            letters.get(i).createSound();
        }

        return letters;
    }

    public void choice1Clicked(View view) {
        Toast.makeText(getBaseContext(), randomNums[0]+"", Toast.LENGTH_SHORT).show();
        if(answerIndex == 0) {
            choice1.setBackgroundColor(Color.GREEN);
        }
        else {
            choice1.setBackgroundColor(Color.RED);
        }
    }

    public void choice2Clicked(View view) {
        Toast.makeText(getBaseContext(), randomNums[1]+"", Toast.LENGTH_SHORT).show();
        if(answerIndex == 1) {
            choice2.setBackgroundColor(Color.GREEN);
        }
        else {
            choice2.setBackgroundColor(Color.RED);
        }
    }

    public void choice3Clicked(View view) {
        Toast.makeText(getBaseContext(), randomNums[2]+"", Toast.LENGTH_SHORT).show();
        if(answerIndex == 2) {
            choice3.setBackgroundColor(Color.GREEN);
        }
        else {
            choice3.setBackgroundColor(Color.RED);
        }
    }

    public void choice4Clicked(View view) {
        Toast.makeText(getBaseContext(), randomNums[3]+"", Toast.LENGTH_SHORT).show();
        if(answerIndex == 3) {
            choice4.setBackgroundColor(Color.GREEN);
        }
        else {
            choice4.setBackgroundColor(Color.RED);
        }
    }

    public void playAudio(View view) {
        subsetOfCharactersToUse.get(randomNums[answerIndex]).getSound().start();
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}