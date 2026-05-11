package org.example.JsonBuilder.json.ma.tables.columns;

import org.example.bank.Annotations.KdbColumn;
import org.example.bank.Annotations.ai.AiFieldsComb;
import org.example.bank.Annotations.ai.KdbAiColumn;
import org.example.bank.OutputClassBank.KdbAiColumnPersona;
import org.example.bank.commonValues.Identifier;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static org.example.bank.commonValues.ValueTypes.TYPE_MAP;

public class AiColumnJson implements KdbAiColumnPersona {

        String name;
        String description;
        String[] tags;
        String columnId = "col" + UUID.randomUUID();

        Identifier identifier;


        String[] uploadTypes;
        String type;




        String defaultValue;

        String fieldType;

        boolean isPrimaryKey;
        boolean isKey;









        public AiColumnJson(Identifier identifier, KdbAiColumn kdbColumn, Class<?> fieldType
        ) {
//        set Values to annotation values




            this.name = kdbColumn.name();
            this.identifier = new Identifier(identifier);
            this.identifier.setColumnName(this.name);
            this.description = kdbColumn.description();
            this.tags = kdbColumn.tags();
            this.uploadTypes = kdbColumn.uploadTypes();
            this.type = !Objects.equals(kdbColumn.type(), "default") ? kdbColumn.type() : TYPE_MAP.get(fieldType);



            this.fieldType = fieldType.getSimpleName();
//        System.out.println("9999"+this.type +this.name+this.fieldType);
            this.defaultValue =  kdbColumn.defaultValue();




//        Create RefColumnJson [] using the  KdbReference Annotation of a column definition passed into it which converts it from gson

        }




    public String[] getUploadTypes() {
        return uploadTypes;
    }

    public void setUploadTypes(String[] uploadTypes) {
        this.uploadTypes = uploadTypes;
    }

    public AiColumnJson(ColumnDTO column) {
            this.name = column.getName();
            this.description = column.getDescription();
            this.tags = column.getTags();
            this.columnId = column.getColumnId();

            this.fieldType = column.getFieldType();
            this.defaultValue = column.getDefaultValue();




        }

        public AiColumnJson() {

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

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }


    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean key) {
        isKey = key;
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



}
