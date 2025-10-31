package org.example.gma.templates;

import java.util.HashMap;
import java.util.Map;

public class ColumnGroupTemplate {
    public static final  String name = "default";
    public static final String columnGroupId = "colg" + java.util.UUID.randomUUID();
    public static final String description = "";
    public static final String[] tags = {};
    public static final Map<String,ColumnTemplate> columns = new HashMap<>();


    public void addCollumn(String name, ColumnTemplate columnTemplate){
        columns.put(name,columnTemplate);
    }



}
