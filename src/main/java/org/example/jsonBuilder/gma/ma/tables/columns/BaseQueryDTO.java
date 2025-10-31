package org.example.jsonBuilder.gma.ma.tables.columns;

import org.example.jsonBuilder.gma.ma.tables.BaseQueryJson;
import org.example.jsonBuilder.gma.ref.RefQueryGroupJson;
import org.example.jsonBuilder.gma.ref.RefSetJson;

public class BaseQueryDTO {
    protected String name;
    protected String description;
    protected String[] tags;
    protected String id; // unique identifier for the query

    protected String queryType;
    protected String contentString; // could be query or procedure

    public BaseQueryDTO(BaseQueryJson baseQueryJson) {
        this.name = baseQueryJson.getName();
        this.description = baseQueryJson.getDescription();
        this.tags = baseQueryJson.getTags();
        this.id = baseQueryJson.getId();
        this.queryType = baseQueryJson.getQueryType();
        this.contentString = baseQueryJson.getContentString();
    }


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }
}
