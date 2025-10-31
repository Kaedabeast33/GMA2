package org.example.jsonBuilder.gma.ma.tables.columns;


import org.apache.catalina.Group;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ColumnGroupJson {

    String name;

    String columnGroupId = "colg" + java.util.UUID.randomUUID();

    ColumnDTO[] groupColumns;

    public List<String> getgroupColumns(){
        return Arrays.stream(groupColumns).map(ColumnDTO::getName).toList();

    }



    public ColumnGroupJson(){

    }


    public ColumnGroupJson(Map.Entry<String, List<ColumnJson>> value) {
// were adding the group DTO to the Column Jsons and the ColumnDTOs to the ColumnGroups

        this.name = value.getKey();
        for(int i = 0; i < value.getValue().size(); i++) {
            if (i == 0) {
                this.groupColumns = new ColumnDTO[value.getValue().size()];
            }
            this.groupColumns[i] = new ColumnDTO(value.getValue().get(i)) ;

        }

        GroupDTO group = new GroupDTO(this.name,this.columnGroupId, Objects.requireNonNull(this.groupColumns));

        for(int j = 0; j < Objects.requireNonNull(this.groupColumns).length; j++) {
            value.getValue().get(j).addColumnGroup(group);
        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnGroupId() {
        return columnGroupId;
    }

    public void setColumnGroupId(String columnGroupId) {
        this.columnGroupId = columnGroupId;
    }

    public ColumnDTO[] getGroupColumns() {
        return groupColumns;
    }

    public void setgroupColumns(ColumnDTO[] groupColumns) {
        this.groupColumns = groupColumns;
    }
}
