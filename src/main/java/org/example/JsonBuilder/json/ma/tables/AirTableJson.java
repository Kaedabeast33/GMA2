package org.example.JsonBuilder.json.ma.tables;

import org.example.JsonBuilder.json.ma.tables.columns.*;
import org.example.JsonBuilder.json.ma.tables.dependencies.DependencyJson;
import org.example.bank.Annotations.*;
import org.example.bank.JsonBuilderRef.IndexMapObject;
import org.example.bank.commonValues.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AirTableJson {
    String name;
    String description;
    String[] tags;
    String tableId;
    String tableType;


    Identifier identifier;



    AirColumnJson[] columns;
    ColumnGroupJson[] columnGroups;

//    UniqueColumnGroupJson[] uniqueColumnGroups;

//    IndexJson[] indexes;
//    QueryJson[] tableQueries;
//    ProcedureJson[] tableProcedures;
//    TriggerJson[] triggers;
//    CustomContraintJson[] customConstraints;
//    UniqueKeyJson[] uniqueKeys;



    private transient Map<String, List<BaseQueryJson>> queriesMap = new HashMap<>();




    public AirTableJson(Identifier identifier,TableComb table)  {
        KdbTable tableData = table.getKdbTable();
        this.name = tableData.name();
        this.tableId = tableData.airtableId();

        this.description = tableData.description();
        this.tags = tableData.tags();
        this.tableType = tableData.type();
        List<FieldsComb> fields = table.getFieldsComb();


        this.identifier = new Identifier(identifier);
        this.identifier.setTableName(this.name);
        this.columns = new AirColumnJson[fields.size()];
        this.columnGroups = new ColumnGroupJson[fields.size()];



        List<MethodsComb> methods = table.getMethodsComb();
        Map<String, List<AirColumnJson>> groupColumnMap = new HashMap<>();
        // Initialize arrays with the size of the fields and methods lists



//        Map<String, List<IndexMapObject>> indexMap = new HashMap<>();
//        Map<String, List<ColumnJson>> groupUniqueColumnMap = new HashMap<>();
//        Map<String, List<ColumnJson>> groupColumnMap = new HashMap<>();
//        Map<String, List<ColumnJson>> groupKeyMap = new HashMap<>();


//        this.tableQueries = new QueryJson[methods.size()];
//        this.triggers = new TriggerJson[methods.size()];
//        this.customConstraints = new CustomContraintJson[methods.size()];
//        this.tableProcedures = new ProcedureJson[methods.size()];


        for (int i = 0; i < fields.size(); i++) {
            FieldsComb field = fields.get(i);

            KdbColumn kdbColumn = field.getKdbColumn();

            Class<?> fieldType = field.getFieldType();


            if (kdbColumn != null) {
//                set each annotated Fields into the Columns Json[]
                columns[i] = new AirColumnJson(new Identifier(this.identifier),kdbColumn, fieldType);


                try {
//                    add each ColumnsJson Object to groupColumnMaps by adding it to the list associated to the default key->
                    List<AirColumnJson> value;
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
                .toArray(AirColumnJson[]::new);



        this.columnGroups = Arrays.stream(this.columnGroups)
                .filter(Objects::nonNull)
                .toArray(ColumnGroupJson[]::new);

        int k = 0;
        k = 0;
        this.columnGroups = new ColumnGroupJson[groupColumnMap.size()];
        for (Map.Entry<String, List<AirColumnJson>> entry : groupColumnMap.entrySet()) {
            this.columnGroups[k++] = ColumnGroupJson.buildAirColumnGroupJson(entry);

        }

    }

    public AirTableJson() {

    }

    public Object[] filterNulls(Object[] array) {
        return Arrays.stream(array).filter(Objects::nonNull).toArray();
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

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public AirColumnJson[] getColumns() {
        return columns;
    }

    public void setColumns(AirColumnJson[] columns) {
        this.columns = columns;
    }

    public ColumnGroupJson[] getColumnGroups() {
        return columnGroups;
    }

    public void setColumnGroups(ColumnGroupJson[] columnGroups) {
        this.columnGroups = columnGroups;
    }

    public Map<String, List<BaseQueryJson>> getQueriesMap() {
        return queriesMap;
    }

    public void setQueriesMap(Map<String, List<BaseQueryJson>> queriesMap) {
        this.queriesMap = queriesMap;
    }

    @Override
    public String toString() {
        return "AirTableJson{" +
                "name='" + name + '\'' +
                "ma_name='" + identifier.getMaName() + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirTableJson tableJson = (AirTableJson) o;
        return Objects.equals(name, tableJson.name) && Objects.equals(identifier.getMaName(), tableJson.identifier.getMaName() );
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name,identifier.getMaName());


        return result;
    }
}
