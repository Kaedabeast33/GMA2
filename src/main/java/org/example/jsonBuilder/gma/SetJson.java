package org.example.jsonBuilder.gma;

import org.example.jsonBuilder.gma.ref.RefProcedureJson;
import org.example.jsonBuilder.gma.ref.RefQueryJson;
import org.example.jsonBuilder.gma.ref.RefTableJson;
import org.example.jsonBuilder.gma.ref.RefTriggerJson;



public class SetJson {
    String name;
    String description;
    String[] tags;
    String setId = "set" + java.util.UUID.randomUUID();

    RefTableJson[] tableSet;
    RefQueryJson[] querySet;
    RefProcedureJson[] procedureSet;
    RefTriggerJson[] triggerSet;

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

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public RefTableJson[] getTableSet() {
        return tableSet;
    }

    public void setTableSet(RefTableJson[] tableSet) {
        this.tableSet = tableSet;
    }

    public RefQueryJson[] getQuerySet() {
        return querySet;
    }

    public void setQuerySet(RefQueryJson[] querySet) {
        this.querySet = querySet;
    }

    public RefProcedureJson[] getProcedureSet() {
        return procedureSet;
    }

    public void setProcedureSet(RefProcedureJson[] procedureSet) {
        this.procedureSet = procedureSet;
    }

    public RefTriggerJson[] getTriggerSet() {
        return triggerSet;
    }

    public void setTriggerSet(RefTriggerJson[] triggerSet) {
        this.triggerSet = triggerSet;
    }


    // Getters and Setters
}
