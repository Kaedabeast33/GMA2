package org.example.gma.templates;

import org.example.CommonValues.DefaultKdbConverter;
import org.example.CommonValues.EntityValue;
import org.example.CommonValues.KdbConverter.KdbConverter;
import org.example.CommonValues.KdbConverter.KdbConverterFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public abstract class ColumnTemplate {

    protected final String name;
    protected final String colId;
    protected final String description;
    protected final String[] tag;

    protected final boolean isNullable;
    protected final boolean isEditable;
    protected final boolean unique;
    protected final boolean isRequired;
    protected final String type;
    protected final Object defaultValue;

    protected final String[] columnGroups;
    protected final boolean uniqueIdentifier;
    protected final String[] uniqueIdentifierGroupNames;

    protected final boolean isIndex;
    protected final String[] indexGroups;
//
    protected final List<ColumnTemplate> referenceColumns;

    protected Class<?> fieldType;

    private EntityValue<?> entityValue;

    private Class<?> kdbConverter;





    public ColumnTemplate(String name, String colId,
                          String description, String[] tag, boolean isNullable, boolean isEditable,
                          boolean unique, boolean isRequired, String type, Object defaultValue,
                          String[] columnGroups, boolean uniqueIdentifier,
                          String[] uniqueIdentifierGroupNames, boolean isIndex
                          , String[] indexGroups, List<ColumnTemplate> referenceColumns, Class<?> fieldType, Class<?> kdbConverter
    ) {

        this.name = name;
        this.colId = colId;
        this.description = description;
        this.tag = tag;
        this.isNullable = isNullable;
        this.isEditable = isEditable;
        this.unique = unique;
        this.isRequired = isRequired;
        this.type = type;
        this.defaultValue = defaultValue;
        this.columnGroups = columnGroups;
        this.uniqueIdentifier = uniqueIdentifier;
        this.uniqueIdentifierGroupNames = uniqueIdentifierGroupNames;
        this.isIndex = isIndex;
        this.indexGroups = indexGroups;
        this.referenceColumns = referenceColumns;
        this.fieldType = fieldType;
        this.kdbConverter = kdbConverter;



    }

    public Class<?> getFieldType() {
        return fieldType;
    }
//
//    public Class<KdbConverter> getKdbConverter() {
//        return kdbConverter;
//    }


    public EntityValue<?> getEntityValue() {
        //            throw new IllegalStateException("Entity value not set for column: " + this.name);
        return Objects.requireNonNullElseGet(entityValue, () -> new EntityValue<>(null, fieldType));
    }

    public void setEntityValue(Object entityValue) throws Exception {
        EntityValue<?> converted;


        if (this.kdbConverter == DefaultKdbConverter.class) {
            KdbConverter<Object, ?> converter = KdbConverterFactory.getConverter(this.type);
            if (converter == null) {
                throw new IllegalStateException("No converter registered for type: " + this.type);
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





    public String getName() {
        return name;
    }

    public String getColId() {
        return colId;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTag() {
        return tag;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String[] getColumnGroups() {
        return columnGroups;
    }

    public boolean isUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public String[] getUniqueIdentifierGroupNames() {
        return uniqueIdentifierGroupNames;
    }

    public boolean isIndex() {
        return isIndex;
    }

    public String[] getIndexGroups() {
        return indexGroups;
    }

    public List<ColumnTemplate> getReferenceColumns() {
        return referenceColumns;
    }
    public String getReference(){
        return String.format("""
                {
                                      "name": "%s",
                                      "columnId": "%s"
                }
                """,this.name,this.colId);


    }
}
