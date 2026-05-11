package org.example.ClassOutputCreator.airtable;

import org.example.JsonBuilder.json.ma.tables.AirTableJson;
import org.example.JsonBuilder.json.ma.tables.columns.AirColumnJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.ClassOutputCreator.ClassCreator.*;
import static org.example.ClassOutputCreator.ClassCreator.wrapWithQuotes;
import static org.example.ClassOutputCreator.GMAClassCreator.*;
import static org.example.ClassOutputCreator.TableClassCreator.generateContextImport;

public class AirTableClassCreator {
    String name;
    List<AirColumnClassCreator> columnList;

    List<String> curDir;
    List<String> pkgDir;


    public AirTableClassCreator(AirTableJson table, List<String> curDir, List<String> pkgDir, String gmaName, String maName,String appId) throws IOException {

        this.name = table.getName();

        // Make a copy so each table has its own unique directory path
        this.curDir = new ArrayList<>(curDir);
        this.curDir.add(name); // e.g. add table name

//        System.out.println(curDir+" CURDIR");

        this.pkgDir = new ArrayList<>(pkgDir);
        this.pkgDir.add(name);

//        System.out.println("creating Table for " + name);
//        System.out.println("        ;

        this.columnList = new ArrayList<>();


        System.out.println("    creating Columns for Table " + table.getName());
        for (int i = 0; i < table.getColumns().length; i++) {

            System.out.println("    creating Column for " + table.getColumns()[i].getName());
            AirColumnClassCreator colCreator = new AirColumnClassCreator(this.curDir, this.pkgDir, table.getColumns()[i]);
            this.columnList.add(colCreator);
        }


        createTabClass(table, this.curDir, this.pkgDir, gmaName, maName,appId);

    }

    private void createTabClass(AirTableJson tab, List<String> curDir, List<String> pkgDir, String gmaName, String maName,String appId) throws IOException {
        String className = "AIRTAB_" + tab.getName();

        Path path = Path.of(String.join(File.separator, curDir), className + ".java");
        Files.createDirectories(path.getParent());
        Files.writeString(path, "", StandardOpenOption.CREATE);

        List<String> childDirs = new ArrayList<>();
        for (AirColumnJson col : tab.getColumns()) {
            childDirs.add("columns" + ".AIRCOL_" + col.getName());
        }



        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{"AirColumnTemplate","AirTableTemplate"}), List.of(new String[]{"JsonBuilder.json.ma.tables.columns.AirColumnJson"}), List.of(), path, false);
        generateAirTabImports(path);
        generateContextImport(path);

//            generateExceptions(exceptions,path);
        generateAirTabFields(tab, gmaName, maName,appId, path);


        generateTabCols(tab, path);
