package org.example.jsonBuilder.gma;

import org.example.jsonBuilder.gma.ma.tables.BaseQueryJson;
import org.example.jsonBuilder.gma.ma.tables.columns.BaseQueryDTO;
import org.example.jsonBuilder.gma.ref.RefProcedureJson;
import org.example.jsonBuilder.gma.ref.RefQueryJson;

import java.util.List;
import java.util.UUID;

public class QueryGroupJson {
    String name;
    String grId = "qgr"+ UUID.randomUUID();
    String[] tags;

    List<BaseQueryDTO> queries = new java.util.ArrayList<>();


    public QueryGroupJson(String name) {
        this.name = name;
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<BaseQueryDTO> getQueries() {
        return queries;
    }

    public void setQueries(List<BaseQueryDTO> queries) {
        this.queries = queries;
    }

    public void addQueries(List<BaseQueryJson> queries) {

        for(BaseQueryJson query:queries){
            this.queries.add(new BaseQueryDTO(query));
        }


    }
}
