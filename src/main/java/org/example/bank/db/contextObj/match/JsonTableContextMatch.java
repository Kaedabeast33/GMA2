package org.example.bank.db.contextObj.match;

import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.bank.db.contextObj.ContextMa;

public class JsonTableContextMatch {
    ContextInterface context;
    TableJson json;



    public JsonTableContextMatch(ContextInterface context, TableJson json) {
        this.context = context;
        this.json = json;
    }

    public ContextInterface getContext() {
        return context;
    }

    public void setContext(ContextInterface context) {
        this.context = context;
    }

    public TableJson getJson() {
        return json;
    }

    public void setJson(TableJson json) {
        this.json = json;
    }

    public boolean descriptionMatch(){
        return true;
    }


}
