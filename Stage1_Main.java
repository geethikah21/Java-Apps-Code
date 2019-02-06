package com.example.geeth.learnbharati;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage1_Main extends AppCompatActivity {

    //instantiating element objects
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
    Button[] characterButtons;
    String type;
    Typeface bharati_font;

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
        characterButtons = new Button[] {button, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13};
        type = getIntent().getStringExtra("type");
        bharati_font = Typeface.createFromAsset(getAssets(), "NavBharati.ttf");

        try {
            DataInputStream textFileStream2 = new DataInputStream(getAssets().open("tamil_bharati"));
            Scanner scan = new Scanner(textFileStream2);
            ArrayList<String> characters = getCharacters(type, scan);
            loadCharacters(characters);
            //characterButtons[0].setTypeface(Typeface.DEFAULT);
            //characterButtons[0].setText(scan.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            Toast.makeText(getBaseContext(), type, Toast.LENGTH_SHORT).show(); //Toast message to let user know which item is selected
            loadCharacters();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Hi", Toast.LENGTH_SHORT).show(); //Toast message to let user know which item is selected
            e.printStackTrace();
        }*/
    }

    public void loadCharacters(ArrayList<String> characters) throws IOException {
        for(int i=0; i<characterButtons.length; i++) {
            //characterButtons[i].setText(characters.get(i));
            characterButtons[i].setTypeface(bharati_font);
            button.setTypeface(bharati_font);
            characterButtons[i].setText(characters.get(i));
            Toast.makeText(getBaseContext(), characterButtons[0].getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<String> getCharacters(String type, Scanner scan) throws IOException {
        ArrayList<String> characters = new ArrayList<String>();
        ArrayList<String> vowelConsonant = new ArrayList<String>();

        while(scan.hasNext()) {
            characters.add(scan.next());
            Toast.makeText(getBaseContext(), characters.get(0), Toast.LENGTH_SHORT).show();
        }

        /*if(type.equals("vowel")) { //0-11
            for(int j=0; j<12; j++) {
                vowelConsonant.add(characters.get(j));
            }
        }
        else if(type.equals("consonant")) { //12-26
            for(int k=12; k<26; k++) {
                vowelConsonant.add(characters.get(k));
            }
        }
        else {

        }*/
        return characters;
    }

    //public ArrayList<Button> addButtons(int numberOfButtons)
}