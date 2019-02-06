package com.example.geeth.learnbharati;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner languageSpinner = (Spinner) findViewById(R.id.languageSpinner); // new spinner
        languages = getResources().getStringArray(R.array.languagesArray);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages); //array adapter to get data and convert it into view objects
        languageSpinner.setAdapter(adapter); //setting adapter as the ArrayAdapter for spinner c2
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) { //when an item is selected, this method is called
                int index = arg0.getSelectedItemPosition(); //getting position number in spinner
                Toast.makeText(getBaseContext(), "You have selected item : " + languages[index], Toast.LENGTH_SHORT).show(); //Toast message to let user know which item is selected

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) { //when nothing is selected
            }
        });

    }

    public void goToStage1Home(View view) {
        Intent stage1 = new Intent(this, Stage1_Home.class);
        startActivity(stage1);
    }
}
