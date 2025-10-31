package org.example.jsonBuilder.gma.ma.tables.columns;



import com.google.gson.Gson;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbReference;

import org.example.jsonBuilder.gma.ref.RefColumnJson;

import org.example.jsonBuilder.gma.ref.ReferenceColumnJson;


import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static org.example.CommonValues.ValueTypes.TYPE_MAP;
import static org.example.jsonBuilder.builder.JsonBuilder.createGson;

public class ColumnJson {
    String name;
    String description;
    String[] tags;
    String columnId= "col"+UUID.randomUUID();

    boolean isNullable;
    boolean isEditable;
    GroupDTO[] columnGroups;
    boolean unique;
    boolean uniqueIdentifier;
    GroupDTO[] uniqueIdentifierGroups;
    boolean isRequired;
    String type;
    String defaultValue;
    boolean isIndex ;
    GroupDTO [] indexGroups;
    ReferenceColumnJson referenceColumn;
    boolean isPrimaryKey;
    String fieldType;
    String kdbConverter;



    public ColumnJson(KdbColumn kdbColumn, KdbPrimaryKey kdbPrimaryKey, KdbIndex kdbIndex, KdbReference kdbReference,Class<?> fieldType
                      )  {
//        set Values to annotation values
        Gson gson = createGson();
        this.name = kdbColumn.name();
        this.description = kdbColumn.description();
        this.tags = kdbColumn.tags();
        this.isNullable = kdbPrimaryKey != null ? false:  kdbColumn.isNullable();
        this.isEditable = kdbColumn.isEditable();

//        this.columnGroups = new RefColumnGroupJson[kdbColumn.columnGroupNames().length];
//        for (int i=0;i<kdbColumn.columnGroupNames().length;i++){
//            columnGroups[i] = new RefColumnGroupJson(kdbColumn.columnGroupNames()[i]);
//        }
        this.unique = kdbPrimaryKey != null?true : kdbColumn.unique();
        this.uniqueIdentifier = kdbColumn.uniqueIdentifier();
//        this.uniqueIdentifierGroups = new RefUniqueColumnGroupJson[kdbColumn.uniqueIdentifierGroupNames().length];
//        for (int i=0;i<kdbColumn.uniqueIdentifierGroupNames().length;i++){
//            uniqueIdentifierGroups[i] = new RefUniqueColumnGroupJson(kdbColumn.uniqueIdentifierGroupNames()[i]);
//        }
        this.isRequired = kdbPrimaryKey != null || kdbColumn.isRequired();

        this.type = !Objects.equals(kdbColumn.type(), "default") ? kdbColumn.type(): TYPE_MAP.get(fieldType);
        this.fieldType = fieldType.getSimpleName();
        this.defaultValue = kdbColumn.defaultValue();

        if (kdbIndex != null) {

            this.isIndex =true;




        }else{
            if(kdbPrimaryKey!=null){
                this.isIndex = true;
            }
        }
//        Create RefColumnJson [] using the  KdbReference Annotation of a column definition passed into it which converts it from gson
        if(kdbReference !=null){

            RefColumnJson[] refColumn = new RefColumnJson[kdbReference.referenceColumns().length];
            for (int i=0;i<kdbReference.referenceColumns().length;i++){
                refColumn[i] = gson.fromJson(kdbReference.referenceColumns()[i],RefColumnJson.class);
            }
            this.referenceColumn = new ReferenceColumnJson(kdbReference.isForeignKey(),refColumn,kdbReference.cascadeRule());
        }
        if(kdbPrimaryKey != null) {
            this.isPrimaryKey = true;
            this.isRequired = true;
        }
        this.kdbConverter = kdbColumn.converter().getName();

    }

    public ColumnJson(ColumnDTO column) {
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
        this.fieldType = column.getFieldType();




    }

    public ColumnJson() {

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

    public String getKdbConverter() {
        return kdbConverter;
    }

    public void setKdbConverter(String kdbConverter) {
        this.kdbConverter = kdbConverter;
    }

    public ReferenceColumnJson getReferenceColumn() {
        return referenceColumn;
    }

    public void setReferenceColumn(ReferenceColumnJson referenceColumn) {
        this.referenceColumn = referenceColumn;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
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

    public GroupDTO[] getColumnGroups() {
        return columnGroups;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public void setColumnGroups(GroupDTO[] columnGroups) {
        this.columnGroups = columnGroups;
    }

    public GroupDTO[] getUniqueIdentifierGroups() {
        return uniqueIdentifierGroups;
    }

    public void setUniqueIdentifierGroups(GroupDTO[] uniqueIdentifierGroups) {
        this.uniqueIdentifierGroups = uniqueIdentifierGroups;
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

    public GroupDTO[] getIndexGroups() {
        return indexGroups;
    }

    public void setIndexGroups(GroupDTO[] indexGroups) {
        this.indexGroups = indexGroups;
    }

    @Override
    public String toString() {
        return "ColumnJson{" +
                "name='" + name + '\'' +
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


    public void addColumnGroup(GroupDTO groupDTO) {
        if (this.columnGroups == null) {
            this.columnGroups = new GroupDTO[]{groupDTO};
        } else {
            GroupDTO[] newColumnGroups = Arrays.copyOf(this.columnGroups, this.columnGroups.length + 1);
            newColumnGroups[newColumnGroups.length - 1] = groupDTO;
            this.columnGroups = newColumnGroups;
        }

    }

    public void addUniqueColumnGroup(GroupDTO groupDTO){
        if (this.uniqueIdentifierGroups == null) {
            this.uniqueIdentifierGroups = new GroupDTO[]{groupDTO};
        } else {
            GroupDTO[] newUniqueIdentifierGroups = Arrays.copyOf(this.uniqueIdentifierGroups, this.uniqueIdentifierGroups.length + 1);
            newUniqueIdentifierGroups[newUniqueIdentifierGroups.length - 1] = groupDTO;
            this.uniqueIdentifierGroups = newUniqueIdentifierGroups;
        }
    }


    public void addIndex(GroupDTO group) {
        if(this.indexGroups == null){
            this.indexGroups = new GroupDTO[]{group};
        }else{
            GroupDTO[] newIndexGroups = Arrays.copyOf(this.indexGroups, this.indexGroups.length + 1);
            newIndexGroups[newIndexGroups.length - 1] = group;
            this.indexGroups = newIndexGroups;
        }
    }

    public boolean getPrimaryKey() {
        return isPrimaryKey;
    }
}
