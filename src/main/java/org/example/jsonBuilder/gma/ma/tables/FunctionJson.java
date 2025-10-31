package org.example.jsonBuilder.gma.ma.tables;

import org.example.jsonBuilder.gma.ref.RefColumnJson;
import org.example.jsonBuilder.gma.ref.RefSetJson;

import java.util.UUID;

public class FunctionJson {
    String name;
    String description;
    String[] tags;
    String procedureId = "que" + UUID.randomUUID();

    String procedureString;

    RefSetJson[] procedureSets;
    RefColumnJson[] refColumn;

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

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureString() {
        return procedureString;
    }

    public void setProcedureString(String procedureString) {
        this.procedureString = procedureString;
    }

    public RefSetJson[] getProcedureSets() {
        return procedureSets;
    }

    public void setProcedureSets(RefSetJson[] procedureSets) {
        this.procedureSets = procedureSets;
    }

    public RefColumnJson[] getRefColumn() {
        return refColumn;
    }

    public void setRefColumn(RefColumnJson[] refColumn) {
        this.refColumn = refColumn;
    }
}
