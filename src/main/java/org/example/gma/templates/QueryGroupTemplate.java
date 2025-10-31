package org.example.gma.templates;

import java.util.List;
import java.util.UUID;

public abstract class QueryGroupTemplate {
    String name;
    String description;
    String[] tags;

    String quegId= "queg"+ UUID.randomUUID();
    QueryTemplate[] queries;

    public String printQueryGroup(String queryGroup) {
        // Implementation for printing the query group
        System.out.println("Query Group: " + queryGroup);
        return null;

    }
    public String runQueryGroup(){
        return null;
    }






}



