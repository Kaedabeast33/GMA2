package org.example.jsonBuilder.gma.ma;

import org.example.jsonBuilder.gma.ma.tables.QueryJson;
import org.example.jsonBuilder.gma.ma.tables.TriggerJson;

import java.util.UUID;

public class PipelineJson {
    String name;
    String description;
    String[] tags;
    String pipelineId="pip"+ UUID.randomUUID();

    QueryJson[] queries;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public QueryJson[] getQueries() {
        return queries;
    }

    public void setQueries(QueryJson[] queries) {
        this.queries = queries;
    }




    // Getters and Setters


}
