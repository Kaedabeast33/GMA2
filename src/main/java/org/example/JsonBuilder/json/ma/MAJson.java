package org.example.JsonBuilder.json.ma;

import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.JsonBuilder.json.ma.tables.ProcedureJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.bank.commonValues.Identifier;
import org.example.bank.db.contextObj.match.JsonInterface;

import java.util.*;


public class MAJson implements JsonInterface {
    String name;
    String description;
    String jdbcUrl;
    String user;
    String pass;
    String[] tags;
    String maId = "ma" + UUID.randomUUID();
    String javaFolderPath;
    String transactionManagerBeanName;
    String entityManagerBeanName;
    String uploadType;
    Identifier identifier;


    Map<String, String> maSettings;
    TableJson[] tables;
    PipelineJson[] pipelines;
    ProcedureJson[] procedures;

    public MAJson(){

    }
    public MAJson(Identifier identifier,MAConfigTemplate db) {
        this.name = db.getName();
        this.description = db.getDescription();
        this.tags = db.getTags();
        this.maSettings = db.getMaSettings();
        this.javaFolderPath = db.getJavaFolderPath();
        this.jdbcUrl = db.getJdbcConnectionUrl();
        this.user = db.getUsername();
        this.pass = db.getPassword();
        this.transactionManagerBeanName = db.getTransactionManagerBeanName();
        this.identifier = identifier;
//        this.uploadType = db.get();

    }

    public String getEntityManagerBeanName() {
        return entityManagerBeanName;
    }

    public void setEntityManagerBeanName(String entityManagerBeanName) {
        this.entityManagerBeanName = entityManagerBeanName;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getTransactionManagerBeanName() {
        return transactionManagerBeanName;
    }

    public void setTransactionManagerBeanName(String transactionManagerBeanName) {
        this.transactionManagerBeanName = transactionManagerBeanName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public List<TableJson> getTableByType(String type) {
        if(tables ==null) return null;
        return Arrays.stream(tables).filter(t-> Objects.equals(t.getTableType(), type)).toList();
    }
}
