package org.example.bank.db.contextObj;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.bank.db.contextObj.match.ContextInterface;

import java.util.Arrays;

public class ContextColumn implements ContextInterface {
    @JsonProperty("column_name")
    String column_name;
    @JsonProperty("column_tags")
    String[] column_tags;
    @JsonProperty("column_description")
    String[] column_description;

    @JsonProperty("column_rules")
    Rules rules;

    @Override
    public String toString() {
        return "ContextColumn{" +
                "column_name='" + column_name + '\'' +
                ", column_tags=" + Arrays.toString(column_tags) +
                ", column_description='" + Arrays.toString(column_description) + '\'' +
                '}';
    }

    public Rules getRules(){
        return rules;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String[] getColumn_tags() {
        return column_tags;
    }

    public void setColumn_tags(String[] column_tags) {
        this.column_tags = column_tags;
    }

    public String[] getColumn_description() {
        return column_description;
    }

    public void setColumn_description(String[] column_description) {
        this.column_description = column_description;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public String getName() {
        return column_name;
    }

    public void setName(String column_name) {
        this.column_name = column_name;
    }

    public String[] getTags() {
        return column_tags;
    }

    public void setTags(String[] column_tags) {
        this.column_tags = column_tags;
    }

    public String[] getDescription() {
        return column_description;
    }

    public void setDescription(String[] column_description) {
        this.column_description = column_description;
    }
}
