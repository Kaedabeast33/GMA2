package org.example.gma.templates;

import org.springframework.beans.factory.annotation.Autowired;

public class GMAConfigTemplate {
    String name;
    String description;
    String[] tags;
    String dbType;
    String dialect;


    public String reinitiaize() {
        // This method should be overridden by subclasses to provide specific reinitialization logic
        // For example, it could reset certain fields or clear caches
        return "Reinitialized";
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

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }


}
