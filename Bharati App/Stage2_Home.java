package com.example.geeth.learnbharati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Stage2_Home extends AppCompatActivity {
    Button vowelButton;
    Button consButton;
    Button vowel_consButton;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2__home);

        language = getIntent().getStringExtra("language");

        vowelButton = (Button) findViewById(R.id.vowelButton);
        consButton = (Button) findViewById(R.id.consButton);
        vowel_consButton = (Button) findViewById(R.id.vowel_cons_button);
    }

    public void goToPageVowel(View view) {
        Intent mainS2IntentV = new Intent(this, Stage2_Main.class);
        mainS2IntentV.putExtra("type", "vowel");
        mainS2IntentV.putExtra("language", language);
        startActivity(mainS2IntentV);
    }

    public void goToPageConsonant(View view) {
        Intent mainS2IntentC = new Intent(this, Stage2_Main.class);
        mainS2IntentC.putExtra("type", "consonant");
        mainS2IntentC.putExtra("language", language);
        startActivity(mainS2IntentC);
    }

    public void goToPageVowelConsonant(View view) {
        Intent mainS2IntentVC = new Intent(this, Stage2_Main.class);
        mainS2IntentVC.putExtra("type", "vowel_cons");
        mainS2IntentVC.putExtra("language", language);
        startActivity(mainS2IntentVC);
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
