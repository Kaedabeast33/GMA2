package org.example.bank.db.contextObj;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.bank.db.contextObj.match.ContextInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ContextTable implements ContextInterface {
        @JsonProperty("table_name")
    private String tableName;

    @JsonProperty("table_tags")
    private String[] tableTags;

    @JsonProperty("table_description")
    private String[] tableDescription;

    @JsonProperty("table_rules")
    Rules rules;

    @Override
    public String toString() {
        return "ContextTable{" +
                "tablename='" + tableName + '\'' +
                ", tabletags=" + Arrays.toString(tableTags) +
                ", tabledescription='" + Arrays.toString(tableDescription) + '\'' +
                ", rules=" + rules +
                '}';
    }

    public Rules getRules(){
        return rules;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getTableTags() {
        return tableTags;
    }

    public void setTableTags(String[] tableTags) {
        this.tableTags = tableTags;
    }

    public String[] getTableDescription() {
        return tableDescription;
    }

    public void setTableDescription(String[] tableDescription) {
        this.tableDescription = tableDescription;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public String getName() {
        return tableName;
    }

    public void setName(String tablename) {
        this.tableName = tablename;
    }

    public String[] getTags() {
        return tableTags;
    }

    public void setTags(String[] tabletags) {
        this.tableTags = tabletags;
    }

    public String[] getDescription() {
        return tableDescription;
    }

    public void setDescription(String[] tabledescription) {
        this.tableDescription = tabledescription;
    }
}
