package org.example.JsonBuilder.json.ma;

import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.JsonBuilder.json.ma.tables.AirTableJson;
import org.example.JsonBuilder.json.ma.tables.ProcedureJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.bank.commonValues.Identifier;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AirMAJson {

    String name;
    String description;

    String[] tags;
    String maId = "ma" + UUID.randomUUID();
    String javaFolderPath;
    String appId;


    Identifier identifier;



    AirTableJson[] tables;


    public AirMAJson(){

    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public AirMAJson(Identifier identifier, MAConfigTemplate db) {
        this.name = db.getName();
        this.description = db.getDescription();
        this.tags = db.getTags();

        this.javaFolderPath = db.getJavaFolderPath();
        this.appId = db.getAirtableApp();

        this.identifier = identifier;
//        this.uploadType = db.get();

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

    public String getJavaFolderPath() {
        return javaFolderPath;
    }

    public void setJavaFolderPath(String javaFolderPath) {
        this.javaFolderPath = javaFolderPath;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public AirTableJson[] getTables() {
        return tables;
    }

    public void setTables(AirTableJson[] tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MAJson)) return false;
        MAJson maJson = (MAJson) o;
        return Objects.equals(name, maJson.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier.getMaName());
    }

    @Override
    public String toString() {
        return "MAJson{name=" + name + '}';
    }

}
