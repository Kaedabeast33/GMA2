package org.example.bank.OutputClassBank;

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
        System.out.println("Java Dir: " + properties.getProperty("app.javaDir", "org.example"));
        return properties.getProperty("app.javaDir", "org.example"); // default value
    }

    public static String getTemplateDir() {
        System.out.println("Template Dir: " + properties.getProperty("app.templateDir", "gma.templates"));
        return properties.getProperty("app.templateDir", "gma.templates");
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
