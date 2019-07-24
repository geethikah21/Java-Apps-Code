package com.example.geeth.physics_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchPage extends AppCompatActivity {

    ListView searchOptions;
    TextView errorField;
    Search search = new Search();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        searchOptions = (ListView) findViewById(R.id.search_options);
        errorField = (TextView) findViewById(R.id.errorField);
        search.setSearch(getIntent().getStringExtra("search"));
        search.setRelatedSearchTerms(getIntent().getStringExtra("searchTerms"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, search.splitSearchTerms(search.getRelatedSearchTerms())); //initializing ArrayAdapter
        searchOptions.setAdapter(adapter); //sets adapter as the ArrayAdapter for listView
        searchOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " was clicked", Toast.LENGTH_LONG).show();
                Intent termPageIntent = new Intent(view.getContext(), TermPage.class);
                String term = parent.getItemAtPosition(position).toString();
                termPageIntent.putExtra("termFromSearchPage", term); //passing the name of the term from the search page to the term page screen
                startActivity(termPageIntent);
            }
        });

        if(search.getRelatedSearchTerms().equals("")) {
            errorField.setText("Sorry, no terms were similar to your search.");
        }
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}