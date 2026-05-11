//package org.example.JsonBuilder.json.ma.tables;
//
//import org.example.JsonBuilder.json.ma.tables.columns.AiColumnJson;
//import org.example.JsonBuilder.json.ma.tables.columns.AiColumnJson;
//import org.example.JsonBuilder.json.ma.tables.columns.ColumnGroupJson;
//import org.example.bank.Annotations.*;
//import org.example.bank.Annotations.ai.AiFieldsComb;
//import org.example.bank.Annotations.ai.AiMAComb;
//
//import org.example.bank.Annotations.ai.KdbAiColumn;
//import org.example.bank.Annotations.ai.KdbAiPrimaryKey;
//import org.example.bank.commonValues.Identifier;
//
//import java.util.*;
//
//public class AiTableJson {
//    String name;
//    String description;
//    String[] tags;
//    String tableId;
//    String tableType;
//
//
//    Identifier identifier;
//
//
//
//    AiColumnJson[] columns;
//    ColumnGroupJson[] columnGroups;
//
////    UniqueColumnGroupJson[] uniqueColumnGroups;
//
////    IndexJson[] indexes;
////    QueryJson[] tableQueries;
////    ProcedureJson[] tableProcedures;
////    TriggerJson[] triggers;
////    CustomContraintJson[] customConstraints;
////    UniqueKeyJson[] uniqueKeys;
//
//
//
//    private transient Map<String, List<BaseQueryJson>> queriesMap = new HashMap<>();
//
//
//
//
//    public AiTableJson(Identifier identifier, AiMAComb table)  {
//
//        this.name = table.getName();
//
//
//        this.description = table.getDescription();
//        this.tags = table.getTags();
//        this.tableType ="ai";
//        List<AiFieldsComb> fields = table.getFieldsComb();
//
//
//        this.identifier = new Identifier(identifier);
//        this.identifier.setTableName(this.name);
//        this.columns = new AiColumnJson[fields.size()];
//        this.columnGroups = new ColumnGroupJson[fields.size()];
//
//
//
//
//        Map<String, List<AiColumnJson>> groupColumnMap = new HashMap<>();
//
//
//
//        for (int i = 0; i < fields.size(); i++) {
//            AiFieldsComb field = fields.get(i);
//
//            KdbAiColumn kdbColumn = field.getKdbColumn();
//            KdbAiPrimaryKey kdbAiPrimaryKey  = field.getKdbPrimaryKey();
//
//            Class<?> fieldType = field.getFieldType();
//
//
//            if (kdbColumn != null) {
////                set each annotated Fields into the Columns Json[]
//                columns[i] = new AiColumnJson(new Identifier(this.identifier),kdbColumn, fieldType);
//
//
//
//
//                try {
////                    add each ColumnsJson Object to groupColumnMaps by adding it to the list associated to the default key->
//                    List<AiColumnJson> value;
//                    String name;
//                    name = "default";
//                    if ((value = groupColumnMap.get(name)) == null) {
//                        value = new ArrayList<>();
//                        value.add(columns[i]);
//                        groupColumnMap.put(name, value);
//
//                    } else {
//                        value.add(columns[i]);
//                    }
//                    for (String groupName : kdbColumn.columnGroupNames()) {
////                        add each ColumnsJson Object to groupColumnMaps by adding it to the list associated to the group name key
//                        if ((value = groupColumnMap.get(groupName)) == null) {
//                            value = new ArrayList<>();
//                            value.add(columns[i]);
//                            groupColumnMap.put(groupName, value);
//                        } else {
//                            value.add(columns[i]);
//                            ;
//                        }
//                    }
//                } catch (Exception e) {
//                    System.out.println("group Names ERROR");
//                    throw (e);
//
//
//                }
//
//
//
//
//
//            }
//        }
//
//
//
//
//
//
//
//
//
////        for(Map.Entry<String, List<BaseQueryJson>> entry : queriesMap.entrySet()) {
////            String key = entry.getKey();
////            List<BaseQueryJson> value = entry.getValue();
////            for(BaseQueryJson query : value) {
////
////            }
////            System.out.println("Query Group: " + key + " -> " + value);
////        }
//        this.columns = Arrays.stream(this.columns)
//                .filter(Objects::nonNull)
//                .toArray(AiColumnJson[]::new);
//
//
//
//        this.columnGroups = Arrays.stream(this.columnGroups)
//                .filter(Objects::nonNull)
//                .toArray(ColumnGroupJson[]::new);
//
//        int k = 0;
//        k = 0;
//        this.columnGroups = new ColumnGroupJson[groupColumnMap.size()];
//        for (Map.Entry<String, List<AiColumnJson>> entry : groupColumnMap.entrySet()) {
//            this.columnGroups[k++] = ColumnGroupJson.buildAiColumnGroupJson(entry);
//
//        }
//
//    }
//
//    public AiTableJson() {
//
//    }
//
//    public Object[] filterNulls(Object[] array) {
//        return Arrays.stream(array).filter(Objects::nonNull).toArray();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String[] getTags() {
//        return tags;
//    }
//
//    public void setTags(String[] tags) {
//        this.tags = tags;
//    }
//
//    public String getTableId() {
//        return tableId;
//    }
//
//    public void setTableId(String tableId) {
//        this.tableId = tableId;
//    }
//
//    public String getTableType() {
//        return tableType;
//    }
//
//    public void setTableType(String tableType) {
//        this.tableType = tableType;
//    }
//
//    public Identifier getIdentifier() {
//        return identifier;
//    }
//
//    public void setIdentifier(Identifier identifier) {
//        this.identifier = identifier;
//    }
//
//    public AiColumnJson[] getColumns() {
//        return columns;
//    }
//
//    public void setColumns(AiColumnJson[] columns) {
//        this.columns = columns;
//    }
//
//    public ColumnGroupJson[] getColumnGroups() {
//        return columnGroups;
//    }
//
//    public void setColumnGroups(ColumnGroupJson[] columnGroups) {
//        this.columnGroups = columnGroups;
//    }
//
//    public Map<String, List<BaseQueryJson>> getQueriesMap() {
//        return queriesMap;
//    }
//
//    public void setQueriesMap(Map<String, List<BaseQueryJson>> queriesMap) {
//        this.queriesMap = queriesMap;
//    }
//
//    @Override
//    public String toString() {
//        return "AiTableJson{" +
//                "name='" + name + '\'' +
//                "ma_name='" + identifier.getMaName() + '\'' +
//                '}';
//    }
//
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AiTableJson tableJson = (AiTableJson) o;
//        return Objects.equals(name, tableJson.name) && Objects.equals(identifier.getMaName(), tableJson.identifier.getMaName() );
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(name,identifier.getMaName());
//
//
//        return result;
//    }
//}
