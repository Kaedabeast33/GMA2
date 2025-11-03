package org.example.JsonBuilder.IDE.json.ma.tables.columns;

import org.example.JsonBuilder.IDE.json.SetJson;
import org.example.JsonBuilder.IDE.json.ref.RefSetJson;

public class ColumnConstraintJson {
    String name;
    String description;
    String[] tags;
    String constraintId = "con" + java.util.UUID.randomUUID();
    String constraintString;
    RefSetJson[] constraintSets;
    SetJson[] sets;


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

    public String getConstraintId() {
        return constraintId;
    }

    public void setConstraintId(String constraintId) {
        this.constraintId = constraintId;
    }


}
