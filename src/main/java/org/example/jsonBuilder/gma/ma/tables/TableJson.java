package org.example.jsonBuilder.gma.ma.tables;

import org.example.Annotations.*;
import org.example.CommonValues.IndexMapObject;
import org.example.jsonBuilder.gma.ma.tables.columns.*;

import org.example.jsonBuilder.gma.ma.tables.dependencies.DependencyJson;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class TableJson {
    String name;
    String description;
    String[] tags;
    String tableId="tab"+ UUID.randomUUID();
    String tableType;

    DependencyJson[] dependencies;

    ColumnJson[] columns;
    ColumnGroupJson[] columnGroups;
    UniqueColumnGroupJson[] uniqueColumnGroups;

    IndexJson[] indexes;
    QueryJson[] tableQueries;
    ProcedureJson[] tableProcedures;
    TriggerJson[] triggers;
    CustomContraintJson[] customConstraints;
    UniqueKeyJson[] uniqueKeys;

    private transient Map<String, List<BaseQueryJson>> queriesMap = new HashMap<>();



    public TableJson(TableComb table) throws InvocationTargetException, IllegalAccessException {
        KdbTable tableData = table.getKdbTable();
        this.name = tableData.name();
        this.description = tableData.description();
        this.tags = tableData.tags();
        this.tableType = tableData.type();

        List<FieldsComb> fields = table.getFieldsComb();
        List<MethodsComb> methods = table.getMethodsComb();

        // Initialize arrays with the size of the fields and methods lists

        this.columns = new ColumnJson[fields.size()];
        this.indexes = new IndexJson[fields.size()];
        this.uniqueColumnGroups = new UniqueColumnGroupJson[fields.size()];
        this.columnGroups = new ColumnGroupJson[fields.size()];
        this.uniqueKeys = new UniqueKeyJson[fields.size()];

        Map<String,List<IndexMapObject>> indexMap = new HashMap<>();
        Map<String,List<ColumnJson>> groupUniqueColumnMap = new HashMap<>();
        Map<String,List<ColumnJson>> groupColumnMap = new HashMap<>();
        Map<String,List<ColumnJson>> groupKeyMap = new HashMap<>();




        this.tableQueries = new QueryJson[methods.size()];
        this.triggers = new TriggerJson[methods.size()];
        this.customConstraints = new CustomContraintJson[methods.size()];
        this.tableProcedures = new ProcedureJson[methods.size()];






        for (int i = 0; i < fields.size(); i++) {
            FieldsComb field = fields.get(i);

            KdbColumn kdbColumn = field.getKdbColumn();
            KdbPrimaryKey kdbPrimaryKey = field.getKdbPrimaryKey();
            KdbIndex kdbIndex = field.getKdbIndex();
            KdbReference kdbReference = field.getKdbReference();
            KdbKey kdbKey = field.getKdbKey();
            Class<?> fieldType = field.getFieldType();


            if (kdbColumn != null) {
//                set each annotated Fields into the Columns Json[]
                columns[i] = new ColumnJson(kdbColumn, kdbPrimaryKey, kdbIndex, kdbReference,fieldType);

                // adding Unique Key field
                try {
                    if (kdbKey != null) {
                        for (String key : kdbKey.groupName()) {
                            List<ColumnJson> value = groupKeyMap.computeIfAbsent(key, k -> new ArrayList<>());
                            value.add(columns[i]);
                        }
                    }else if(kdbPrimaryKey!=null){
                        // Filter primary key columns

                        List<ColumnJson> value = groupKeyMap.computeIfAbsent("PrimaryKeyGroup", k -> new ArrayList<>());
                        value.add(columns[i]);


                    }else if(columns[i].isUnique()){
                        String name = columns[i].getName() + "_key";
                        List<ColumnJson> singleKeyList = groupKeyMap.computeIfAbsent(name, k -> new ArrayList<>());
                        singleKeyList.add(columns[i]);
                    }
                } catch (Exception e) {
                    System.out.println("Unique Key Group Names ERROR");
                    throw e;
                }

                try {
//                    add each ColumnsJson Object to groupColumnMaps by adding it to the list associated to the default key->
                    List<ColumnJson> value;
                    String name;
                    name = "default";
                    if ((value = groupColumnMap.get(name)) == null) {
                        value = new ArrayList<>();
                        value.add(columns[i]);
                        groupColumnMap.put(name, value);

                    } else {
                        value.add(columns[i]);
                    }
                    for (String groupName : kdbColumn.columnGroupNames()) {
//                        add each ColumnsJson Object to groupColumnMaps by adding it to the list associated to the group name key
                        if ((value = groupColumnMap.get(groupName)) == null) {
                            value = new ArrayList<>();
                            value.add(columns[i]);
                            groupColumnMap.put(groupName, value);
                        } else {
                            value.add(columns[i]);
                            ;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("group Names ERROR");
                    throw (e);


                }
                try {
                    List<ColumnJson> value;
                    String name;

                    if (kdbColumn.uniqueIdentifier()) {

//                        add each ColumnsJson Object to groupUnizueColumnMap by adding it to the list associated to the default key if kdbColumn has uniqueIdentifier true ->
                        name = "default";
                        if ((value = groupUniqueColumnMap.get(name)) == null) {
                            value = new ArrayList<>();
                            value.add(columns[i]);
                            groupUniqueColumnMap.put(name, value);
                        } else {
                            value.add(columns[i]);
                        }
                        for (String uniqueColumnGroup : kdbColumn.uniqueIdenftifierGroupNames()) {
//                            add each ColumnsJson Object to groupUniqueColumnMaps by adding it to the list associated to the group name key
                            if ((value = groupUniqueColumnMap.get(uniqueColumnGroup)) == null) {
                                value = new ArrayList<>();
                                value.add(columns[i]);
                                groupUniqueColumnMap.put(uniqueColumnGroup, value);
                            } else {
                                value.add(columns[i]);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("unique column group Names ERROR");
                    throw (e);
                }


                try {
                    if (kdbIndex != null|| kdbPrimaryKey != null ||kdbReference!=null||kdbKey!=null) {
//add to list of Index Strings in the index map by using the the column name + _idx if indexGroups is empty ->
                        List<IndexMapObject> value;
                        String name;

                        if (kdbIndex== null || kdbIndex.indexGroups().length == 0||(kdbReference!=null && kdbIndex==null)||kdbKey!=null) {
                            name = kdbPrimaryKey!=null?"PRIMARY_"+kdbColumn.name():kdbReference!=null? kdbColumn.name() +"_fk": kdbKey!=null?kdbColumn.name()+"_key": kdbColumn.name() + "_idx";
                            if ((value = indexMap.get(name)) == null) {
                                value = new ArrayList<>();
                                IndexMapObject map = new IndexMapObject(0, columns[i]);

                                value.add(map);
                                indexMap.put(name, value);
                            } else {
                                IndexMapObject map = new IndexMapObject(0,columns[i]);
                                value.add(map);
                            }

                        }
                        else {
                            if (kdbIndex.order().length != kdbIndex.indexGroups().length) {
                                throw new IllegalArgumentException("Mismatch: order length ("
                                        + kdbIndex.order().length + ") != indexGroups length ("
                                        + kdbIndex.indexGroups().length + ")\n on " + kdbColumn.name());
                            }
                            for (int j = 0; j < kdbIndex.indexGroups().length; j++) {
//                                add to list of Index Strings in the index map by using the the indexgroup and order to determine the order of the list of indexes in the map
                                String indexGroup = kdbIndex.indexGroups()[j];
                                if ((value = indexMap.get(indexGroup)) == null) {
                                    value = new ArrayList<>();
                                    IndexMapObject map = new IndexMapObject(kdbIndex.order()[j], columns[i]);
                                    value.add(map);
                                    indexMap.put(indexGroup, value);
                                } else {
                                    IndexMapObject map = new IndexMapObject(kdbIndex.order()[j], columns[i]);
                                    value.add(map);
                                }
                            }
                        }


                    }


                } catch (Exception e) {
                    System.out.println("Index Group Names ERROR");
                    throw (e);
                }
            }
        }

        //create index columnGroup uniqueColumnGroup arrays from those maps
        int k = 0;
        this.indexes = new IndexJson[indexMap.size()];
        for(Map.Entry<String, List<IndexMapObject>> entry : indexMap.entrySet()) {
            this.indexes[k++] = new IndexJson(entry);
        }
        k=0;
        this.columnGroups = new ColumnGroupJson[groupColumnMap.size()];
        for(Map.Entry<String,List<ColumnJson>> entry : groupColumnMap.entrySet()) {
            this.columnGroups[k++] = new ColumnGroupJson(entry);

        }
        k=0;
        this.uniqueColumnGroups = new UniqueColumnGroupJson[groupUniqueColumnMap.size()];
        for(Map.Entry<String, List<ColumnJson>> entry : groupUniqueColumnMap.entrySet()) {

            this.uniqueColumnGroups[k++] = new UniqueColumnGroupJson(entry);
        }
        k=0;
        this.uniqueKeys = new UniqueKeyJson[groupKeyMap.size()];
        for(Map.Entry<String,List<ColumnJson>> entry: groupKeyMap.entrySet()){

            this.uniqueKeys[k++] = new UniqueKeyJson(entry);
        }



        for (int i = 0; i < methods.size(); i++) {
            MethodsComb method = methods.get(i);
            KdbQuery kdbQuery = method.getKdbQuery();
            if (kdbQuery != null) {
                //add each KdbQuery to QueryJson[]  if not null->
                tableQueries[i] = new QueryJson(kdbQuery, method.getMethod());
                if(kdbQuery.queryGroups().length>0){
                    //add each KdbQuery to key of the groupName if queryGroup isn't null
                    for (int j = 0; j < kdbQuery.queryGroups().length; j++) {
                        String groupName = kdbQuery.queryGroups()[j];
                        if (groupName != null && !groupName.isEmpty()) {
                            List<BaseQueryJson> value;
                            if ((value = queriesMap.get(groupName)) == null) {
                                value = new ArrayList<>();
                                value.add(tableQueries[i]);
                                queriesMap.put(groupName, value);
                            } else {
                                value.add(tableQueries[i]);
                            }
                        }
                    }
                }
            }
            KdbTrigger kdbTrigger = method.getKdbTrigger();
            if (kdbTrigger != null) {
//                add each KdbTrigger to TriggerJson[] if not null
                triggers[i] = new TriggerJson(kdbTrigger, method.getMethod());
            }
            KdbCustomContraint kdbCustomContraint = method.getKdbCustomContraint();
            if (kdbCustomContraint != null) {
//                add each KdbCustomConstraint to customConstraintJson[]if not null
                customConstraints[i] = new CustomContraintJson(kdbCustomContraint, method.getMethod());
            }
            KdbProcedure kdbProcedure = method.getKdbProcedure();
            if(kdbProcedure!=null){
                // add each KdbProcedure to ProcedureJson[] if not null ->
                tableProcedures[i] = new ProcedureJson(kdbProcedure,method.getMethod());
                if(kdbProcedure.procedureGroups().length>0){
                    // add each KdbProcedure to key of the groupName if queryGroup isn't null
                    for(int j = 0;j<kdbProcedure.procedureGroups().length;j++){
                        String key = kdbProcedure.procedureGroups()[j];
                        if(key!=null && !key.isEmpty()){
                            List<BaseQueryJson> value;
                            if((value= queriesMap.get(key))==null){
                                value = new ArrayList<>();
                                value.add(tableProcedures[i]);
                                queriesMap.put(key,value);
                            }else{
                                value.add(tableProcedures[i]);
                            }
                        }
                    }
                }
            }
        }

//        for(Map.Entry<String, List<BaseQueryJson>> entry : queriesMap.entrySet()) {
//            String key = entry.getKey();
//            List<BaseQueryJson> value = entry.getValue();
//            for(BaseQueryJson query : value) {
//
//            }
//            System.out.println("Query Group: " + key + " -> " + value);
//        }
        this.columns = Arrays.stream(this.columns)
                .filter(Objects::nonNull)
                .toArray(ColumnJson[]::new);


        this.tableQueries =Arrays.stream(this.tableQueries)
                .filter(Objects::nonNull)
                .toArray(QueryJson[]::new);
        this.triggers =Arrays.stream(this.triggers)
                .filter(Objects::nonNull)
                .toArray(TriggerJson[]::new);
        this.customConstraints = Arrays.stream(this.customConstraints)
                .filter(Objects::nonNull)
                .toArray(CustomContraintJson[]::new);

        this.indexes = Arrays.stream(this.indexes)
                .filter(Objects::nonNull)
                .toArray(IndexJson[]::new);
        this.columnGroups = Arrays.stream(this.columnGroups)
                .filter(Objects::nonNull)
                .toArray(ColumnGroupJson[]::new);
        this.uniqueColumnGroups = Arrays.stream(this.uniqueColumnGroups)
                .filter(Objects::nonNull)
                .toArray(UniqueColumnGroupJson[]::new);

        this.tableProcedures = Arrays.stream(this.tableProcedures)
                .filter(Objects::nonNull)
                .toArray(ProcedureJson[]::new);





    }

    public TableJson() {

    }

    public Object[]  filterNulls(Object[] array){
        return Arrays.stream(array).filter(Objects::nonNull).toArray();
    }

    public String getName() {
        return name;
    }

    public QueryJson[] getTableQueries() {
        return tableQueries;
    }

    public void setTableQueries(QueryJson[] tableQueries) {
        this.tableQueries = tableQueries;
    }

    public ProcedureJson[] getTableProcedures() {
        return tableProcedures;
    }

    public void setTableProcedures(ProcedureJson[] tableProcedures) {
        this.tableProcedures = tableProcedures;
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

    public String getTableId() {
        return tableId;
    }

    public Map<String, List<BaseQueryJson>> getQueriesMap() {
        return queriesMap;
    }

    public void setQueriesMap(Map<String, List<BaseQueryJson>> queriesMap) {
        this.queriesMap = queriesMap;
    }



    public void setIndexes(IndexJson[] indexes) {
        this.indexes = indexes;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public ColumnJson[] getColumns() {
        return columns;
    }

    public UniqueKeyJson[] getUniqueKeys() {
        return uniqueKeys;
    }

    public void setUniqueKeys(UniqueKeyJson[] uniqueKeys) {
        this.uniqueKeys = uniqueKeys;
    }

    public void setColumns(ColumnJson[] columns) {
        this.columns = columns;
    }




    public TriggerJson[] getTriggers() {
        return triggers;
    }

    public void setTriggers(TriggerJson[] triggers) {
        this.triggers = triggers;
    }

    public CustomContraintJson[] getCustomConstraints() {
        return customConstraints;
    }

    public void setCustomConstraints(CustomContraintJson[] customConstraints) {
        this.customConstraints = customConstraints;
    }

    public ColumnGroupJson[] getColumnGroups() {
        return columnGroups;
    }

    public void setColumnGroups(ColumnGroupJson[] columnGroups) {
        this.columnGroups = columnGroups;
    }

    public UniqueColumnGroupJson[] getUniqueColumnGroups() {
        return uniqueColumnGroups;
    }

    public void setUniqueColumnGroups(UniqueColumnGroupJson[] uniqueColumnGroups) {
        this.uniqueColumnGroups = uniqueColumnGroups;
    }

    public DependencyJson[] getDependencies() {
        return dependencies;
    }

    public void setDependencies(DependencyJson[] dependencies) {
        this.dependencies = dependencies;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    @Override
    public String toString() {
        return "TableJson{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", tableId='" + tableId + '\'' +
                ", tableType='" + tableType + '\'' +
                ", columns=" + Arrays.toString(columns) +

                ", queries=" + Arrays.toString(tableQueries) +
                ", triggers=" + Arrays.toString(triggers) +
                ", customConstraints=" + Arrays.toString(customConstraints) +
                ", columnGroups=" + Arrays.toString(columnGroups) +
                ", uniqueColumnGroups=" + Arrays.toString(uniqueColumnGroups) +
                ", dependencies=" + Arrays.toString(dependencies) +
                ", indexes=" + Arrays.toString(indexes) +
                '}';
    }

    public IndexJson[] getIndexes() {
        return indexes;
    }
}
