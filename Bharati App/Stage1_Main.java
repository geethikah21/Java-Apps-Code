package com.example.geeth.learnbharati;

import android.content.Intent;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Stage1_Main extends AppCompatActivity {

    //instantiating objects
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    Button button17;
    Button button18;
    Button[] characterButtons;
    TextView languageName;
    String type;
    String language;
    Typeface bharati_font;
    Typeface tamil_font;
    MediaPlayer sound;
    int numberOfTamilVowels = 12;
    int numberOfTamilConsonants = 18;
    ArrayList<Character> characters;
    ArrayList<Character> charactersToBeDisplayed;
    int pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1_main);
        //initializing
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        button17 = (Button) findViewById(R.id.button17);
        button18 = (Button) findViewById(R.id.button18);
        languageName = (TextView) findViewById(R.id.language_name);

        characterButtons = new Button[] {button, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, button17, button18};
        type = getIntent().getStringExtra("type");
        language = getIntent().getStringExtra("language");
        if(language == null) {
            language = "";
        }
        bharati_font = Typeface.createFromAsset(getAssets(), "fonts/NavBharati.ttf");
        tamil_font = Typeface.createFromAsset(getAssets(), "fonts/baamini.ttf");
        characters = new ArrayList<Character>();

        //Toast.makeText(getBaseContext(), language, Toast.LENGTH_SHORT).show();
        languageName.setText(language);

        //adding bharati characters
        //String letter, String type, MediaPlayer sound, int source, Context classToCreateSound
        if(language.equals("Tamil")) {
            characters.add(new Character("அ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஆ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("இ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஈ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("உ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஊ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("எ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஏ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஐ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஒ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஓ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஔ", "vowel", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("க", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ங", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஜ", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ஞ", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ட", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ண", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ப", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("த", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ந", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ம", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ய", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ர", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ல", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("வ", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ழ", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ள", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ற", "consonant", sound, R.raw.ding, Stage1_Main.this));
            characters.add(new Character("ன", "consonant", sound, R.raw.ding, Stage1_Main.this));
        }
        else {
            characters.add(new Character("அ", "vowel", sound, R.raw.ding, Stage1_Main.this));
        }

        createSounds(characters);

        try {
            charactersToBeDisplayed = getCharacters(characters, type);

            if(charactersToBeDisplayed.size() % 18 != 0) {
                padArrayListToMakeMultOfEighteen(18 - (charactersToBeDisplayed.size() % 18), type);
                Toast.makeText(getBaseContext(),"padding", Toast.LENGTH_SHORT).show();
            }


            //Toast.makeText(getBaseContext(),charactersToBeDisplayed.size(), Toast.LENGTH_SHORT).show();
            if(charactersToBeDisplayed.size() > 18) {
                loadCharacters(charactersToBeDisplayed, 0, 18);
                //need to click next button
            }
            else {
                loadCharacters(charactersToBeDisplayed, 0, charactersToBeDisplayed.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCharacters(ArrayList<Character> characters, int indexToStart, int indexToEndPlusOne) throws IOException {
        clearAllButtons();
        for(int i=indexToStart; i<indexToEndPlusOne; i++) {
            characterButtons[i-indexToStart].setTypeface(bharati_font);
            //characterButtons[i].setTypeface(tamil_font);
            //characterButtons[i].setTypeface(Typeface.DEFAULT);
            characterButtons[i-indexToStart].setText(characters.get(i).getLetter());
        }
    }

    public void padArrayListToMakeMultOfEighteen(int numberOfElements, String type) {
        for(int i=0; i<numberOfElements; i++) {
            charactersToBeDisplayed.add(new Character(" ", type, sound, R.raw.ding, Stage1_Main.this));
        }
    }

    public ArrayList<Character> getCharacters(ArrayList<Character> characters, String type) throws IOException {
        ArrayList<Character> vowelConsonant = new ArrayList<Character>();

        for(int i=0; i<characters.size(); i++) {
            if(characters.get(i).getType().equals(type))
                vowelConsonant.add(characters.get(i));
        }

        return vowelConsonant;
    }

    public int getIndexThatMatchesId(int id) {
        int index = -1;
        for(int i=0; i<characterButtons.length; i++) {
            if(id == characterButtons[i].getId()) {
                index = i;
            }
        }
        return index;
    }

    public void clearAllButtons() {
        for(int i=0; i<characterButtons.length; i++) {
            characterButtons[i].setText("");
        }
    }

    public ArrayList<Character> createSounds(ArrayList<Character> letters) {

        for(int i=0; i<letters.size(); i++) {
            letters.get(i).createSound();
        }

        return letters;
    }

    public void onClick(View view) throws IOException {
        Toast.makeText(getBaseContext(),getIndexThatMatchesId(view.getId())+"", Toast.LENGTH_SHORT).show();
        if(characters.get(getIndexThatMatchesId(view.getId())).getSound() == null) {
            Toast.makeText(getBaseContext(), "null", Toast.LENGTH_SHORT).show();
        }
        else {
            characters.get(getIndexThatMatchesId(view.getId())).getSound().start();
            Toast.makeText(getBaseContext(), "sound played", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getBaseContext(),characterButtons[3].getText(), Toast.LENGTH_SHORT).show();
    }

    public void next(View view) throws IOException {
        int maxPage = charactersToBeDisplayed.size() / 18;
        pageNumber++;
        if(pageNumber > maxPage-1) {
            pageNumber = pageNumber-1;
        }
        else {
            loadCharacters(charactersToBeDisplayed, pageNumber*18, (pageNumber+1)*18);
        }
        Toast.makeText(getBaseContext(), pageNumber+"", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) throws IOException {
        pageNumber--;
        if(pageNumber < 0) {
            pageNumber = 0;
        }
        else {
            loadCharacters(charactersToBeDisplayed, pageNumber*18, (pageNumber+1)*18);
        }
        Toast.makeText(getBaseContext(), pageNumber+"", Toast.LENGTH_SHORT).show();
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}