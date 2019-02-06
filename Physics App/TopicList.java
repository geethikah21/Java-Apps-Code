package com.example.geeth.physics_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TopicList extends AppCompatActivity {
    TextView topicName;
    ListView termList;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<Term> termInfo = new ArrayList<Term>();
    String fileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        //instantiate objects
        topicName = (TextView) findViewById(R.id.topicName);
        termList = (ListView) findViewById(R.id.termList);

        String topic = getIntent().getStringExtra("topic");
        //the main activity is passing a string representing the topic selected to this string variable (above)
        topicName.setText(topic);

        if(topicName.getText().toString().equals("Electricity and Magnetism")) {
            fileName = "electricity_magnetism";
        }
        else if(topicName.getText().toString().equals("Fluids")) {
            fileName = "fluids";
        }
        else if(topicName.getText().toString().equals("Forces")) {
            fileName = "forces";
        }
        else if(topicName.getText().toString().equals("Motion")) {
            fileName = "motion";
        }
        else if(topicName.getText().toString().equals("Thermodynamics")) {
            fileName = "thermo";
        }
        else if(topicName.getText().toString().equals("Waves and Sound")) {
            fileName = "waves_sound";
        }
        else if(topicName.getText().toString().equals("Work and Energy")){
            fileName = "work_energy";
        }
        else if(topicName.getText().toString().equals("Modern Physics")) {
            fileName = "modern";
        }

        try {
            DataInputStream textFileStream = new DataInputStream(getAssets().open(fileName));
            Scanner scan = new Scanner(textFileStream);
            loadTerms(scan, topic);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names); //initializing ArrayAdapter
            termList.setAdapter(adapter); //sets adapter as the ArrayAdapter for listView
            termList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                    Intent termIntentFromTopic = new Intent(view.getContext(), TermPage.class);
                    String term = parent.getItemAtPosition(position).toString();
                    termIntentFromTopic.putExtra("term", term);
                    //termIntentFromTopic.putStringArrayListExtra("names", names);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("termInfo", termInfo); //passing the data structure with term info to the term page
                    termIntentFromTopic.putExtras(bundle);
                    startActivity(termIntentFromTopic);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(),"hi", Toast.LENGTH_LONG).show();
        }
    }

    public void loadTerms(Scanner scan, String topic) {
        int numberOfLines = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<numberOfLines/3; i++) {
            String name = scan.nextLine();
            String definition = scan.nextLine();
            String related = scan.nextLine();
            termInfo.add(new Term(name, definition, related, topic));
            names.add(termInfo.get(termInfo.size()-1).getTermName());
        }
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
