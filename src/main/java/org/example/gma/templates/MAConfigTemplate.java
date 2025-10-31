package org.example.gma.templates;

import java.sql.SQLException;
import java.util.Map;

public class MAConfigTemplate {
    String name;
    String description;
    String[] tags;
    Map<String,String> maSettings;
    String jdbcConnectionUrl;
    String javaFolderPath;
    KdbManager kdbManager;
    String username;
    String password;

    public String getJavaFolderPath() {
//        System.out.println("javaFolderPath:"+javaFolderPath);
//        System.out.println("In "+this.name);
        return javaFolderPath;
    }

    public void setJavaFolderPath(String javaFolderPath) {
        this.javaFolderPath = javaFolderPath;
    }

    public void setKdbManager(KdbManager kdbManager) {
        this.kdbManager = kdbManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public KdbManager getKdbManager() {
        return kdbManager;
    }




    public void initKdbManager() throws SQLException {
//        System.out.println(jdbcConnectionUrl);
        kdbManager = new KdbManager(username, password, jdbcConnectionUrl);

    }

    public String getJdbcConnectionUrl() {
        return jdbcConnectionUrl;
    }

    public void setJdbcConnectionUrl(String jdbcConnectionUrl) {
        this.jdbcConnectionUrl = jdbcConnectionUrl;
    }

    public Map<String, String> getMaSettings() {
        return maSettings;
    }

    public void setMaSettings(Map<String, String> maSettings) {
        this.maSettings = maSettings;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
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


}
