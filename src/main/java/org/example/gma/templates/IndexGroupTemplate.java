package org.example.gma.templates;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class IndexGroupTemplate {
    public static final String name = "default";
    public static final String indexId = "idx" + UUID.randomUUID();
    public static final String description="";
    public static final String[] tags={};
    public static final Map<String,SetTemplate> indexSet= new TreeMap<>();
    public static final Map<String,ColumnTemplate> refColumnJson = new TreeMap<>();
}
