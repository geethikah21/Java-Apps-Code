package com.example.geeth.physics_app;

/**
 * Created by geeth on 1/3/2019.
 */
public class Search {
    private String search;
    private String relatedSearchTerms;

    public Search() {
        search = "";
        relatedSearchTerms = "";
    }
    public Search(String search, String relatedSearchTerms) {
        this.search = search;
        this.relatedSearchTerms = relatedSearchTerms;
    }

    public String getSearch() {
        return search;
    }

    public String getRelatedSearchTerms() {
        return relatedSearchTerms;
    }

    public void setSearch(String search) { this.search = search; }

    public void setRelatedSearchTerms(String relatedSearchTerms) { this.relatedSearchTerms = relatedSearchTerms; }


    public String[] splitSearchTerms(String searchTerms) {
        String[] searchTermArray = searchTerms.split(",");

        for(int i=0; i<searchTermArray.length; i++) {
            searchTermArray[i] = searchTermArray[i].trim();
        }

        return searchTermArray;
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

}
