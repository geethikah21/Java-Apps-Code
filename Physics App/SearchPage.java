package com.example.geeth.physics_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchPage extends AppCompatActivity {

    ListView searchOptions;
    Search search = new Search();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        searchOptions = (ListView) findViewById(R.id.search_options);
        //search.setSearch(getIntent().getStringExtra("search"));
        //search.setRelatedSearchTerms(getIntent().getStringExtra("searchTerms"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, search.splitSearchTerms(search.getRelatedSearchTerms())); //initializing ArrayAdapter
        searchOptions.setAdapter(adapter); //sets adapter as the ArrayAdapter for listView
        searchOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " was clicked", Toast.LENGTH_LONG).show();
                //Intent topicListIntent = new Intent(view.getContext(), TopicList.class);
                //String topic = parent.getItemAtPosition(position).toString();
                //topicListIntent.putExtra("topic", topic); //passing the name of the topic from the main screen to the topic list screen
                //startActivity(topicListIntent);
            }
        });
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
