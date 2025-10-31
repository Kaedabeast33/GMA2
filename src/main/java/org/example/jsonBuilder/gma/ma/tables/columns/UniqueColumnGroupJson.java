package org.example.jsonBuilder.gma.ma.tables.columns;

import org.example.jsonBuilder.gma.SetJson;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UniqueColumnGroupJson {
    String name;

    String uniqueColumnGroupId = "ucolg" + java.util.UUID.randomUUID();

    ColumnDTO [] columns;


    public UniqueColumnGroupJson(Map.Entry<String, List<ColumnJson>> entry) {
        this.name = entry.getKey();

        for(int i = 0; i < entry.getValue().size(); i++) {
            if (i == 0) {
                this.columns = new ColumnDTO[entry.getValue().size()];
            }
            this.columns[i] = new ColumnDTO(entry.getValue().get(i)) ;
        }
        GroupDTO group = new GroupDTO(this.name,this.uniqueColumnGroupId,this.columns);

        for(int j = 0; j < Objects.requireNonNull(this.columns).length; j++) {
            entry.getValue().get(j).addUniqueColumnGroup(group);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueColumnGroupId() {
        return uniqueColumnGroupId;
    }

    public void setUniqueColumnGroupId(String uniqueColumnGroupId) {
        this.uniqueColumnGroupId = uniqueColumnGroupId;
    }

    public ColumnDTO[] getColumns() {
        return columns;
    }

    public void setColumns(ColumnDTO[] columns) {
        this.columns = columns;
    }
}