//        generateTabGetSet(tab, path);
        generateColumnMethods(tab, path);


        Files.writeString(path, "\n}", StandardOpenOption.APPEND); // Close the class definition
    }



    private void generateTabGetSet(AirTableJson tab, Path path) {

    }


    private void generateColumnMethods(AirTableJson tab, Path path) throws IOException {
//        generateGetColumnsMethod(tab.getColumns(),path);
//        generateGetColumnsByNameMethod(tab.getColumnGroups(),path);
        generateGetColumnNamesMethod(path);

        generateGetCols(tab.getColumns(), path);

        generateUploadStatements(path);
        generateQueryMethods(path);


    }

    private void generateQueryMethods(Path path) {
//        String getQueryByCol = """
////                    @Override
////                    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns,
////                                                      EntityManager entityManager) throws SQLException {
////                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, entityManager);
////                    }
////
////                    @Override
////                    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns,
////                                                      List<KdbColumnPersona> getColumns,
////                                                      EntityManager entityManager) throws SQLException {
////                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, getColumns, entityManager);
////                    }
////
////                    @Override
////                    public QueryResult getQuery(List<KdbColumnPersona> getColumns,
////                                                EntityManager entityManager) throws SQLException {
////                        return context.getQuery(this.getGmaName(), this.getMaName(), this.getName(), getColumns, entityManager);
////                    }
////
////
////
////                    @Override
////                    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns) throws SQLException {
////                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns);
////                    }
////
////                    @Override
////                    public QueryResult getQueryByCols(List<ColumnTemplate> byColumns,
////                                                      List<KdbColumnPersona> getColumns) throws SQLException {
////                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, getColumns);
////                    }
////
////                    @Override
////                    public QueryResult getQuery(List<KdbColumnPersona> getColumns) throws SQLException {
////                        return context.getQuery(this.getGmaName(), this.getMaName(), this.getName(), getColumns);
////                    }
//                """;
//        try {
//            Files.writeString(path, getQueryByCol, StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void generateUploadStatements(Path path) throws IOException {

//        String getUpload = """
////                        @Override
////                        public String getUploadDeleteGma(List<KdbColumnPersona> toDeleteBy,Boolean includeNullValues ) {
////                            return context.getUploadDeleteGma(this.getGmaName(),this.getMaName(),this.getName(),toDeleteBy,includeNullValues);
////                        }
////
////                        @Override
////                        public String getUploadUpdateGma(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns ) {
////                            return context.getUploadUpdateGma(this.getGmaName(),this.getMaName(),this.getName(),toUpdateBy,includeNullValues,updateColumns);
////                        }
////
////                        @Override
////                        public String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey ) {
////                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues,insertColumns,includePrimaryKey);
////                        }
////
////                        @Override
////                        public String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
////                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues);
////                        }
////
////                        @Override
////                        public String getUploadInsertGma() {
////                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName());
////                        }
////
////                        @Override
////                        public String getUploadInsertGma(Boolean includePrimaryKey ) {
////                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),includePrimaryKey);
////                        }
////
////
////                        @Override
////                        public String getUploadDelete(List<KdbColumnPersona> toDeleteBy,Boolean includeNullValues ) {
////                            return context.getUploadDelete(this.getGmaName(),this.getMaName(),this.getName(),toDeleteBy,includeNullValues);
////                        }
////
////                        @Override
////                        public String getUploadUpdate(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns ) {
////                            return context.getUploadUpdate(this.getGmaName(),this.getMaName(),this.getName(),toUpdateBy,includeNullValues,updateColumns);
////                        }
////
////                        @Override
////                        public String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey ) {
////                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues,insertColumns,includePrimaryKey);
////                        }
////
////                        @Override
////                        public String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
////                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues);
////                        }
////
////                        @Override
////                        public String getUploadInsert() {
////                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName());
////                        }
////
////                        @Override
////                        public String getUploadInsert(Boolean includePrimaryKey ) {
////                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),includePrimaryKey);
////                        }
//                """;
//
//        Files.writeString(path, getUpload, StandardOpenOption.APPEND);
    }

    private void generateGetColumnNamesMethod(Path path) throws IOException {
//        String get = """
//                        @Override
//                        public List<AirColumnJson> getAirColumns(){
//                           List<AirColumnJson> list = context.getColumns(this.getGmaName(),this.getMaName(),this.getName());
//                           return list;
//                        }
//                        \s
//                        @Override
//                        public List<AirColumnJson> getAirColumnsByGroupName(String groupName){
//                            List<AirColumnJson> list = context.getColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
//                            return list;
//                        }
//
//                        @Override
//                        public List<String> getAirColumnsString(){
//                           List<String> list = context.getColumnsString(this.getGmaName(),this.getMaName(),this.getName());
//                           System.out.println(list);
//                           return list;
//                        }
//                        \s
//                        @Override
//                        public List<String> getAirColumnsByGroupNameString(String groupName){
//                            List<String> list = context.getColumnsByGroupNameString(this.getGmaName(),this.getMaName(),this.getName(),groupName);
//                            System.out.println(list);
//                            return list;
//                        }
//
//
//                        @Override
//                        public String replaceCharacters(String value){
//                           if(value==null){;
//                               return null;
//                           }
//                             return value.replace("'","''");
//                        }
//                        @Override
//                        public String getTableName(){
//                            return this.getName();
//                        }
//                """;
//        Files.writeString(path, get, StandardOpenOption.APPEND);
    }

