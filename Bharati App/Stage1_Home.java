package com.example.learnbharati;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Stage1_Home extends AppCompatActivity {
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1_home);
        language = getIntent().getStringExtra("language");
    }

    public void goToPageVowel(View view) {
        Intent mainS1IntentV = new Intent(this, Stage1_Main.class);
        mainS1IntentV.putExtra("type", "vowel");
        mainS1IntentV.putExtra("language", language);
        startActivity(mainS1IntentV);
    }

    public void goToPageConsonant(View view) {
        Intent mainS1IntentC = new Intent(this, Stage1_Main.class);
        mainS1IntentC.putExtra("type", "consonant");
        mainS1IntentC.putExtra("language", language);
        startActivity(mainS1IntentC);
    }

    public void goToPageVowelConsonant(View view) {
        Intent mainS1IntentVC = new Intent(this, Stage1_Main.class);
        mainS1IntentVC.putExtra("type", "vowel_cons");
        mainS1IntentVC.putExtra("language", language);
        startActivity(mainS1IntentVC);
    }

    public void goToMainPage(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
