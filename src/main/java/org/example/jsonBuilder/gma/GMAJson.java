package org.example.jsonBuilder.gma;

import org.example.gma.templates.KdbGma;
import org.example.jsonBuilder.gma.ma.MAJson;


import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GMAJson {

    String name;
    String description;
    String[] tags;
    String gmaId="gma"+ UUID.randomUUID();
    Map<String,String> gmaSettings;

    List<MAJson> ma;
    SetJson[] sets;
    QueryGroupJson[]queryGroups;


    public void addGroup(QueryGroupJson group) {
        if (queryGroups == null) {
            queryGroups= new QueryGroupJson[]{group};
        } else {
            QueryGroupJson[] newGroups = new QueryGroupJson[queryGroups.length + 1];
            System.arraycopy(queryGroups, 0, newGroups, 0,queryGroups.length);
            newGroups[queryGroups.length] = group;
            queryGroups = newGroups;
        }
    }

    public GMAJson(KdbGma gma) {
        this.name = gma.getName();
        this.description = gma.getDescription();
        this.tags = gma.getTags();
        this.gmaSettings = gma.getGmaSettings();
//        this.sets = gma.getSets();
//        this.groups = gma.getGroups();
    }


//    public GMAJson(KdbGmaDb gma){
//
//    }


    public Map<String, String> getGmaSettings() {
        return gmaSettings;
    }

    public void setGmaSettings(Map<String, String> gmaSettings) {
        this.gmaSettings = gmaSettings;
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

    public String getGmaId() {
        return gmaId;
    }

    public void setGmaId(String gmaId) {
        this.gmaId = gmaId;
    }



    public List<MAJson> getMa() {
        return ma;
    }

    public void setMa(List<MAJson> ma) {
        this.ma = ma;
    }

    public SetJson[] getSets() {
        return sets;
    }

    public void setSets(SetJson[] sets) {
        this.sets = sets;
    }

    public QueryGroupJson[] getQueryGroups() {
        return queryGroups;
    }

    public void setQueryGroups(QueryGroupJson[] queryGroups) {
        this.queryGroups = queryGroups;
    }
}
