package org.example.jsonBuilder.gma.ma.tables;

import org.example.jsonBuilder.gma.ma.tables.columns.QueryGroupDTO;
import org.example.jsonBuilder.gma.ref.RefSetJson;

import java.util.ArrayList;
import java.util.List;

public class BaseQueryJson {
    protected String name;
    protected String description;
    protected String[] tags;
    protected String id; // unique identifier for the query

    protected String queryType;
    protected String contentString; // could be query or procedure

    protected List<QueryGroupDTO> groups=new ArrayList<>();
    protected RefSetJson[] sets;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTags() {
        return tags;
    }


    public String getQueryType() {
        return queryType;
    }

    public String getContentString() {
        return contentString;
    }



    public RefSetJson[] getSets() {
        return sets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }


    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public List<QueryGroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<QueryGroupDTO> groups) {
        this.groups = groups;
    }

    public void setSets(RefSetJson[] sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "BaseQueryJson{" +
                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", tags=" + Arrays.toString(tags) +
//                ", id='" + id + '\'' +
//                ", queryType='" + queryType + '\'' +
                ", contentString='" + contentString + '\'' +
//                ", groups=" + Arrays.toString(groups) +
//                ", sets=" + Arrays.toString(sets) +
                '}';
    }
}
