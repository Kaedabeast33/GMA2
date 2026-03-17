package org.example.bank.db.contextObj;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.bank.db.contextObj.match.ContextInterface;

import java.util.Arrays;

public class ContextMa implements ContextInterface {
    String ma_name;
    String[] ma_tags;
    String[] ma_description;
    @JsonProperty("ma_rules")
    Rules rules;

    @Override
    public String toString() {
        return "ContextMa{" +
                "ma_name='" + ma_name + '\'' +
                ", ma_tags=" + Arrays.toString(ma_tags) +
                ", ma_description='" + Arrays.toString(ma_description) + '\'' +
                '}';
    }

    public Rules getRules(){
        return rules;
    }


    public String getMa_name() {
        return ma_name;
    }

    public String[] getMa_tags() {
        return ma_tags;
    }

    public String[] getMa_description() {
        return ma_description;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public void setMa_name(String ma_name) {
        this.ma_name = ma_name;
    }

    public void setMa_tags(String[] ma_tags) {
        this.ma_tags = ma_tags;
    }

    public void setMa_description(String[] ma_description) {
        this.ma_description = ma_description;
    }

    @Override
    public String getName() {
        return this.ma_name;
    }

    @Override
    public String[] getTags() {
        return this.ma_tags;
    }

    @Override
    public String[] getDescription() {
        return this.ma_description;
    }

    //{"ma_name": "client", "ma_tags": ["raw", "json", "parsed", "client", "health", "records"], "table_name": "clients", "table_tags": [], "column_name": "db_id", "column_tags": [],
// "ma_description": "this is the schema where all client health records data is kept",
// "table_description": "This contains Client information which includes the Client Id",
// "column_description": ""}
}
