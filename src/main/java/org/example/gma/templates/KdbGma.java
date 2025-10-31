package org.example.gma.templates;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class KdbGma {
    String name;
    String description;
    String[] tags;
    Map<String,String> gmaSettings;


    List<MAConfigTemplate> ma;




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

    public Map<String, String> getGmaSettings() {
        return gmaSettings;
    }

    public void setGmaSettings(Map<String, String> gmaSettings) {
        this.gmaSettings = gmaSettings;
    }

    public KdbGma(String name, String description, String[] tags, Map<String, String> gmaSettings, List<MAConfigTemplate> ma) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.gmaSettings = gmaSettings;
        this.ma = ma;
    }

    public KdbGma() {
    }





    public List<MAConfigTemplate> getMa() {
        return ma;
    }

    public void setMa(List<MAConfigTemplate> ma) {
        this.ma = ma;
    }






    public void setTable(MAConfigTemplate table) {
    }
}
