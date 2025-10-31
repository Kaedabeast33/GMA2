package org.example.gma.templates;

import java.util.UUID;

public abstract class QueryTemplate {

    protected final String name;
    protected final String description;
    protected final String[] tags;
    protected final String queId;

    protected final String queryType;
    protected final String[] queryGroups;

    protected final String query;

    public QueryTemplate(String name, String description, String[] tags, String queId, String queryType, String[] queryGroups, String query) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.queId = queId;
        this.queryType = queryType;
        this.queryGroups = queryGroups;


        this.query = query;
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

    public String getQueId() {
        return queId;
    }

    public String getQueryType() {
        return queryType;
    }

    public String[] getQueryGroups() {
        return queryGroups;
    }



    public String getQuery() {
        return query;
    }

    public void  runQuery(){
        System.out.println("Running query: " + query);
    }



    //package org.example.Annotations;
    //
    //public @interface KdbQuery {
    //    String name();
    //
    //    String description();
    //
    //    String[] tags() default {};
    //
    //    String queryType();
    //
    //    String[] queryGroups() default {};
    //
    //    int[] queryGroupsOrder() default {1};
    //
    //    String[] querySet() default {};
    //}

}
