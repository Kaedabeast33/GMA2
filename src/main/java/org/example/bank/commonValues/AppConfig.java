package org.example.bank.commonValues;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    public static String getJavaDir() {
        System.out.println("Java Dir: " + properties.getProperty("app.javaDir", ""));
        return properties.getProperty("app.javaDir", ""); // default value
    }

    public static String getGmaName(){
        return "vyta";
    }



    public static String getJdbcUrl() {
//        System.out.println("JDBC URL: " + properties.getProperty("app.jdbc.url", "jdbc:mysql://localhost:3306/mydb"));
        return properties.getProperty("app.jdbc.url", "jdbc:mysql://localhost:3306/mydb");
    }

    public static String getJdbcUser() {
//        System.out.println("JDBC URL: " + properties.getProperty("app.jdbc.url", "jdbc:mysql://localhost:3306/mydb"));
        return properties.getProperty("app.jdbc.username", "user");
    }

    public static String getJdbcPassword() {
        return properties.getProperty("app.jdbc.password", "password");
    }

    public static String getOutputDir() {
        System.out.println("Output Dir: " + properties.getProperty("app.outputDir", "output"));
        return properties.getProperty("app.outputDir", "output");
    }
    public static String getConnectionString(){
        return properties.getProperty("app.connection", "jdbc:mysql://localhost:3306/mydb");
    }
    public static String getUserString(){
        return properties.getProperty("app.user", "root");
    }
    public static String getPassString(){
        return properties.getProperty("app.password", "password");
    }
}
