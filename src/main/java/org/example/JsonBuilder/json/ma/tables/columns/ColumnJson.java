package org.example.JsonBuilder.json.ma.tables.columns;

import com.google.gson.Gson;
import org.example.JsonBuilder.json.ref.RefColumnJson;
import org.example.JsonBuilder.json.ref.ReferenceColumnJson;
import org.example.bank.Annotations.*;
import org.example.bank.JsonBuilderRef.EntityValue;
import org.example.bank.KdbConverter.DefaultKdbConverter;
import org.example.bank.KdbConverter.KdbConverter;
import org.example.bank.KdbConverter.KdbConverterFactory;
import org.example.bank.OutputClassBank.KdbColumnPersona;
import org.example.bank.commonValues.Identifier;
import org.springframework.boot.autoconfigure.gson.GsonProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.example.JsonBuilder.IDE.JsonBuilder.createGson;
import static org.example.bank.AppConfig.getJavaDir;
import static org.example.bank.commonValues.ValueTypes.FIELD_TYPE_MAP;
import static org.example.bank.commonValues.ValueTypes.TYPE_MAP;


public class ColumnJson implements KdbColumnPersona {
    String name;
    String description;
    String[] tags;
    String columnId = "col" + UUID.randomUUID();

    Identifier identifier;

    boolean isNullable;
    boolean isEditable;
    GroupDTO[] columnGroups;
    boolean unique;
    boolean uniqueIdentifier;
    GroupDTO[] uniqueIdentifierGroups;
    boolean isRequired;
    String type;
    String defaultValue;
    boolean isIndex;
    GroupDTO[] indexGroups;
    ReferenceColumnJson referenceColumn;
    boolean isPrimaryKey;
    String fieldType;
    String kdbConverter;
    boolean isEmbedding;
    EntityValue<?> entityValue;
    List<String> queryMatchStrings;


    private transient Gson gson = new Gson();

// java
// In file: `src/main/java/org/example/JsonBuilder/json/ma/tables/columns/ColumnJson.java`

    private Class<?> resolveClass(String typeName) throws ClassNotFoundException {
        // handle array types like "java.lang.Double[]" or "int[][]"
        if (typeName.endsWith("[]")) {
            String componentName = typeName.substring(0, typeName.length() - 2);
            Class<?> compClass = resolveClass(componentName);
            // create a zero-length array to obtain the array Class object
            return java.lang.reflect.Array.newInstance(compClass, 0).getClass();
        }

        // primitives
        return switch (typeName) {
            case "int" -> int.class;
            case "long" -> long.class;
            case "double" -> double.class;
            case "float" -> float.class;
            case "boolean" -> boolean.class;
            case "char" -> char.class;
            case "byte" -> byte.class;
            case "short" -> short.class;
            case "void" -> void.class;
            default -> Class.forName(typeName);
        };
    }

    // Replace usages of Class.forName(typeName) with resolveClass(typeName).
// Example replacement inside getEntityValue:
// original: Class<?> cls = Class.forName(typeName);
// updated:


    public ColumnJson setQueryMatchStrings(List<String> queryMatchStrings) {

        this.queryMatchStrings =    queryMatchStrings;

        return  gson.fromJson(gson.toJson(this), ColumnJson.class);
    }

    public EntityValue<?> getEntityValue() throws ClassNotFoundException {
        //            throw new IllegalStateException("Entity value not set for column: " + this.name);
        String className = FIELD_TYPE_MAP.get(this.fieldType).replace(".class", "");

// load the Class object
        Class<?> clazz = resolveClass(className);
//        System.out.println(clazz.getSimpleName() + " resolved for fieldType: " + this.fieldType);

        return Objects.requireNonNullElseGet(entityValue, () -> new EntityValue<>(null,clazz    ));
    }

    public void setEntityValue(Object entityValue) throws Exception {
        EntityValue<?> converted;



            KdbConverter<Object, ?> converter = KdbConverterFactory.getConverter(this.type);

            if (converter == null) {
                throw new IllegalStateException("No converter registered for type: " + this.type);
            }
            converted = converter.convert(entityValue);

        this.entityValue = converted;
    }



