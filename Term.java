package com.example.geeth.physics_app;

import java.io.Serializable;

/**
 * Created by geeth on 3/27/2018.
 */
public class Term implements Serializable {
    private String termName;
    private String definition;
    private String topic;
    private String relatedTerms;

    public Term() {
        termName = "";
        definition = "";
        relatedTerms = "";
        topic = "";
    }
    public Term(String termName, String definition, String relatedTerms, String topic) {
        this.termName = termName;
        this.definition = definition;
        this.relatedTerms = relatedTerms;
        this.topic = topic;
    }

    public String getTermName() {
        return termName;
    }

    public String getDefinition() {
        return definition;
    }

    public String getTopic() { return topic;}

    public String getRelatedTerms() { return relatedTerms; }

    public void setTermName(String termName) { this.termName = termName; }

    public void setDefinition(String definition) {this.definition = definition; }

    public void setTopic(String topic) {this.topic = topic;}

    public void setRelatedTerms(String relatedTerms) {this.relatedTerms = relatedTerms; }

    public String[] splitRelatedTerms(String relatedTerms) {
        String[] relatedTermArray = relatedTerms.split(",");

        for(int i=0; i<relatedTermArray.length; i++) {
            relatedTermArray[i] = relatedTermArray[i].trim();
        }

        return relatedTermArray;
    }
}
