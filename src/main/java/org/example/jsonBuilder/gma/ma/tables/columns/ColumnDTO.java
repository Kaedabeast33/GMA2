package org.example.jsonBuilder.gma.ma.tables.columns;

import org.example.jsonBuilder.gma.ref.RefColumnGroupJson;
import org.example.jsonBuilder.gma.ref.RefUniqueColumnGroupJson;
import org.example.jsonBuilder.gma.ref.ReferenceColumnJson;

import java.util.Arrays;
import java.util.UUID;

public class ColumnDTO {
    String name;
    String description;
    String[] tags;
    String columnId;

    boolean isNullable;
    boolean isEditable;

    boolean unique;
    boolean uniqueIdentifier;

    boolean isRequired;
    String type;
    String defaultValue;
    boolean isIndex;

    boolean isPrimaryKey;

    String fieldType;

    public ColumnDTO(ColumnJson column) {
//        System.out.println("DTO COLUMN NAME: " + column.getName());
        this.name = column.getName();
        this.description = column.getDescription();
        this.tags = column.getTags();
        this.columnId = column.getColumnId();
        this.isNullable = column.isNullable();
        this.isEditable = column.isEditable();
        this.unique = column.isUnique();
        this.uniqueIdentifier = column.isUniqueIdentifier();
        this.isRequired = column.isRequired();
        this.type = column.getType();
        this.defaultValue = column.getDefaultValue();
        this.isIndex = column.isIndex();
        this.isPrimaryKey = column.isPrimaryKey();
        this.fieldType = column.getFieldType();



    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
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

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(boolean uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isIndex() {
        return isIndex;
    }

    public void setIndex(boolean index) {
        isIndex = index;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "ColumnDTO{" +
                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", tags=" + Arrays.toString(tags) +
//                ", columnId='" + columnId + '\'' +
//                ", isNullable=" + isNullable +
//                ", isEditable=" + isEditable +
//                ", unique=" + unique +
//                ", uniqueIdentifier=" + uniqueIdentifier +
//                ", isRequired=" + isRequired +
//                ", type='" + type + '\'' +
//                ", defaultValue='" + defaultValue + '\'' +
//                ", index=" + index +
//                ", isPrimaryKey=" + isPrimaryKey +
                '}';
    }
}