    public ColumnJson(Identifier identifier, KdbColumn kdbColumn, KdbPrimaryKey kdbPrimaryKey, KdbIndex kdbIndex, KdbReference kdbReference, KdbEmbedding kdbEmbedding, Class<?> fieldType
    ) {
//        set Values to annotation values
        Gson gson = createGson();

        if((kdbEmbedding!=null && kdbPrimaryKey!=null)||(kdbEmbedding!=null && kdbIndex!=null)||(kdbEmbedding!=null && kdbReference!=null)){
            throw new IllegalArgumentException("A column cannot be both a primary key or kdbIndex or kdbReference and an embedding column.");
        }

        this.name = kdbColumn.name();
        this.identifier = new Identifier(identifier);
        this.identifier.setColumnName(this.name);
        this.description = kdbColumn.description();
        this.tags = kdbColumn.tags();

        this.isNullable = kdbPrimaryKey != null ? false : kdbColumn.isNullable();
        this.isEditable = kdbColumn.isEditable();

//        this.columnGroups = new RefColumnGroupJson[kdbColumn.columnGroupNames().length];
//        for (int i=0;i<kdbColumn.columnGroupNames().length;i++){
//            columnGroups[i] = new RefColumnGroupJson(kdbColumn.columnGroupNames()[i]);
//        }
        this.unique = kdbColumn.unique();
        this.uniqueIdentifier = kdbColumn.uniqueIdentifier();
//        this.uniqueIdentifierGroups = new RefUniqueColumnGroupJson[kdbColumn.uniqueIdentifierGroupNames().length];
//        for (int i=0;i<kdbColumn.uniqueIdentifierGroupNames().length;i++){
//            uniqueIdentifierGroups[i] = new RefUniqueColumnGroupJson(kdbColumn.uniqueIdentifierGroupNames()[i]);
//        }
        this.isRequired = kdbPrimaryKey != null || kdbColumn.isRequired();

//        this.type =kdbEmbedding!=null?String.format("VECTOR(%s)",kdbEmbedding.size()): !Objects.equals(kdbColumn.type(), "default") ? kdbColumn.type() : TYPE_MAP.get(fieldType);
        this.type =kdbEmbedding!=null? "TEXT": !Objects.equals(kdbColumn.type(), "default") ? kdbColumn.type() : TYPE_MAP.get(fieldType);

        this.fieldType = fieldType.getSimpleName();
//        System.out.println("9999"+this.type +this.name+this.fieldType);
        this.defaultValue = kdbEmbedding!=null ? null: kdbColumn.defaultValue();

        if (kdbIndex != null) {

            this.isIndex = true;


        } else {
            if (kdbPrimaryKey != null) {
                this.isIndex = true;
            }
        }
//        Create RefColumnJson [] using the  KdbReference Annotation of a column definition passed into it which converts it from gson
        if (kdbReference != null) {

            RefColumnJson[] refColumn = new RefColumnJson[kdbReference.referenceColumns().length];
            for (int i = 0; i < kdbReference.referenceColumns().length; i++) {
                refColumn[i] = gson.fromJson(kdbReference.referenceColumns()[i], RefColumnJson.class);
            }
            this.referenceColumn = new ReferenceColumnJson(kdbReference.isForeignKey(), refColumn, kdbReference.cascadeRule());
        }
        if (kdbPrimaryKey != null) {
            this.isPrimaryKey = true;
            this.isRequired = true;
        }
        this.kdbConverter = kdbColumn.converter()!=null? kdbColumn.converter().getName(): getJavaDir()+ ".bank.KdbConverter.DefaultKdbConverter";
        this.isEmbedding = kdbEmbedding != null;

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
        this.defaultValue = column.getDefaultValue();
        this.isIndex = column.isIndex();
        this.isEmbedding = column.isEmbedding();



    }

    public ColumnJson() {

    }

    public boolean isEmbedding() {
        return isEmbedding;
    }

    public void setEmbedding(boolean embedding) {
        isEmbedding = embedding;
    }

    public String getName() {
        return name.replace(" ", "_").toLowerCase();
    }

    public String getRealName() {
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

    @Override
    public List<String> getQueryMatchStrings() {
        return this.queryMatchStrings;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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


    public void addColumnGroup(GroupDTO groupDTO) {
        if (this.columnGroups == null) {
            this.columnGroups = new GroupDTO[]{groupDTO};
        } else {
            GroupDTO[] newColumnGroups = Arrays.copyOf(this.columnGroups, this.columnGroups.length + 1);
            newColumnGroups[newColumnGroups.length - 1] = groupDTO;
            this.columnGroups = newColumnGroups;
        }

    }

    public void addUniqueColumnGroup(GroupDTO groupDTO) {
        if (this.uniqueIdentifierGroups == null) {
            this.uniqueIdentifierGroups = new GroupDTO[]{groupDTO};
        } else {
            GroupDTO[] newUniqueIdentifierGroups = Arrays.copyOf(this.uniqueIdentifierGroups, this.uniqueIdentifierGroups.length + 1);
            newUniqueIdentifierGroups[newUniqueIdentifierGroups.length - 1] = groupDTO;
            this.uniqueIdentifierGroups = newUniqueIdentifierGroups;
        }
    }


    public void addIndex(GroupDTO group) {
        if (this.indexGroups == null) {
            this.indexGroups = new GroupDTO[]{group};
        } else {
            GroupDTO[] newIndexGroups = Arrays.copyOf(this.indexGroups, this.indexGroups.length + 1);
            newIndexGroups[newIndexGroups.length - 1] = group;
            this.indexGroups = newIndexGroups;
        }
    }

    public boolean getPrimaryKey() {
        return isPrimaryKey;
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
