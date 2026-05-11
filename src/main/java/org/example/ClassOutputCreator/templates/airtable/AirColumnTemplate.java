package org.example.ClassOutputCreator.templates.airtable;

import org.example.bank.JsonBuilderRef.EntityValue;
import org.example.bank.KdbConverter.DefaultKdbConverter;
import org.example.bank.KdbConverter.KdbConverter;
import org.example.bank.KdbConverter.KdbConverterFactory;
import org.example.bank.OutputClassBank.KdbAirColumnPersona;

import java.util.List;
import java.util.Objects;

public abstract class AirColumnTemplate implements KdbAirColumnPersona {
    String name;
    String columnId;
    String description;
    String[] tags;
    Class<?> fieldType;
    String defaultValue;
    String[] columnGroups;
    private EntityValue<?> entityValue;
    private List<String> queryMatchString;
    private Class<?> kdbConverter;



    public AirColumnTemplate(String name, String columnId, String description, String[] tags, Class<?> fieldtype, String defaultValue, String[] columnGroups, Class<?> kdbConverter) {
        this.name = name;
        this.columnId = columnId;
        this.description = description;
        this.tags = tags;
        this.fieldType = fieldtype;
        this.defaultValue = defaultValue;
        this.columnGroups = columnGroups;
        this.kdbConverter = kdbConverter;
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

    public String[] getColumnGroups() {
        return columnGroups;
    }

    public void setColumnGroups(String[] columnGroups) {
        this.columnGroups = columnGroups;
    }

    public EntityValue<?> getEntityValue() {
        //            throw new IllegalStateException("Entity value not set for column: " + this.name);
        return Objects.requireNonNullElseGet(entityValue, () -> new EntityValue<>(null, fieldType));
    }

    public void setEntityValue(Object entityValue) throws Exception {
        EntityValue<?> converted;


        if (this.kdbConverter == DefaultKdbConverter.class) {
            KdbConverter<Object, ?> converter = KdbConverterFactory.getConverter(this.fieldType);
            if (converter == null) {
                throw new IllegalStateException("No converter registered for type: " + this.fieldType);
            }
            converted = converter.convert(entityValue);
        } else {
            System.out.println("custom converter found: " + kdbConverter.getName());
            KdbConverter<Object, ?> converter =
                    (KdbConverter<Object, ?>) kdbConverter.getDeclaredConstructor().newInstance();
            converted = converter.convert(entityValue);
        }

        // Validate that the converted type matches fieldType
//        if (!fieldType.isAssignableFrom(converted.getType())) {
//            throw new IllegalArgumentException(
//                    "Converted value type " + converted.getType().getName() +
//                            " does not match fieldType " + fieldType.getName()
//            );
//        }

        this.entityValue = converted;
    }


    public List<String> getQueryMatchStrings() {
        return queryMatchString;
    }

    public AirColumnTemplate setQueryMatchStrings(List<String> queryMatchString) {
        this.queryMatchString = queryMatchString;
        return this;
    }

}
