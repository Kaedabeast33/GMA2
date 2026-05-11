package org.example.JsonBuilder.json;

import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.JsonBuilder.json.ma.AiMAJson;
import org.example.JsonBuilder.json.ma.AirMAJson;
import org.example.JsonBuilder.json.ma.MAJson;

import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.AiColumnJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.bank.commonValues.Identifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GMAJson {



    String name;
    String description;
    String[] tags;
    String gmaId = "gma" + UUID.randomUUID();
    Map<String, String> gmaSettings;

    List<MAJson> ma;
    List<AirMAJson> airMa;
    List<AiMAJson> aiMa;

    SetJson[] sets;
    QueryGroupJson[] queryGroups;

    public List<AirMAJson> getAirMa() {
        return airMa;
    }

    public void setAirMa(List<AirMAJson> airMa) {
        this.airMa = airMa;
    }

    public void addGroup(QueryGroupJson group) {
        if (queryGroups == null) {
            queryGroups = new QueryGroupJson[]{group};
        } else {
            QueryGroupJson[] newGroups = new QueryGroupJson[queryGroups.length + 1];
            System.arraycopy(queryGroups, 0, newGroups, 0, queryGroups.length);
            newGroups[queryGroups.length] = group;
            queryGroups = newGroups;
        }
    }

    public GMAJson(KdbGma gma) {
        this.name = gma.getName();
        this.description = gma.getDescription();
        this.tags = gma.getTags();
        this.gmaSettings = gma.getGmaSettings();
//        this.sets = gma.getSets();
//        this.groups = gma.getGroups();
    }

    public GMAJson() {
    }


    public <T> T getGmaObject(Identifier id, Class<T> type) {
        if (id.getMaName() == null) return null;

        MAJson idMa = ma.stream()
                .filter(m -> m.getName().equals(id.getMaName()))
                .findFirst()
                .orElse(null);
        System.out.println("Looking for MA with name: " + id.getMaName() + ", found: " + (idMa != null ? idMa.getName() : "null"));

        if (idMa == null) return null;

        // If caller wants MAJson
        if (type == MAJson.class) {
            return type.cast(idMa);
        }

        if (id.getTableName() == null) return null;

        Arrays.stream(idMa.getTables()).forEach(t-> System.out.println("MA " + idMa.getName() + " has table: " + t.getName()));
        TableJson idTable = Arrays.stream(idMa.getTables())
                .filter(t -> t.getName().equals(id.getTableName()))
                .findFirst()
                .orElse(null);

        if (idTable == null) return null;

        // If caller wants TableJson
        if (type == TableJson.class) {
            return type.cast(idTable);
        }

        if (id.getColumnName() == null) return null;

        ColumnJson idColumn = Arrays.stream(idTable.getColumns())
                .filter(c -> c.getName().equals(id.getColumnName()))
                .findFirst()
                .orElse(null);

        // If caller wants ColumnJson
        if (type == ColumnJson.class) {
            return type.cast(idColumn);
        }

        return null;
    }

//    public <T> T getGmaObjectAi(Identifier id, Class<T> type) {
//        if (id.getMaName() == null) return null;
//
//        AiMAJson idMa = aiMa.stream()
//                .filter(m -> m.getName().equals(id.getMaName()))
//                .findFirst()
//                .orElse(null);
//
//        if (idMa == null) return null;
//
//        // If caller wants MAJson
//        if (type == AiMAJson.class) {
//            return type.cast(idMa);
//        }
//
//        if (id.getTableName() == null) return null;
//
//        AiTableJson idTable = Arrays.stream(idMa.getTables())
//                .filter(t -> t.getName().equals(id.getTableName()))
//                .findFirst()
//                .orElse(null);
//
//        if (idTable == null) return null;
//
//        // If caller wants TableJson
//        if (type == AiTableJson.class) {
//            return type.cast(idTable);
//        }
//
//        if (id.getColumnName() == null) return null;
//
//        AiColumnJson idColumn = Arrays.stream(idTable.getColumns())
//                .filter(c -> c.getName().equals(id.getColumnName()))
//                .findFirst()
//                .orElse(null);
//
//        // If caller wants ColumnJson
//        if (type == ColumnJson.class) {
//            return type.cast(idColumn);
//        }
//
//        return null;
//    }

    //    public GMAJson(KdbGmaDb gma){
//
//    }


    public Map<String, String> getGmaSettings() {
        return gmaSettings;
    }

    public void setGmaSettings(Map<String, String> gmaSettings) {
        this.gmaSettings = gmaSettings;
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

    public String getGmaId() {
        return gmaId;
    }

    public void setGmaId(String gmaId) {
        this.gmaId = gmaId;
    }


    public List<MAJson> getMa() {
        return ma;
    }

    public void setMa(List<MAJson> ma) {
        this.ma = ma;
    }

    public SetJson[] getSets() {
        return sets;
    }

    public void setSets(SetJson[] sets) {
        this.sets = sets;
    }

    public QueryGroupJson[] getQueryGroups() {
        return queryGroups;
    }

    public void setQueryGroups(QueryGroupJson[] queryGroups) {
        this.queryGroups = queryGroups;
    }

    public static <T> T createBlankInstance(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                if (fieldType == String.class) {
                    field.set(instance, "");
                } else if (fieldType == String[].class) {
                    field.set(instance, new String[]{});
                } else if (fieldType == Integer.class || fieldType == int.class) {
                    field.set(instance, 0);
                } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                    field.set(instance, false);
                } else if (fieldType == Double.class || fieldType == double.class) {
                    field.set(instance, 0.0);
                } else if (fieldType == List.class) {
                    field.set(instance, new ArrayList<>());
                } else if (fieldType == Map.class) {
                    field.set(instance, new HashMap<>());
                } else if (fieldType.isArray()) {
                    field.set(instance, java.lang.reflect.Array.newInstance(
                            fieldType.getComponentType(), 0));
                }

            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create blank instance", e);
        }
    }

    public void setAiMa(List<AiMAJson> aiMAJsons) {
        this.aiMa = aiMAJsons;
    }

    public List<AiMAJson> getAiMa() {
        return aiMa;
    }
}
