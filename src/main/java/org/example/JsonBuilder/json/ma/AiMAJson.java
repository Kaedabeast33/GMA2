package org.example.JsonBuilder.json.ma;

import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.ClassOutputCreator.templates.ai.AiColumnTemplate;

import org.example.JsonBuilder.json.ma.tables.AirTableJson;
import org.example.JsonBuilder.json.ma.tables.columns.AiColumnJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnGroupJson;
import org.example.bank.Annotations.KdbKey;
import org.example.bank.Annotations.ai.*;

import org.example.bank.commonValues.Identifier;
import org.example.bank.db.contextObj.match.JsonInterface;

import java.util.*;

public class AiMAJson implements JsonInterface {

    String name;
    String custom;
    String description;

    String[] tags;
    String maId = "ma" + UUID.randomUUID();
    String javaFolderPath;

    String[] uploadTypes;


    Identifier identifier;

    String schema;





    AiColumnJson[] primaryKeys;
    AiColumnJson[] keys;
    AiColumnJson[] cols;












    public AiMAJson(AiMAComb aiMAComb, Identifier identifier) {
        KdbAi kdbAi = aiMAComb.getKdbAi();
        this.name = aiMAComb.getName();
        this.custom = kdbAi.custom();
        this.uploadTypes = kdbAi.uploadTypes();
        this.schema = kdbAi.schema();


        this.name = aiMAComb.getName();


        this.description = aiMAComb.getDescription();
        this.tags = aiMAComb.getTags();

        List<AiFieldsComb> primaryKeyFields = aiMAComb.getFieldsComb().stream().filter(f -> f.getKdbPrimaryKey() != null).toList();
        List<AiFieldsComb> keyFields = aiMAComb.getFieldsComb().stream().filter(f -> f.getKdbAiKey() != null).toList();
        List<AiFieldsComb> fields = aiMAComb.getFieldsComb().stream().filter(f -> f.getKdbAiKey() == null && f.getKdbPrimaryKey() == null).toList();

//        System.out.println(keyFields.size()+" KEYS FIELDS SIZE");


        this.identifier = new Identifier(identifier);
        this.identifier.setTableName(this.name);

        this.primaryKeys = new AiColumnJson[primaryKeyFields.size()];
        this.keys = new AiColumnJson[keyFields.size()];
        this.cols = new AiColumnJson[fields.size()];


        for (int i = 0; i < primaryKeys.length; i++) {
            AiFieldsComb field = primaryKeyFields.get(i);
            KdbAiColumn kdbColumn = field.getKdbColumn();
            KdbAiPrimaryKey kdbAiPrimaryKey = field.getKdbPrimaryKey();

            Class<?> fieldType = field.getFieldType();
            if (kdbColumn != null) {
//                set each annotated Fields into the Columns Json[]
                primaryKeys[i] = new AiColumnJson(new Identifier(this.identifier), kdbColumn, fieldType);
                primaryKeys[i].setPrimaryKey(true);

            }
        }

        for (int i = 0; i < keys.length; i++) {
            AiFieldsComb field = keyFields.get(i);
            KdbAiColumn kdbColumn = field.getKdbColumn();
            KdbAiKey kdbAiKey = field.getKdbAiKey();

            Class<?> fieldType = field.getFieldType();
            if (kdbColumn != null) {
//                set each annotated Fields into the Columns Json[]
                keys[i] = new AiColumnJson(new Identifier(this.identifier), kdbColumn, fieldType);
                keys[i].setKey(true);

            }
        }

        for (int i = 0; i < cols.length; i++) {
            AiFieldsComb field = fields.get(i);
            KdbAiColumn kdbColumn = field.getKdbColumn();

            Class<?> fieldType = field.getFieldType();
            if (kdbColumn != null) {
                cols[i] = new AiColumnJson(new Identifier(this.identifier), kdbColumn, fieldType);
            }


        }
    }

    public List<AiColumnJson> getAllCols(){
        List<AiColumnJson> allCols = new ArrayList<>();
        if (primaryKeys != null) {
            allCols.addAll(Arrays.asList(primaryKeys));
        }
        if (keys != null) {
            allCols.addAll(Arrays.asList(keys));
        }
        if (cols != null) {
            allCols.addAll(Arrays.asList(cols));
        }
        return allCols;
    }

    public List<AiColumnJson> getAllKeys(){
        List<AiColumnJson> allKeys = new ArrayList<>();
        if (primaryKeys != null) {
            allKeys.addAll(Arrays.asList(primaryKeys));
        }
        if (keys != null) {
            allKeys.addAll(Arrays.asList(keys));
        }
        return allKeys;
    }


    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String[] getUploadTypes() {
        return uploadTypes;
    }

    public void setUploadTypes(String[] uploadTypes) {
        this.uploadTypes = uploadTypes;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public boolean equals(Object o){
        if(this ==o) return true;
        if(!(o instanceof AiMAJson)) return false;
        AiMAJson maJson = (AiMAJson) o;
        return Objects.equals(name,maJson.name);

    }

    @Override
    public int hashCode(){
        return Objects.hash(identifier.getMaName());
    }

    @Override
    public String toString() {
        return "MAJson{name=" + name + '}';
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

    public String getMaId() {
        return maId;
    }

    public void setMaId(String maId) {
        this.maId = maId;
    }

    public String getJavaFolderPath() {
        return javaFolderPath;
    }

    public void setJavaFolderPath(String javaFolderPath) {
        this.javaFolderPath = javaFolderPath;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }



    public AiColumnJson[] getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(AiColumnJson[] primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public AiColumnJson[] getKeys() {
        return keys;
    }

    public AiColumnJson[] getCols() {
        return cols;
    }

    public void setKeys(AiColumnJson[] keys) {
        this.keys = keys;
    }
}
