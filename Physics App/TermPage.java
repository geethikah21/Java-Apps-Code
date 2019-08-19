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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TermPage extends AppCompatActivity {
    TextView termNameFromTopic;
    TextView termNameFromSearch;
    TextView termNamefromSearchPage;
    TextView definitionBody;
    ListView relatedList;
    String termInput;
    Term term = new Term();
    HashMap<String, Term> termInfo_termPage = new HashMap<String, Term>();
    String[] relatedTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_page);

        //instantiate objects
        termNameFromTopic = (TextView) findViewById(R.id.termNameFromTopic);
        termNameFromSearch = (TextView) findViewById(R.id.termNameFromSearch);
        termNamefromSearchPage = (TextView) findViewById(R.id.termNameFromSearchPage);
        definitionBody = (TextView) findViewById(R.id.definition_body);
        relatedList = (ListView) findViewById(R.id.relatedList);

        Intent receivingIntent = this.getIntent();
        Bundle bundle = receivingIntent.getExtras();

        if(termInfo_termPage == null) {
            termInfo_termPage = new HashMap<String, Term>();
        }

        termNameFromTopic.setText(getIntent().getStringExtra("term"));
        termNameFromSearch.setText(getIntent().getStringExtra("search"));
        termNamefromSearchPage.setText(getIntent().getStringExtra("termFromSearchPage"));

        if(!termNameFromSearch.getText().toString().equals("")) {
            termInput = formatSearch(termNameFromSearch.getText().toString());
            termNameFromSearch.setText(termInput);
            //Toast.makeText(getBaseContext(), termInput, Toast.LENGTH_LONG).show();
            try {
                DataInputStream textFileStream1 = new DataInputStream(getAssets().open("terms_topics"));
                Scanner searchScan = new Scanner(textFileStream1);
                if(termListContainsSearch(termInput,searchScan)) {
                    DataInputStream textFileStream5 = new DataInputStream(getAssets().open("terms_topics"));
                    searchScan = new Scanner(textFileStream5);
                    String topic = findTopicTermPage(termInput, searchScan);
                    DataInputStream textFileStream2 = new DataInputStream(getAssets().open(topic));
                    Scanner scan = new Scanner(textFileStream2);
                    loadTermsTermPage(scan, topic);
                    //Toast.makeText(getBaseContext(), "true", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent searchIntent = new Intent(this, SearchPage.class);
                    DataInputStream textFileStream6 = new DataInputStream(getAssets().open("terms_topics"));
                    searchScan = new Scanner(textFileStream6);
                    searchIntent.putExtra("search", termInput);
                    searchIntent.putExtra("searchTerms", findTermsThatContainSearch(termInput.toLowerCase(),searchScan));
                    startActivity(searchIntent);
                    //Toast.makeText(getBaseContext(), "false", Toast.LENGTH_LONG).show();
                }
            }
            catch (IOException e) {
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
        //Begin changes MP3 2019
        else if(!termNamefromSearchPage.getText().toString().equals("")) {
            termInput = termNamefromSearchPage.getText().toString();
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
        //End changes MP3 2019
        else {
            termInput = "";
        }

        if(termInfo_termPage.containsKey(termInput)) {
            term = getTermInfo(termInput, termInfo_termPage);
            relatedTerms = term.splitRelatedTerms(term.getRelatedTerms());
            definitionBody.setText(term.getDefinition());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, relatedTerms); //initializing ArrayAdapter
            relatedList.setAdapter(adapter); //sets adapter as the ArrayAdapter for listView
            relatedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                    Intent termIntentFromTopic = new Intent(view.getContext(), TermPage.class);
                    termInput = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(getBaseContext(), termInput, Toast.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("termInfo", termInfo_termPage);
                    termIntentFromTopic.putExtras(bundle);
                    termIntentFromTopic.putExtra("term", termInput);
                    startActivity(termIntentFromTopic);
                }
            });
        }

    }

    public Term getTermInfo(String termInput, HashMap<String, Term> termInfo) {
        Term term = new Term();
        if(termInput.equals("")) {
            term.setTermName("");
            term.setDefinition("");
            term.setRelatedTerms("");
            term.setTopic("");
        }
        else {
            term.setTermName(termInput);
            term.setDefinition(termInfo.get(termInput).getDefinition());
            term.setRelatedTerms(termInfo.get(termInput).getRelatedTerms());
            term.setTopic(termInfo.get(termInput).getTopic());
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
            termInfo_termPage.put(name, new Term(name, definition, related, topic));
        }
    }

    public boolean topicContainsTerm(String termInput) {
        return termInfo_termPage.containsKey(termInput);
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

    //Begin changes for 3 MP 2019
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
            String[] term = line.split(",");
            if(term[0].equals(search)) {
                //Toast.makeText(getBaseContext(), "Yes", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    public String findTermsThatContainSearch(String search, Scanner searchScan) {
        String relatedSearchTerms = "";
        int numberOfLines = searchScan.nextInt();
        searchScan.nextLine();
        for(int i=0; i<numberOfLines; i++) {
            String line = searchScan.nextLine();
            String[] term = line.split(",");
            if(term[0].toLowerCase().indexOf(search) != -1) {
                relatedSearchTerms += line.substring(0, line.indexOf(",")) + ", ";
            }
        }
        return relatedSearchTerms;
    }
    //End changes for MP3 2019

    public void goHome(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}