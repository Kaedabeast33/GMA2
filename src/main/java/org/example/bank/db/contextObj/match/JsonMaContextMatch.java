package org.example.bank.db.contextObj.match;

import org.example.JsonBuilder.json.ma.MAJson;

public class JsonMaContextMatch {
    ContextInterface context;
    MAJson json;



    public JsonMaContextMatch(ContextInterface context, MAJson json) {
        this.context = context;
        this.json = json;
    }

    public ContextInterface getContext() {
        return context;
    }

    public void setContext(ContextInterface context) {
        this.context = context;
    }

    public MAJson getJson() {
        return json;
    }


    public void setJson(MAJson json) {
        this.json = json;
    }

    public boolean descriptionMatch(){
        return true;
    }


}
