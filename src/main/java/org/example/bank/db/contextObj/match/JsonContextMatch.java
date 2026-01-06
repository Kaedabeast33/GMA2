package org.example.bank.db.contextObj.match;

import org.example.JsonBuilder.json.ma.MAJson;
import org.example.bank.db.contextObj.ContextMa;

public class JsonContextMatch {
    ContextInterface context;
    JsonInterface json;



    public JsonContextMatch(ContextInterface context, JsonInterface json) {
        this.context = context;
        this.json = json;
    }

    public ContextInterface getContext() {
        return context;
    }

    public void setContext(ContextInterface context) {
        this.context = context;
    }

    public JsonInterface getJson() {
        return json;
    }

    public void setJson(JsonInterface json) {
        this.json = json;
    }

    public boolean descriptionMatch(){
        return true;
    }


}
