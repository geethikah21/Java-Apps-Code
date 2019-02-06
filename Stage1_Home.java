package com.example.geeth.learnbharati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Stage1_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1_home);
    }

    public void goToPageVowel(View view) {
        Intent mainS1IntentV = new Intent(this, Stage1_Main.class);
        mainS1IntentV.putExtra("type", "vowel");
        startActivity(mainS1IntentV);
    }

    public void goToPageConsonant(View view) {
        Intent mainS1IntentC = new Intent(this, Stage1_Main.class);
        mainS1IntentC.putExtra("type", "consonant");
        startActivity(mainS1IntentC);
    }

    public void goToPageVowelConsonant(View view) {
        Intent mainS1IntentVC = new Intent(this, Stage1_Main.class);
        mainS1IntentVC.putExtra("type", "vowel_cons");
        startActivity(mainS1IntentVC);
    }
}
