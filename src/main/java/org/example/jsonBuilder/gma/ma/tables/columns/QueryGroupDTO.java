package org.example.jsonBuilder.gma.ma.tables.columns;

import org.example.jsonBuilder.gma.ma.tables.BaseQueryJson;

import java.util.UUID;

public class QueryGroupDTO {
    String name;
    String grId = "gr"+ UUID.randomUUID();
    BaseQueryJson queries;

    public BaseQueryJson getQueries() {
        return queries;
    }

    public void setQueries(BaseQueryJson queries) {
        this.queries = queries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrId() {
        return grId;
    }

    public void setGrId(String grId) {
        this.grId = grId;
    }
}
