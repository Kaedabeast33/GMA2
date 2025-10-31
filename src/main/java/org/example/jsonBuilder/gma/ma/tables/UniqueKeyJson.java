package org.example.jsonBuilder.gma.ma.tables;

import org.example.jsonBuilder.gma.ma.tables.columns.ColumnDTO;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UniqueKeyJson {
    private String name;
    private String description;
    private String[] tags;
    private String id;
    private ColumnDTO[] columns;

    public UniqueKeyJson(String name, String description, String[] tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.id = "key" + UUID.randomUUID();
    }

    public UniqueKeyJson(Map.Entry<String, List<ColumnJson>> entry) {
        this.name = entry.getKey();
        this.columns = new ColumnDTO[entry.getValue().size()];
        for(int i = 0; i < entry.getValue().size(); i++) {
            this.columns[i] = new ColumnDTO(entry.getValue().get(i)) ;
        }
    }

    public ColumnDTO[] getColumns() {
        return columns;
    }

    public void setColumns(ColumnDTO[] columns) {
        this.columns = columns;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
