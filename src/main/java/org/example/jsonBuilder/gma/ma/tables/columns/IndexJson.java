package org.example.jsonBuilder.gma.ma.tables.columns;

import org.example.CommonValues.IndexMapObject;


import java.util.*;

public class IndexJson {
    String indexGroupName;
    String indexId = "idx" + UUID.randomUUID();
    String description;
    String[] tags;
    String[] indexSet;
    List<ColumnDTO> columns;

    public IndexJson(Map.Entry<String, List<IndexMapObject>> entry) {
        this.indexGroupName = entry.getKey();
        this.columns = new ArrayList<>();

        // Clone the list and sort it by the order field
        List<IndexMapObject> originalList = entry.getValue();
        originalList.sort(Comparator.comparingInt(IndexMapObject::getOrder));

        // Now create the columns list in the sorted order
        this.columns = new ArrayList<>();
        for (IndexMapObject obj : originalList) {
            this.columns.add(new ColumnDTO(obj.getColumn()));
        }
        GroupDTO group = new GroupDTO(this.indexGroupName, this.indexId, this.columns.toArray(new ColumnDTO[0]));

        for(int j = 0; j < Objects.requireNonNull(this.columns).size(); j++) {
            entry.getValue().get(j).getColumn().addIndex(group);
        }
    }

    public IndexJson() {
    }

    public String getIndexGroupName() {
        return indexGroupName;
    }

    public void setIndexGroupName(String indexGroupName) {
        this.indexGroupName = indexGroupName;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
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

    public String[] getIndexSet() {
        return indexSet;
    }

    public void setIndexSet(String[] indexSet) {
        this.indexSet = indexSet;
    }

    public List<ColumnDTO> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDTO> columns) {
        this.columns = columns;
    }

    public void setUnique(boolean b) {

    }

    /*match schema table column name
    * isNullable unique* type  isPrimaryKey
    *
    * match table
    * indexGroupName
    *
    *
    *
    * Table
    * ColChecks IndexChecks TriggerChecks
    *
    * */

}
