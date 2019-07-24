package com.example.geeth.physics_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    //#d899f9
    //#ffe600
    String[] topics;
    ListView topicsList;
    EditText searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate objects
        topicsList = (ListView) findViewById(R.id.topicsList);
        topics = getResources().getStringArray(R.array.topic_array);
        searchField = (EditText) findViewById(R.id.search_edit_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics); //initializing ArrayAdapter
        topicsList.setAdapter(adapter); //sets adapter as the ArrayAdapter for listView
        topicsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " was clicked", Toast.LENGTH_LONG).show();
                Intent topicListIntent = new Intent(view.getContext(), TopicList.class);
                String topic = parent.getItemAtPosition(position).toString();
                topicListIntent.putExtra("topic", topic); //passing the name of the topic from the main screen to the topic list screen
                startActivity(topicListIntent);
            }
        });


    }

    /*public void pullUpTerm(View view) throws IOException {
        String search = searchField.getText().toString();
        search = formatSearch(search);
        //Toast.makeText(getBaseContext(), search, Toast.LENGTH_LONG).show();
        DataInputStream searchInputStream = new DataInputStream(getAssets().open("terms_topics"));
        Scanner searchScan = new Scanner(searchInputStream);
        if(termListContainsSearch(search, searchScan)) {
            Intent termIntent = new Intent(this, TermPage.class);
            termIntent.putExtra("search", search);
            startActivity(termIntent);
        }
        else {
            Intent searchIntent = new Intent(this, SearchPage.class);
            searchIntent.putExtra("search", search);
            searchIntent.putExtra("searchTerms", findTermsThatContainSearch(search,searchScan));
            startActivity(searchIntent);
        }

        if(searchField.getText().toString().equals("")) {
            Intent termIntent = new Intent(this, TermPage.class);
            startActivity(termIntent);
        }
        else {
            Intent searchIntent = new Intent(this, SearchPage.class);
            startActivity(searchIntent);
        }

    }*/

    public void pullUpTerm(View view) {
        Intent termIntent = new Intent(this, TermPage.class);
        String search = searchField.getText().toString();
        termIntent.putExtra("search", search);
        startActivity(termIntent);
    }
}