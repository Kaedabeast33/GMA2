package org.example.gma.templates;

public abstract class GMATemplate {
    protected final String name;
    protected final String description;
    protected final String[] tags;

    protected final String gmaId;
    protected final String dbType;
    protected final String dialect;

    public GMATemplate(String name, String description, String[] tags,
                       String gmaId, String dbType, String dialect) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.gmaId = gmaId;
        this.dbType = dbType;
        this.dialect = dialect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTags() {
        return tags;
    }

    public String getGmaId() {
        return gmaId;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDialect() {
        return dialect;
    }
}
