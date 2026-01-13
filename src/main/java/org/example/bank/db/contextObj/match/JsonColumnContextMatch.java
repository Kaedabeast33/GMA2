package org.example.bank.db.contextObj.match;


import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

public class JsonColumnContextMatch {
    ContextInterface context;
    ColumnJson json;

    public JsonColumnContextMatch(ContextInterface context, ColumnJson json) {
        this.context = context;
        this.json = json;
    }

    public ContextInterface getContext() {
        return context;
    }

    public void setContext(ContextInterface context) {
        this.context = context;
    }

    public ColumnJson getJson() {
        return json;
    }

    public void setJson(ColumnJson json) {
        this.json = json;
    }

    public boolean descriptionMatch(){
        return true;
    }
}
