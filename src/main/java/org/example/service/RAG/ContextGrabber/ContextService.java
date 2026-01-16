package org.example.service.RAG.ContextGrabber;

import org.apache.tomcat.util.digester.Rule;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.bank.db.PythonContextBuilderJson;
import org.example.bank.db.contextObj.*;

import org.example.bank.db.contextObj.match.JsonColumnContextMatch;
import org.example.bank.db.contextObj.match.JsonMaContextMatch;
import org.example.bank.db.contextObj.match.JsonTableContextMatch;
import org.example.bank.db.whereObj.ColWhereList;
import org.example.bank.db.whereObj.MaWhereList;
import org.example.bank.db.whereObj.TabWhereList;
import org.example.bank.db.whereObj.WhereObj;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.example.bank.OutputClassBank.KDBContext.KDB_CONTEXT;
import static org.example.bank.commonValues.AppConfig.*;

@Service
public class ContextService {

    final String EQUALS = "eq";
    final String NOT_EQUALS = "neq";
    final String GREATER_THAN = ">";
    final String LESS_THAN = "<";
    final String BETWEEN = "btw";

    public String whereBuilder(Rules rules){
        List<String> clauses = new ArrayList<>();

        rules.getMap().forEach((key, value) -> {
            if (value == null) {
                throw new IllegalArgumentException("Value for key " + key + " is null");
            }
            for (int i = 0; i < value.size(); i++) {
                String nextValue = value.get(i).toString().trim();
                String modifier = null;
                List<String> values;

                Pattern modifierPattern = Pattern.compile("%30(.*?)%30;");
                Matcher matcher = modifierPattern.matcher(nextValue);
                if (matcher.find()) {
                    modifier = matcher.group(1);
                    nextValue = nextValue.replaceAll("%30.*?%30;", "").trim();
                }

                values = Arrays.stream(nextValue.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();

                if (modifier == null || modifier.equalsIgnoreCase(EQUALS)) {
                    if (values.size() > 1) {
                        String inClause = values.stream()
                                .map(val -> String.format("'%s'", val))
                                .collect(Collectors.joining(", "));
                        clauses.add(String.format("%s IN (%s)", key, inClause));
                        continue;
                    } else {
                        clauses.add(String.format("%s = '%s'", key, values.get(0)));
                        continue;
                    }
                } else if (modifier.equalsIgnoreCase(NOT_EQUALS)) {
                    if (values.size() > 1) {
                        String inClause = values.stream()
                                .map(val -> String.format("'%s'", val))
                                .collect(Collectors.joining(", "));
                        clauses.add(String.format("%s NOT IN (%s)", key, inClause));
                        continue;
                    } else {
                        clauses.add(String.format("%s != '%s'", key, values.get(0)));
                        continue;
                    }
                } else if (modifier.equalsIgnoreCase(GREATER_THAN)) {
                    if (values.size() > 1) {
                        throw new IllegalArgumentException("GREATER_THAN modifier cannot have multiple values for key: " + key);
                    }
                    clauses.add(String.format("%s > '%s'", key, values.get(0)));
                    continue;
                } else if (modifier.equalsIgnoreCase(LESS_THAN)) {
                    if (values.size() > 1) {
                        throw new IllegalArgumentException("LESS_THAN modifier cannot have multiple values for key: " + key);
                    }
                    clauses.add(String.format("%s < '%s'", key, values.get(0)));
                    continue;
                } else if (modifier.equalsIgnoreCase(BETWEEN) || (modifier != null && modifier.equalsIgnoreCase("btwn"))) {
                    if (values.size() != 2) {
                        throw new IllegalArgumentException("BETWEEN modifier requires exactly two values for key: " + key);
                    }
                    clauses.add(String.format("%s BETWEEN '%s' AND '%s'", key, values.get(0), values.get(1)));
                    continue;
                } else {
                    throw new IllegalArgumentException("Unsupported rule: " + modifier + " for " + value);
                }
            }
        });

        return String.join(" AND ", clauses);
    }




    public List<String> recursiveQueryBuild(Map<ColumnJson, Rules> columnRulesMap, List<String> queries) {
        if (queries == null) queries = new ArrayList<>();
        if (columnRulesMap == null || columnRulesMap.isEmpty()) return queries;

        // operate on a copy to avoid modifying caller's map and to prevent concurrent modification
        Map<ColumnJson, Rules> remaining = new LinkedHashMap<>(columnRulesMap);

        while (!remaining.isEmpty()) {
            Iterator<Map.Entry<ColumnJson, Rules>> it = remaining.entrySet().iterator();
            System.out.println("\n--------------------\n");
            Map.Entry<ColumnJson, Rules> seedEntry = it.next();
            ColumnJson seedCol = seedEntry.getKey();
            Rules seedRules = seedEntry.getValue();
            System.out.println(seedCol+" with rules "+seedRules);

            String maName = seedCol.getIdentifier().getMaName();
            String tableName = seedCol.getIdentifier().getTableName();

            List<String> columnNames = new ArrayList<>();
            List<ColumnJson> toRemove = new ArrayList<>();

            // collect all columns that match ma/table and rules of the seed
            for (Map.Entry<ColumnJson, Rules> e : remaining.entrySet()) {
                ColumnJson col = e.getKey();
                Rules r = e.getValue();
                if (Objects.equals(maName, col.getIdentifier().getMaName())
                        && Objects.equals(tableName, col.getIdentifier().getTableName())
                        && Objects.equals(seedRules, r)) {
                    System.out.println("adding column "+col+" with rules "+r);
                    columnNames.add(col.getName());
                    toRemove.add(col);
                }
            }

            // remove matched columns from remaining
            for (ColumnJson c : toRemove) {
                remaining.remove(c);
            }

            String selectColumns = String.join(", ", columnNames);
            if (seedRules == null) throw new IllegalArgumentException("Rules cannot be null for column: " + selectColumns);
            String fromClause = String.format("%s.%s", maName, tableName);
            String whereClause = whereBuilder(seedRules);

            String query = String.format(
                    "SELECT\n    %s\nFROM\n    %s\nWHERE\n    %s",
                    selectColumns, fromClause, whereClause
            );
            queries.add(query);
        }

        return queries;
    }


    public void buildSelectString(ContextObj contextObj) {
        System.out.println(contextObj);

         ColWhereList colWhereList =  getContextMySql(contextObj);
         List<String> queryList = new ArrayList<>();


         queryList = recursiveQueryBuild(colWhereList.getColumnJsonList(),queryList);
        System.out.println(queryList);


        /*
        if columns are in the same ma table and have the same column rules they can be in the same query,
         otherwise they need to be separate queries*/



    }


    private ColWhereList getContextMySql(ContextObj context) {

        GMAJson gma = KDB_CONTEXT.getGmaByName(getGmaName());
        List<MAJson> gmaMas = gma.getMa();
        System.out.println(context);
        List<MAJson> mas = new ArrayList<>();
        List<TableJson> tables=new ArrayList<>();
        List<ColumnJson> columns=new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(getJdbcUrl(),getJdbcUser(),getJdbcPassword())) {



            MaWhereList whereMas = getListMas(context.getMas(),gmaMas,connection);
            mas = whereMas.getList();








            TabWhereList whereTables = getListTables(context.getTables(),whereMas,connection);
            tables = whereTables.getList();

            System.out.println(whereTables.getTableJsonList());
//
            ColWhereList whereColumns = getListColumns(context.getColumns(),whereTables,connection);
            columns = whereColumns.getList();

            System.out.println(whereColumns.getColumnJsonList());


//            for(ColumnJson col : columns){
//                System.out.println("final matching columns: "+col.getName()+" in tables "+col.getIdentifier().getTableName()+" in mas "+col.getIdentifier().getMaName());
//
//            }

            return whereColumns;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private ColWhereList getListColumns(ContextColumn[] context, TabWhereList tableJsons, Connection connection) {
        ColWhereList columnJsons = new ColWhereList(tableJsons);


        ColWhereList columns;


// the three types of context matches: by name, by tags, by description
        List<ContextColumn> tagsColumn = Arrays.stream(context)
                .filter(contextColumn ->
                        (contextColumn.getName() == null || contextColumn.getName().isEmpty()) &&
                                contextColumn.getTags() != null &&
                                contextColumn.getTags().length > 0
                )
                .toList();

        List<ContextColumn> nameColumn = Arrays.stream(context)
                .filter(contextColumn -> {
                    return contextColumn.getName() != null && !contextColumn.getName().isEmpty();
                }).toList();


        List<ContextColumn> descriptionColumn = Arrays.stream(context).filter(contextColumn -> {
            return (contextColumn.getDescription() != null && contextColumn.getDescription().length > 0)
                    &&
                    (contextColumn.getName() == null || contextColumn.getName().isEmpty())
                    &&
                    (contextColumn.getTags() == null || Arrays.stream(contextColumn.getTags()).filter(tag -> !tag.isEmpty()).toList().isEmpty());
        }).toList();


        List<ColumnJson> matchingColumns = new ArrayList<>();


        //// ------------------------ Name ------------------------
        List<JsonColumnContextMatch> columnFilter = new ArrayList<>();

        for (ContextColumn contextColumn : nameColumn) {

            // in `src/main/java/org/example/ContextGrabber/ContextService.java`
            if (contextColumn.getTags() != null) {
                contextColumn.setTags(Arrays.stream(contextColumn.getTags())
                        .filter(Objects::nonNull)       // remove nulls
                        .map(String::trim)              // trim whitespace
                        .filter(s -> !s.isEmpty())     // remove blank strings
                        .toArray(String[]::new));      // convert back to String[]
            } else {
                System.out.println("no tags in name context");
                contextColumn.setTags(new String[0]);
            }


            // 1️⃣ Find MAJsons with matching name
            matchingColumns = columnJsons.getList().stream()
                    .filter(column -> Objects.equals(column.getName(), contextColumn.getName()))
                    .toList();

            // 2️⃣ For each matching MAJson, check tags if needed
            for (ColumnJson columnJson : matchingColumns) {

                boolean tagMatch = true;

                // If context has tags, enforce tag matching

                if (contextColumn.getTags() != null && contextColumn.getTags().length > 0) {


                    System.out.println(Arrays.toString(contextColumn.getTags()));
                    tagMatch = columnJson.getTags() != null &&
                            Arrays.stream(contextColumn.getTags())
                                    .anyMatch(ctxTag ->
                                            Arrays.stream(columnJson.getTags())
                                                    .anyMatch(jsonTag ->
                                                            jsonTag.equalsIgnoreCase(ctxTag)
                                                    )
                                    );
                }

                // 3️⃣ If name matched AND tag rules pass → add match
                if (tagMatch) {
                    System.out.println("matching ide" + columnJson.getName() + " to context " + contextColumn.getName());
                    columnFilter.add(new JsonColumnContextMatch(contextColumn, columnJson));
                }
            }


        }
        for (ContextColumn contextColumn : tagsColumn) {

            if (contextColumn.getTags() == null || contextColumn.getTags().length == 0) {
                continue; // no tag constraints
            }

            List<ColumnJson> matchingMas = columnJsons.getList().stream()
                    .filter(ma -> ma.getTags() != null)
                    .filter(ma ->
                            Arrays.stream(ma.getTags())
                                    .anyMatch(maTag ->
                                            Arrays.stream(contextColumn.getTags())
                                                    .anyMatch(ctxTag ->
                                                            ctxTag.equalsIgnoreCase(maTag)
                                                    )
                                    )
                    )
                    .toList();

            for (ColumnJson columnJson : matchingMas) {
                System.out.println("matching ide " + columnJson.getName() + " to context " + contextColumn.getName());
                columnFilter.add(new JsonColumnContextMatch(contextColumn, columnJson));
            }
        }

// ------------------------ Description------------------------
        for (ContextColumn contextMa : descriptionColumn) {

            System.out.println("looking for matching descriptions for " + String.join(" | ", contextMa.getDescription()));
        }
        System.out.println(columnFilter.size()+" columns matched before description filtering");
        System.out.println(columnFilter.stream().map(ma-> ma.getContext().getName()).toList());

        if(columnFilter.isEmpty()){
            System.out.println("no matching columns");
            columns = columnJsons;
        }else {
            columns = new ColWhereList(columnFilter, columnJsons);
        }


        if (columns.getList().isEmpty()) {
            System.out.println("no matching columns");
        }


        // these are the rules for

//        for (ContextColumn contextColumn : descriptionColumn) {
//            String columnName = contextColumn.getName();
//
//            List<ColumnJson> matched = columns.stream()
//                    .flatMap(column -> column.getColumns().stream())
//                    .filter(column -> column.getName().equalsIgnoreCase(columnName))
//                    .filter(column -> maNames.contains(column.getMaName()))
//                    .toList();
//
//            matchingColumns.addAll(matched);
//        }

        return columns;

    }



    private TabWhereList getListTables(ContextTable[] context, MaWhereList mas, Connection connection) {
        TabWhereList tableJsons = new TabWhereList(mas);


        TabWhereList tables;


// the three types of context matches: by name, by tags, by description
        List<ContextTable> tagsTable = Arrays.stream(context)
                .filter(contextTable ->
                        (contextTable.getName() == null || contextTable.getName().isEmpty()) &&
                                contextTable.getTags() != null &&
                                contextTable.getTags().length > 0
                )
                .toList();

        List<ContextTable> nameTable = Arrays.stream(context)
                .filter(contextTable -> {
                    return contextTable.getName() != null && !contextTable.getName().isEmpty();
                }).toList();



        List<ContextTable> descriptionTable = Arrays.stream(context).filter(contextTable -> {
            return (contextTable.getDescription() != null && contextTable.getDescription().length > 0)
                    &&
                    (contextTable.getName() == null || contextTable.getName().isEmpty())
                    &&
                    (contextTable.getTags() == null || Arrays.stream(contextTable.getTags()).filter(tag->!tag.isEmpty()).toList().isEmpty());
        }).toList();


        List<TableJson> matchingTables = new ArrayList<>();


        //// ------------------------ Name ------------------------
        List<JsonTableContextMatch> tableFilter = new ArrayList<>();

        for (ContextTable contextTable : nameTable) {

            // in `src/main/java/org/example/ContextGrabber/ContextService.java`
            if (contextTable.getTags() != null) {
                contextTable.setTags(Arrays.stream(contextTable.getTags())
                        .filter(Objects::nonNull)       // remove nulls
                        .map(String::trim)              // trim whitespace
                        .filter(s -> !s.isEmpty())     // remove blank strings
                        .toArray(String[]::new));      // convert back to String[]
            } else {
                System.out.println("no tags in name context");
                contextTable.setTags(new String[0]);
            }


            // 1️⃣ Find MAJsons with matching name
            matchingTables = tableJsons.getList().stream()
                    .filter(table -> Objects.equals(table.getName(), contextTable.getName()))
                    .toList();

            // 2️⃣ For each matching MAJson, check tags if needed
            for (TableJson tableJson : matchingTables) {

                boolean tagMatch = true;

                // If context has tags, enforce tag matching

                if (contextTable.getTags() != null && contextTable.getTags().length > 0) {


                    System.out.println(Arrays.toString(contextTable.getTags()));
                    tagMatch = tableJson.getTags() != null &&
                            Arrays.stream(contextTable.getTags())
                                    .anyMatch(ctxTag ->
                                            Arrays.stream(tableJson.getTags())
                                                    .anyMatch(jsonTag ->
                                                            jsonTag.equalsIgnoreCase(ctxTag)
                                                    )
                                    );
                }

                // 3️⃣ If name matched AND tag rules pass → add match
                if (tagMatch) {
                    System.out.println("matching ide" + tableJson.getName() + " to context " + contextTable.getName());
                    tableFilter.add(new JsonTableContextMatch(contextTable, tableJson));
                }
            }
        }


        // ------------------------ Tag ------------------------


        for (ContextTable contextTable : tagsTable) {

            if (contextTable.getTags() == null || contextTable.getTags().length == 0) {
                continue; // no tag constraints
            }

            List<TableJson> matchingMas = tableJsons.getList().stream()
                    .filter(ma -> ma.getTags() != null)
                    .filter(ma ->
                            Arrays.stream(ma.getTags())
                                    .anyMatch(maTag ->
                                            Arrays.stream(contextTable.getTags())
                                                    .anyMatch(ctxTag ->
                                                            ctxTag.equalsIgnoreCase(maTag)
                                                    )
                                    )
                    )
                    .toList();

            for (TableJson tableJson : matchingMas) {
                System.out.println("matching ide " + tableJson.getName() + " to context " + contextTable.getName());
                tableFilter.add(new JsonTableContextMatch(contextTable, tableJson));
            }
        }

// ------------------------ Description------------------------
        for (ContextTable contextMa : descriptionTable) {

            System.out.println("looking for matching descriptions for " + String.join(" | ", contextMa.getDescription()));
        }
        System.out.println(tableFilter.size()+" tables matched before description filtering");
        System.out.println(tableFilter.stream().map(ma-> ma.getContext().getName()).toList());
        if(tableFilter.isEmpty()){
            System.out.println("no tables to filter, returning all matching tables from mas");
            tables = tableJsons;
        }else {
            tables = new TabWhereList(tableFilter,tableJsons);
        }



        if (tables.getList().isEmpty()) {
            System.out.println("no matching tables");
        }



        return tables;
    }

    private MaWhereList getListMas(ContextMa[] context,List<MAJson> gmaMas,Connection connection) throws SQLException {
        // we are grabbing
        MaWhereList mas;

// the three types of context matches: by name, by tags, by description
        List<ContextMa> tagsMa = Arrays.stream(context)
                .filter(contextMa ->
                        (contextMa.getName() == null || contextMa.getName().isEmpty()) &&
                                contextMa.getTags() != null &&
                                contextMa.getTags().length > 0
                )
                .toList();

        List<ContextMa> nameMa = Arrays.stream(context)
                .filter(contextMa -> {
                    return contextMa.getName() != null && !contextMa.getName().isEmpty();
                }).toList();



        List<ContextMa> descriptionMa = Arrays.stream(context).filter(contextMa -> {
            return (contextMa.getDescription() != null && contextMa.getDescription().length > 0)
                    &&
                    (contextMa.getName() == null || contextMa.getName().isEmpty())
                    &&
                    (contextMa.getTags() == null || Arrays.stream(contextMa.getTags()).filter(tag->!tag.isEmpty()).toList().isEmpty());
        }).toList();



//// ------------------------ Name ------------------------
        List<JsonMaContextMatch> maFilter = new ArrayList<>();

        for (ContextMa contextMa : nameMa) {

            // in `src/main/java/org/example/ContextGrabber/ContextService.java`
            if (contextMa.getTags() != null) {
                contextMa.setMa_tags(Arrays.stream(contextMa.getTags())
                        .filter(Objects::nonNull)       // remove nulls
                        .map(String::trim)              // trim whitespace
                        .filter(s -> !s.isEmpty())     // remove blank strings
                        .toArray(String[]::new));      // convert back to String[]
            } else {
                System.out.println("no tags in name context");
                contextMa.setMa_tags(new String[0]);
            }


            // 1️⃣ Find MAJsons with matching name
            List<MAJson> matchingMas = gmaMas.stream()
                    .filter(ma -> Objects.equals(ma.getName(), contextMa.getName()))
                    .toList();

            // 2️⃣ For each matching MAJson, check tags if needed
            for (MAJson maJson : matchingMas) {

                boolean tagMatch = true;

                // If context has tags, enforce tag matching

                if (contextMa.getTags() != null && contextMa.getTags().length > 0) {


                    System.out.println(Arrays.toString(contextMa.getTags()));
                    tagMatch = maJson.getTags() != null &&
                            Arrays.stream(contextMa.getTags())
                                    .anyMatch(ctxTag ->
                                            Arrays.stream(maJson.getTags())
                                                    .anyMatch(jsonTag ->
                                                            jsonTag.equalsIgnoreCase(ctxTag)
                                                    )
                                    );
                }

                // 3️⃣ If name matched AND tag rules pass → add match
                if (tagMatch) {
                    System.out.println("matching ide" + maJson.getName() + " to context " + contextMa.getName());
                    maFilter.add(new JsonMaContextMatch(contextMa, maJson));
                }
            }
        }


        // ------------------------ Tag ------------------------


        for (ContextMa contextMa : tagsMa) {

            if (contextMa.getTags() == null || contextMa.getTags().length == 0) {
                continue; // no tag constraints
            }

            List<MAJson> matchingMas = gmaMas.stream()
                    .filter(ma -> ma.getTags() != null)
                    .filter(ma ->
                            Arrays.stream(ma.getTags())
                                    .anyMatch(maTag ->
                                            Arrays.stream(contextMa.getTags())
                                                    .anyMatch(ctxTag ->
                                                            ctxTag.equalsIgnoreCase(maTag)
                                                    )
                                    )
                    )
                    .toList();

            for (MAJson maJson : matchingMas) {
                System.out.println("matching ide " + maJson.getName() + " to context " + contextMa.getName());
                maFilter.add(new JsonMaContextMatch(contextMa, maJson));
            }
        }

// ------------------------ Description------------------------
        for (ContextMa contextMa : descriptionMa) {

            System.out.println("looking for matching descriptions for " + String.join(" | ", contextMa.getDescription()));
        }
        System.out.println(maFilter.size()+" mas matched before description filtering");
        System.out.println(maFilter.stream().map(ma-> ma.getContext().getName()).toList());
        if(maFilter.size()==0){
            System.out.println("no matching mas");
            mas = new MaWhereList(gmaMas);
        }else {
            mas = new MaWhereList(maFilter);
        }




        if (mas.getList().isEmpty()){
            throw new RuntimeException("no matching mas");
        }

//        String whereClause = String.format(
//                "WHERE ma.ma_name IN (%s)",
//                String.join(",",mas)
//
//        );
//
//
//        String maSelect = String.format("""
//                SELECT
//                    ma.ma_name
//                FROM valhalla.gma_configs gc
//                JOIN JSON_TABLE(
//                    gc.config_json,
//                    '$.ma[*]'
//                    COLUMNS (
//                        ma_name        VARCHAR(255) PATH '$.name',
//                        ma_description VARCHAR(255) PATH '$.description',
//                        tags           JSON         PATH '$.tags'
//                    )
//                ) AS ma
//                %s
//                """, whereClause);
//
//        System.out.println(maSelect);
//
//        ResultSet resultSet = connection.prepareStatement(maSelect).executeQuery();
//        ResultSetMetaData meta = resultSet.getMetaData();
//        int cols = meta.getColumnCount();
//
//
//        while (resultSet.next()) {
//            for (int i = 1; i <= cols; i++) {
//                String columnName = meta.getColumnLabel(i); // or getColumnName(i)
//                String value = resultSet.getString(i);
//                System.out.print(columnName + "=" + value + "  ");
//                mas.add(value);
//            }
//        }

        return mas;
}
}
