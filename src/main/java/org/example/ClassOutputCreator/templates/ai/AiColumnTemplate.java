package org.example.ClassOutputCreator.templates.ai;


import org.example.bank.JsonBuilderRef.EntityValue;
import org.example.bank.KdbConverter.KdbConverter;
import org.example.bank.KdbConverter.KdbConverterFactory;
import org.example.bank.OutputClassBank.KdbAiColumnPersona;

import java.util.List;
import java.util.Objects;

public abstract class AiColumnTemplate implements KdbAiColumnPersona {
    String name;
    String columnId;
    String description;
    String[] tags;
    Class<?> fieldType;
    String type;
    String defaultValue;

    boolean isPrimaryKey;
    boolean isKey;


    private EntityValue<?> entityValue;
    private List<String> queryMatchString;


    String [] uploadTypes;


    public AiColumnTemplate(String name, String columnId, String description, String[] tags, Class<?> fieldtype, String type, String defaultValue, boolean isKey, boolean isPrimaryKey) {
        this.name = name;
        this.columnId = columnId;
        this.description = description;
        this.tags = tags;
        this.fieldType = fieldtype;
        this.defaultValue = defaultValue;

        this.isKey = isKey;
        this.isPrimaryKey = isPrimaryKey;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String[] getUploadTypes() {
        return uploadTypes;
    }

    public void setUploadTypes(String[] uploadTypes) {
        this.uploadTypes = uploadTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
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

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }



    public void setEntityValueDirect(EntityValue<?> entityValue) {
        System.out.println("Setting entity value directly for column: " + this.name + " with value: " + entityValue.getValue()+" "+ entityValue.getType());
        this.entityValue = entityValue;
    }
    public EntityValue<?> getEntityValue() {
        //            throw new IllegalStateException("Entity value not set for column: " + this.name);
        return Objects.requireNonNullElseGet(entityValue, () -> new EntityValue<>(null, fieldType));
    }

    public AiColumnTemplate setEntityValue(Object entityValue) throws Exception {
        EntityValue<?> converted;

        System.out.println(this.fieldType+" "+entityValue+" "+entityValue.getClass());


            KdbConverter<Object, ?> converter = KdbConverterFactory.getConverter(this.fieldType);
            if (converter == null) {
                throw new IllegalStateException("No converter registered for type: " + this.fieldType);
            }
            converted = converter.convert(entityValue);



        // Validate that the converted type matches fieldType
//        if (!fieldType.isAssignableFrom(converted.getType())) {
//            throw new IllegalArgumentException(
//                    "Converted value type " + converted.getType().getName() +
//                            " does not match fieldType " + fieldType.getName()
//            );
//        }

        this.entityValue = converted;
        return this;
    }


    public List<String> getQueryMatchStrings() {
        return queryMatchString;
    }

    public AiColumnTemplate setQueryMatchStrings(List<String> queryMatchString) {
        this.queryMatchString = queryMatchString;
        return this;
    }
}
