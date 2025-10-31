package org.example.jsonBuilder.gma.ma;

import org.example.gma.templates.MAConfigTemplate;
import org.example.jsonBuilder.gma.ma.tables.BaseQueryJson;
import org.example.jsonBuilder.gma.ma.tables.ProcedureJson;
import org.example.jsonBuilder.gma.ma.tables.QueryJson;
import org.example.jsonBuilder.gma.ma.tables.TableJson;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MAJson {
    String name;
    String description;
    String[] tags;
    String maId="ma"+ UUID.randomUUID();
    String javaFolderPath;

    Map<String,String> maSettings;
    TableJson[] tables;
    PipelineJson[] pipelines;
    ProcedureJson[] procedures;

    public MAJson(MAConfigTemplate db) {
        this.name = db.getName();
        this.description = db.getDescription();
        this.tags = db.getTags();
        this.maSettings = db.getMaSettings();
        this.javaFolderPath = db.getJavaFolderPath();

    }

    public String getJavaFolderPath() {
        return javaFolderPath;
    }

    public void setJavaFolderPath(String javaFolderPath) {
        this.javaFolderPath = javaFolderPath;
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

    public String getMaId() {
        return maId;
    }

    public void setMaId(String maId) {
        this.maId = maId;
    }

    public Map<String, String> getMaSettings() {
        return maSettings;
    }

    public void setMaSettings(Map<String, String> maSettings) {
        this.maSettings = maSettings;
    }

    public TableJson[] getTables() {
        return tables;
    }

    public void setTables(TableJson[] tables) {
        this.tables = tables;
    }

    public PipelineJson[] getPipelines() {
        return pipelines;
    }

    public void setPipelines(PipelineJson[] pipelines) {
        this.pipelines = pipelines;
    }

    public void setQueries(TableJson[] tables) {
    }

    public ProcedureJson[] getProcedures() {
        return procedures;
    }

    public void setProcedures(ProcedureJson[] procedures) {
        this.procedures = procedures;
    }
}
