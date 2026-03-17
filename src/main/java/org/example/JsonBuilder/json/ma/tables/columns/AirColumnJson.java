package org.example.JsonBuilder.json.ma.tables.columns;

import com.google.gson.Gson;
import org.example.JsonBuilder.json.ref.RefColumnJson;
import org.example.JsonBuilder.json.ref.ReferenceColumnJson;
import org.example.bank.Annotations.*;
import org.example.bank.OutputClassBank.KdbAirColumnPersona;
import org.example.bank.commonValues.Identifier;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static org.example.JsonBuilder.IDE.JsonBuilder.createGson;
import static org.example.bank.commonValues.ValueTypes.TYPE_MAP;

public class AirColumnJson implements KdbAirColumnPersona {
    String name;
    String description;
    String[] tags;
    String columnId = "col" + UUID.randomUUID();

    Identifier identifier;

    String kdbConverter;



    String defaultValue;

    String fieldType;

    GroupDTO[] columnGroups;







    public AirColumnJson(Identifier identifier, KdbColumn kdbColumn, Class<?> fieldType
    ) {
//        set Values to annotation values




        this.name = kdbColumn.name();
        this.identifier = new Identifier(identifier);
        this.identifier.setColumnName(this.name);
        this.description = kdbColumn.description();
        this.tags = kdbColumn.tags();



        this.fieldType = fieldType.getSimpleName();
//        System.out.println("9999"+this.type +this.name+this.fieldType);
        this.defaultValue =  kdbColumn.defaultValue();

        this.kdbConverter = kdbColumn.converter().getName();

//        Create RefColumnJson [] using the  KdbReference Annotation of a column definition passed into it which converts it from gson

    }

    public AirColumnJson(ColumnDTO column) {
        this.name = column.getName();
        this.description = column.getDescription();
        this.tags = column.getTags();
        this.columnId = column.getColumnId();

        this.fieldType = column.getFieldType();
        this.defaultValue = column.getDefaultValue();




    }

    public AirColumnJson() {

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

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getKdbConverter() {
        return kdbConverter;
    }

    public void setKdbConverter(String kdbConverter) {
        this.kdbConverter = kdbConverter;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return "ColumnJson{" +
                "name='" + name + "' ->" +
                "ma_name='" + identifier.getMaName() + "' ->" +
                "table_name='" + identifier.getTableName() + "' ->" +
//                ", description='" + description + '\'' +
//                ", tags=" + Arrays.toString(tags) +
//                ", columnId='" + columnId + '\'' +
//                ", isNullable=" + isNullable +
//                ", isEditable=" + isEditable +
//                ", columnGroups=" + Arrays.toString(columnGroups) +
//                ", unique=" + unique +
//                ", uniqueIdentifier=" + uniqueIdentifier +
//                ", uniqueIdentifierGroups=" + Arrays.toString(uniqueIdentifierGroups) +
//                ", isRequired=" + isRequired +
//                ", type='" + type + '\'' +
//                ", defaultValue='" + defaultValue + '\'' +
//                ", index=" + index +
//                ", referenceColumn=" + referenceColumn +
//                ", isPrimaryKey=" + isPrimaryKey +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnJson that)) return false;
        return Objects.equals(name, that.name) &&
                Objects.equals(identifier.getMaName(), that.identifier.getMaName()) &&
                Objects.equals(identifier.getTableName(), that.identifier.getTableName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, identifier.getMaName(), identifier.getTableName());
    }


    public void addColumnGroup(GroupDTO groupDTO) {
        if (this.columnGroups == null) {
            this.columnGroups = new GroupDTO[]{groupDTO};
        } else {
            GroupDTO[] newColumnGroups = Arrays.copyOf(this.columnGroups, this.columnGroups.length + 1);
            newColumnGroups[newColumnGroups.length - 1] = groupDTO;
            this.columnGroups = newColumnGroups;
        }

    }

    public GroupDTO[] getColumnGroups() {
        return columnGroups;
    }

    public void setColumnGroups(GroupDTO[] columnGroups) {
        this.columnGroups = columnGroups;
    }
}
