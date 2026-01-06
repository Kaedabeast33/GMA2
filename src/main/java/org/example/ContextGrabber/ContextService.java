package org.example.ContextGrabber;

import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.bank.db.contextObj.ContextMa;
import org.example.bank.db.contextObj.ContextObj;

import org.example.bank.db.contextObj.match.JsonContextMatch;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.bank.OutputClassBank.KDBContext.KDB_CONTEXT;
import static org.example.bank.commonValues.AppConfig.*;

@Service
public class ContextService {
    public void buildSelectString(ContextObj context){

        GMAJson gma = KDB_CONTEXT.getGmaByName(getGmaName());
        List<MAJson> gmaMas = gma.getMa();
        System.out.println(context);
        List<String> mas = new ArrayList<>();
        List<String> tables=new ArrayList<>();;
        List<String> columns=new ArrayList<>();;

        try(Connection connection = DriverManager.getConnection(getJdbcUrl(),getJdbcUser(),getJdbcPassword())) {

            mas = getListMas(context.getMas(),gmaMas,connection);










        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private List<String> getListMas(ContextMa[] context,List<MAJson> gmaMas,Connection connection) throws SQLException {
        // we are grabbing
        List<String> mas = new ArrayList<>();
        List<String> tags = new ArrayList<>();

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
            return (contextMa.getDescription() != null || contextMa.getDescription().length > 0) &&
                    (contextMa.getName() == null || contextMa.getName().isBlank()) &&
                    (contextMa.getTags() == null || contextMa.getTags().length == 0);
        }).toList();


//// ------------------------ Name ------------------------
        List<JsonContextMatch> maFilter = new ArrayList<>();

        for (ContextMa contextMa : nameMa) {

            // 1️⃣ Find MAJsons with matching name
            List<MAJson> matchingMas = gmaMas.stream()
                    .filter(ma -> Objects.equals(ma.getName(), contextMa.getName()))
                    .toList();

            // 2️⃣ For each matching MAJson, check tags if needed
            for (MAJson maJson : matchingMas) {

                boolean tagMatch = true;

                // If context has tags, enforce tag matching
                if (contextMa.getTags() != null && contextMa.getTags().length > 0) {

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
                    maFilter.add(new JsonContextMatch(contextMa, maJson));
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
                System.out.println("matching ide" + maJson.getName() + " to context " + contextMa.getName());
                maFilter.add(new JsonContextMatch(contextMa, maJson));
            }
        }

// ------------------------ Description------------------------
        for (ContextMa contextMa : descriptionMa) {

            System.out.println("looking for matching descriptions for " + String.join(" | ", contextMa.getDescription()));
        }

        mas = maFilter.stream().filter(JsonContextMatch::descriptionMatch).map(ma-> ma.getContext().getName()).toList();


        if (mas.isEmpty()) {
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
        for (String name: mas){
            System.out.println("matching");
            System.out.println(name);
        }
        return mas;
    }

}