//    private void generateGetColumnNamesMethod(ColumnJson[] columns, Path path) {
//        StringBuilder sb = new StringBuilder();
//
//        for (ColumnJson col : columns) {
//            String className = "COL_" + col.getName();
//            if (col.isUniqueIdentifier()) {
//                sb.append(String.format("""
//                        get%s().getName(),
//                        """, className));
//
//
//            }
//        }
//        try {
//            if(!sb.isEmpty()) {
//                sb.setLength(sb.length() - 1);
//                String getUniqueIdentifierMethod = String.format("""
//                        @Override
//                        public List<String> getUniqueIdentifierColumns() {
//                            return List.of(new String[]{%s});
//                            }
//                        """, sb);
//                Files.writeString(path, getUniqueIdentifierMethod, StandardOpenOption.APPEND);
//            } else {
//                System.out.println("no columns is a uniqueIdentifier");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//
//        }
//
//    }

    private void generateGetCols(AirColumnJson[] columns, Path path) {

        String  colsList = String.join(",\n",Arrays.stream(columns).map(col ->String.format("\t\tAIRCOL_%s", col.getName())).toList());






        String getValuesMethod = String.format("""
                @Override
                public List<AirColumnTemplate> getAllColumns()  {
                    return List.of(
                %s
                    );
                }
                    
                """, colsList);


        try {
            Files.writeString(path, getValuesMethod, StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void generateTabCols(AirTableJson tab, Path path) throws IOException {
        for (int i = 0; i < tab.getColumns().length; i++) {
            String className = "AIRCOL_" + tab.getColumns()[i].getName();
            String colFormat = String.format("""
                                private final AirColumnTemplate %1$s = new %1$s();
                            
                                public AirColumnTemplate get%1$s() {
                                    return %1$s;
                                }
                            
                            
                            
                            
                            
                            """,
                    className
            );

            Files.writeString(path, colFormat, StandardOpenOption.APPEND);

        }
//        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition
    }

     void generateAirTabImports(Path path) throws IOException {
        String imports = """
                import java.util.List;
                import java.sql.SQLException;
                import jakarta.persistence.EntityManager;
                
                import java.sql.Connection;
                
                """;
        // Ensure directory exists
        Files.createDirectories(path.getParent());

        // Write imports to the file
        Files.writeString(path, imports, StandardOpenOption.APPEND);
    }




    static void generateAirTabFields(AirTableJson tab, String gmaName, String maName, String appId, Path path) throws IOException {
        String className = "AIRTAB_" + tab.getName();


        // Ensure directory exists
        Files.createDirectories(path.getParent());

        // Build the class string
        String classFormat = String.format("""
                        public class %s extends AirTableTemplate {
                        
                            public %s() {
                                super(
                                    %s,
                                    %s,
                                    %s,
                                    %s,
                                    %s,
                                    %s,
                                    %s
                                );
                            }
                        
                        
                        
                        """,
                className,
                className,
                wrapWithQuotes(tab.getName()),
                wrapWithQuotes(tab.getDescription()),
                toArrayLiteral(safeArray(tab.getTags())),  // Better than Arrays.toString
                wrapWithQuotes(tab.getTableId()),
                wrapWithQuotes(appId),
                wrapWithQuotes(gmaName),
                wrapWithQuotes(maName)
        );

// Write or overwrite file
        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
        String context = "\n KDBContext context = KDBContext.KDB_CONTEXT;\n";
        Files.writeString(path, context, StandardOpenOption.APPEND);


    }
}
