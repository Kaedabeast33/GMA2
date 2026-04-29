package org.example.JsonBuilder.json.ma.tables.columns;

import org.example.ClassOutputCreator.GenericInterface.GenericColumnInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ColumnGroupJson {

    String name;

    String columnGroupId = "colg" + java.util.UUID.randomUUID();

    ColumnDTO[] groupColumns;


    public List<String> getgroupColumns() {
        return Arrays.stream(groupColumns).map(ColumnDTO::getName).toList();

    }


    public ColumnGroupJson() {

    }

    public static ColumnGroupJson buildAiColumnGroupJson(Map.Entry<String, List<AiColumnJson>> value) {
        ColumnGroupJson columnGroupJson =  new ColumnGroupJson();

        columnGroupJson.name = value.getKey();
        for (int i = 0; i < value.getValue().size(); i++) {
            if (i == 0) {
                columnGroupJson.groupColumns = new ColumnDTO[value.getValue().size()];
            }
            columnGroupJson.groupColumns[i] = new ColumnDTO(value.getValue().get(i));

        }

        GroupDTO group = new GroupDTO(columnGroupJson.name, columnGroupJson.columnGroupId, Objects.requireNonNull(columnGroupJson.groupColumns));

        for (int j = 0; j < Objects.requireNonNull(columnGroupJson.groupColumns).length; j++) {
            value.getValue().get(j).addColumnGroup(group);
        }

        return columnGroupJson;
    }



    public static ColumnGroupJson buildAirColumnGroupJson(Map.Entry<String, List<AirColumnJson>> value) {
// were adding the group DTO to the Column Jsons and the ColumnDTOs to the ColumnGroups
        ColumnGroupJson columnGroupJson =  new ColumnGroupJson();

        columnGroupJson.name = value.getKey();
        for (int i = 0; i < value.getValue().size(); i++) {
            if (i == 0) {
                columnGroupJson.groupColumns = new ColumnDTO[value.getValue().size()];
            }
            columnGroupJson.groupColumns[i] = new ColumnDTO(value.getValue().get(i));

        }

        GroupDTO group = new GroupDTO(columnGroupJson.name, columnGroupJson.columnGroupId, Objects.requireNonNull(columnGroupJson.groupColumns));

        for (int j = 0; j < Objects.requireNonNull(columnGroupJson.groupColumns).length; j++) {
            value.getValue().get(j).addColumnGroup(group);
        }

        return columnGroupJson;
    }

    public static ColumnGroupJson buildColumnGroupJson(Map.Entry<String, List<ColumnJson>> value) {
// were adding the group DTO to the Column Jsons and the ColumnDTOs to the ColumnGroups
        ColumnGroupJson columnGroupJson =  new ColumnGroupJson();

        columnGroupJson.name = value.getKey();
        for (int i = 0; i < value.getValue().size(); i++) {
            if (i == 0) {
                columnGroupJson.groupColumns = new ColumnDTO[value.getValue().size()];
            }
            columnGroupJson.groupColumns[i] = new ColumnDTO(value.getValue().get(i));

        }

        GroupDTO group = new GroupDTO(columnGroupJson.name, columnGroupJson.columnGroupId, Objects.requireNonNull(columnGroupJson.groupColumns));

        for (int j = 0; j < Objects.requireNonNull(columnGroupJson.groupColumns).length; j++) {
            value.getValue().get(j).addColumnGroup(group);
        }
        return columnGroupJson;


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
