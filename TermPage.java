package com.example.geeth.physics_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TermPage extends AppCompatActivity {
    TextView termNameFromTopic;
    TextView termNameFromSearch;
    TextView definitionBody;
    ListView relatedList;
    String termInput;
    String search;
    Term term = new Term();
    ArrayList<Term> termInfoTermPage;
    String[] relatedTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_page);

        //instantiate objects
        termNameFromTopic = (TextView) findViewById(R.id.termNameFromTopic);
        termNameFromSearch = (TextView) findViewById(R.id.termNameFromSearch);
        definitionBody = (TextView) findViewById(R.id.definition_body);
        relatedList = (ListView) findViewById(R.id.relatedList);

        Intent receivingIntent = this.getIntent();
        Bundle bundle = receivingIntent.getExtras();
        //termInfoTermPage = (ArrayList<Term>)(bundle.getSerializable("termInfo"));

        if(termInfoTermPage == null) {
            termInfoTermPage = new ArrayList<Term>();
        }

        termNameFromTopic.setText(getIntent().getStringExtra("term"));
        termNameFromSearch.setText(getIntent().getStringExtra("search"));

        if(!termNameFromSearch.getText().toString().equals("")) {
            termInput = termNameFromSearch.getText().toString();
            search = termInput;
            termInput = formatSearch(termInput);
            //Toast.makeText(getBaseContext(), termInput, Toast.LENGTH_LONG).show();
            termNameFromSearch.setText(termInput);
            try {
                DataInputStream textFileStream1 = new DataInputStream(getAssets().open("terms_topics"));
                Scanner searchScan = new Scanner(textFileStream1);
                if(termListContainsSearch(termInput,searchScan)) {
                    String topic = findTopicTermPage(termInput, searchScan);
                    DataInputStream textFileStream2 = new DataInputStream(getAssets().open(topic));
                    Scanner scan = new Scanner(textFileStream2);
                    loadTermsTermPage(scan, topic);
                }
                else {
                    Intent searchIntent = new Intent(this, SearchPage.class);
                    //searchIntent.putExtra("search", search);
                    //searchIntent.putExtra("searchTerms", findTermsThatContainSearch(search,searchScan));
                    startActivity(searchIntent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!termNameFromTopic.getText().toString().equals("")) {
            termInput = termNameFromTopic.getText().toString();
            //Toast.makeText(getBaseContext(), termInput, Toast.LENGTH_LONG).show();
            if(!topicContainsTerm(termInput)) {
                try {
                    DataInputStream textFileStream1 = new DataInputStream(getAssets().open("terms_topics"));
                    Scanner topicScan = new Scanner(textFileStream1);
                    String topic = findTopicTermPage(termInput, topicScan);
                    //Toast.makeText(getBaseContext(), "Hi", Toast.LENGTH_LONG).show();
                    DataInputStream textFileStream2 = new DataInputStream(getAssets().open(topic));
                    Scanner scan = new Scanner(textFileStream2);
                    loadTermsTermPage(scan, topic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            termInput = "";
        }

        term = getTermInfo(termInput, termInfoTermPage);
        relatedTerms = term.splitRelatedTerms(term.getRelatedTerms());
        definitionBody.setText(term.getDefinition());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, relatedTerms); //initializing ArrayAdapter
        relatedList.setAdapter(adapter); //sets adapter as the ArrayAdapter for listView
        relatedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent termIntentFromTopic = new Intent(view.getContext(), TermPage.class);
                termInput = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), termInput, Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable("termInfo", termInfoTermPage);
                termIntentFromTopic.putExtras(bundle);
                termIntentFromTopic.putExtra("term", termInput);
                //term  = getTermInfo(termInput, termInfoTermPage);
                //relatedTerms = term.splitRelatedTerms(term.getRelatedTerms());
                startActivity(termIntentFromTopic);
                //definitionBody.setText(term.getDefinition());
                //termNameFromTopic.setText(termInput);
            }
        });
    }

    public Term getTermInfo(String termInput, ArrayList<Term> termInfo) {
        Term term = new Term();
        for(int i=0; i<termInfo.size(); i++) {
            if(termInfo.get(i).getTermName().equals(termInput)) {
                term.setTermName(termInfo.get(i).getTermName());
                term.setDefinition(termInfo.get(i).getDefinition());
                term.setRelatedTerms(termInfo.get(i).getRelatedTerms());
                term.setTopic(termInfo.get(i).getTopic());
            }
        }
        return term;
    }

    public void loadTermsTermPage(Scanner scan, String topic) {
        int numberOfLines = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<numberOfLines/3; i++) {
            String name = scan.nextLine();
            String definition = scan.nextLine();
            String related = scan.nextLine();
            termInfoTermPage.add(new Term(name, definition, related, topic));
        }
    }

    public boolean topicContainsTerm(String termInput) {
        for(int i=0; i<termInfoTermPage.size(); i++) {
            if(termInfoTermPage.get(i).getTermName().equals(termInput)) {
                return true;
            }
        }
        return false;
    }

    public String findTopicTermPage(String termInput, Scanner topicScanner) {
        int numberOfLines = topicScanner.nextInt();
        topicScanner.nextLine();
        for(int i=0; i<numberOfLines; i++) {
            String line = topicScanner.nextLine();
            if(line.substring(0, line.indexOf(",")).equals(termInput)) {
                return line.substring(line.indexOf(",")+2);
            }
        }
        return "";
    }

    public static String formatSearch(String termInput) {
        termInput = termInput.trim();
        String formatted = "";
        for(int i=0; i<termInput.length(); i++) {
            if(i == 0) {
                formatted += termInput.substring(i,i+1).toUpperCase();
            }
            else {
                formatted += termInput.substring(i,i+1).toLowerCase();
            }
        }
        return formatted;
    }

    public boolean termListContainsSearch(String search, Scanner searchScan) {
        int numberOfLines = searchScan.nextInt();
        searchScan.nextLine();
        for(int i=0; i<numberOfLines; i++) {
            String line = searchScan.nextLine();
            if(line.substring(0, line.indexOf(",")).equals(search)) {
                Toast.makeText(getBaseContext(), "Yes", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        Toast.makeText(getBaseContext(), "No", Toast.LENGTH_LONG).show();
        return false;
    }

    public String findTermsThatContainSearch(String search, Scanner searchScan) {
        String relatedSearchTerms = "";
        int numberOfLines = searchScan.nextInt();
        searchScan.nextLine();
        for(int i=0; i<numberOfLines; i++) {
            String line = searchScan.nextLine();
            if(line.substring(0, line.indexOf(",")).indexOf(search) != -1) {
                relatedSearchTerms += line.substring(0, line.indexOf(",")) + ", ";
            }
        }
        return relatedSearchTerms;
    }

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}