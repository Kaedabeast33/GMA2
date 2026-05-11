package org.example.ClassOutputCreator;

import org.example.JsonBuilder.json.ma.tables.QueryJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.ClassOutputCreator.ClassCreator.*;
import static org.example.ClassOutputCreator.GMAClassCreator.*;

public class TableClassCreator {
    String name;
    List<ColumnClassCreator> columnList;
    List<QueryClassCreator> queryList;
    List<String> curDir;
    List<String> pkgDir;


    public TableClassCreator(TableJson table, List<String> curDir, List<String> pkgDir, String gmaName, String maName) throws IOException {
        System.out.println("Creating TableClassCreator for table: " + table.getName());
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
        this.queryList = new ArrayList<>();

        System.out.println("    creating Columns for Table " + table.getName());
        for (int i = 0; i < table.getColumns().length; i++) {

            System.out.println("    creating Column for " + table.getColumns()[i].getName());
            ColumnClassCreator colCreator = new ColumnClassCreator(this.curDir, this.pkgDir, table.getColumns()[i]);
            this.columnList.add(colCreator);
        }
        for (int j = 0; j < table.getTableQueries().length; j++) {
            QueryClassCreator queryCreator = new QueryClassCreator(this.curDir, this.pkgDir, table.getTableQueries()[j]);
            this.queryList.add(queryCreator);
        }

        createTabClass(table, this.curDir, this.pkgDir, gmaName, maName);

    }

    private void createTabClass(TableJson tab, List<String> curDir, List<String> pkgDir, String gmaName, String maName) throws IOException {
        String className = "TAB_" + tab.getName();

        Path path = Path.of(String.join(File.separator, curDir), className + ".java");
        Files.createDirectories(path.getParent());
        Files.writeString(path, "", StandardOpenOption.CREATE);

        List<String> childDirs = new ArrayList<>();
        for (ColumnJson col : tab.getColumns()) {
            childDirs.add("columns" + ".COL_" + col.getName());
        }
        List<String> childDirQuery = new ArrayList<>();
        for (QueryJson query : tab.getTableQueries()) {
            childDirs.add("queries" + ".QUE_" + query.getName());
        }


        generatePackageDeclaration(pkgDir, childDirQuery, List.of(new String[]{"TableTemplate", "QueryTemplate", "ColumnTemplate"}), List.of(new String[]{"JsonBuilder.json.ma.tables.columns.ColumnJson", "bank.OutputClassBank.QueryResult","bank.OutputClassBank.KdbColumnPersona"}), List.of(new String[]{"bank.OutputClassBank.KdbColumnWrapper.safeGetValue"}), path, false);
        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{}), path, true);
        generateTabImports(path);
        generateContextImport(path);

//            generateExceptions(exceptions,path);
        generateTabFields(tab, gmaName, maName, path);

        generateQueries(tab, path);
        generateTabCols(tab, path);
        generateTabGetSet(tab, path);
        generateColumnMethods(tab, path);


        Files.writeString(path, "\n}", StandardOpenOption.APPEND); // Close the class definition
    }

    static public void generateContextImport(Path path) {
        StringBuilder packagePath = new StringBuilder(javaDir);
        StringBuilder packagePath2 = new StringBuilder(javaDir);
        StringBuilder packagePath3 = new StringBuilder(javaDir);


        packagePath.append(resourceDir).append("KDBContext;\n");
        packagePath3.append(resourceDir).append("KdbContextAi;\n");

        packagePath2.append(resourceDir).append("KdbColumnPersona;\n\n");


        String importStatement = String.format("import %s", packagePath.toString());
        importStatement += String.format("import %s", packagePath2.toString());
        importStatement += String.format("import %s", packagePath3.toString());
        try {
            Files.writeString(path, importStatement, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateTabGetSet(TableJson tab, Path path) {

    }


    private void generateColumnMethods(TableJson tab, Path path) throws IOException {
//        generateGetColumnsMethod(tab.getColumns(),path);
//        generateGetColumnsByNameMethod(tab.getColumnGroups(),path);
        generateGetColumnNamesMethod(path);

        generateGetValues(tab.getColumns(), path);

        generateUploadStatements(path);
        generateQueryMethods(path);


    }

    private void generateQueryMethods(Path path) {
        String getQueryByCol = """
                    @Override
                    public QueryResult getQueryByCols(List<KdbColumnPersona> byColumns,
                                                      EntityManager entityManager) throws SQLException {
                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, entityManager);
                    }
                
                    @Override
                    public QueryResult getQueryByCols(List<KdbColumnPersona> byColumns,
                                                      List<KdbColumnPersona> getColumns,
                                                      EntityManager entityManager) throws SQLException {
                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, getColumns, entityManager);
                    }
                
                    @Override
                    public QueryResult getQuery(List<KdbColumnPersona> getColumns,
                                                EntityManager entityManager) throws SQLException {
                        return context.getQuery(this.getGmaName(), this.getMaName(), this.getName(), getColumns, entityManager);
                    }
                
                    
                
                    @Override
                    public QueryResult getQueryByCols(List<KdbColumnPersona> byColumns) throws SQLException {
                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns);
                    }
                
                    @Override
                    public QueryResult getQueryByCols(List<KdbColumnPersona> byColumns,
                                                      List<KdbColumnPersona> getColumns) throws SQLException {
                        return context.getQueryByColumns(this.getGmaName(), this.getMaName(), this.getName(), byColumns, getColumns);
                    }
                
                    @Override
                    public QueryResult getQuery(List<KdbColumnPersona> getColumns) throws SQLException {
                        return context.getQuery(this.getGmaName(), this.getMaName(), this.getName(), getColumns);
                    }
                """;
        try {
            Files.writeString(path, getQueryByCol, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateUploadStatements(Path path) throws IOException {

        String getUpload = """
                        @Override
                        public String getUploadDeleteGma(List<KdbColumnPersona> toDeleteBy,Boolean includeNullValues ) {
                            return context.getUploadDeleteGma(this.getGmaName(),this.getMaName(),this.getName(),toDeleteBy,includeNullValues);
                        }
                
                        @Override
                        public String getUploadUpdateGma(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns ) {
                            return context.getUploadUpdateGma(this.getGmaName(),this.getMaName(),this.getName(),toUpdateBy,includeNullValues,updateColumns);
                        }
                
                        @Override
                        public String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey ) {
                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues,insertColumns,includePrimaryKey);
                        }
                
                        @Override
                        public String getUploadInsertGma(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues);
                        }
                
                        @Override
                        public String getUploadInsertGma() {
                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName());
                        }
                
                        @Override
                        public String getUploadInsertGma(Boolean includePrimaryKey ) {
                            return context.getUploadInsertGma(this.getGmaName(),this.getMaName(),this.getName(),includePrimaryKey);
                        }
                
                
                        @Override
                        public String getUploadDelete(List<KdbColumnPersona> toDeleteBy,Boolean includeNullValues ) {
                            return context.getUploadDelete(this.getGmaName(),this.getMaName(),this.getName(),toDeleteBy,includeNullValues);
                        }
                
                        @Override
                        public String getUploadUpdate(List<KdbColumnPersona> toUpdateBy,Boolean includeNullValues,List<KdbColumnPersona> updateColumns ) {
                            return context.getUploadUpdate(this.getGmaName(),this.getMaName(),this.getName(),toUpdateBy,includeNullValues,updateColumns);
                        }
                
                        @Override
                        public String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey ) {
                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues,insertColumns,includePrimaryKey);
                        }
                
                        @Override
                        public String getUploadInsert(List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),toInsertBy,includeNullValues);
                        }
                
                        @Override
                        public String getUploadInsert() {
                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName());
                        }
                
                        @Override
                        public String getUploadInsert(Boolean includePrimaryKey ) {
                            return context.getUploadInsert(this.getGmaName(),this.getMaName(),this.getName(),includePrimaryKey);
                        }
                """;

        Files.writeString(path, getUpload, StandardOpenOption.APPEND);
    }

    private void generateGetColumnNamesMethod(Path path) throws IOException {
        String get = """
                        @Override
                        public List<ColumnJson> getColumns(){
                           List<ColumnJson> list = context.getColumns(this.getGmaName(),this.getMaName(),this.getName());
                           return list;
                        }
                        \s
                        @Override
                        public List<ColumnJson> getColumnsByGroupName(String groupName){
                            List<ColumnJson> list = context.getColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
                            return list;
                        }
                        \s
                        @Override
                        public List<ColumnJson> getUniqueIdentifierColumns(){
                            List<ColumnJson> list = context.getUniqueIdentifierColumns(this.getGmaName(),this.getMaName(),this.getName());
                            return list;
                        }
                        \s
                        @Override
                        public List<ColumnJson> getUniqueIdentifierColumnsByGroupName(String groupName){
                            List<ColumnJson> list = context.getUniqueIdentifierColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
                            return list;
                            }
                        \s
                        @Override
                        public List<String> getColumnsString(){
                           List<String> list = context.getColumnsString(this.getGmaName(),this.getMaName(),this.getName());
                           System.out.println(list);
                           return list;
                        }
                        \s
                        @Override
                        public List<String> getColumnsByGroupNameString(String groupName){
                            List<String> list = context.getColumnsByGroupNameString(this.getGmaName(),this.getMaName(),this.getName(),groupName);
                            System.out.println(list);
                            return list;
                        }
                        \s
                        @Override
                        public List<String> getUniqueIdentifierColumnsString(){
                            List<String> list = context.getUniqueIdentifierColumnsString(this.getGmaName(),this.getMaName(),this.getName());
                            System.out.println(list);
                            return list;
                        }
                        \s
                        @Override
                        public List<String> getUniqueIdentifierColumnsByGroupNameString(String groupName){
                            List<String> list = context.getUniqueIdentifierColumnsByGroupNameString(this.getGmaName(),this.getMaName(),this.getName(),groupName);
                            System.out.println(list);
                            return list;
                            }
                        \s
                
                        @Override
                        public String replaceCharacters(String value){
                           if(value==null){;
                               return null;
                           }
                             return value.replace("'","''");
                        }
                        @Override
                        public String getTableName(){
                            return this.getName();
                        }
                """;
        Files.writeString(path, get, StandardOpenOption.APPEND);
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

    private void generateGetValues(ColumnJson[] columns, Path path) {
        StringBuilder variableStrings = new StringBuilder();
        StringBuilder getEntitiesList = new StringBuilder();

        for (ColumnJson col : columns) {
            String className = "COL_" + col.getName();

            // Add to getEntitiesList
            getEntitiesList.append(String.format("          safeGetValue(get%s()),\n", className));

            // Add to variableStrings for String.format placeholders
            variableStrings.append("%s,");
        }
        String getEntitiesListArg = getEntitiesList + "           arg";

        String variableStringsArg = variableStrings + "%s";


        // Remove last comma and newline correctly
        if (getEntitiesList.length() > 2) {
            getEntitiesList.setLength(getEntitiesList.length() - 2); // removes last ",\n"
        }
        if (!variableStrings.isEmpty()) {
            variableStrings.setLength(variableStrings.length() - 1); // removes last ","
        }

        String getValuesMethod = String.format("""
                @Override
                public String getValues()  {
                    return String.format("(%s)\\n ,",
                            %s
                    );
                }
                """, variableStrings, getEntitiesList);

        String getValuesArgMethod = String.format("""
                            @Override
                            public String getValues(String arg) {
                                return String.format("(%s)\\n,",
                %s
                                );
                            }
                """, variableStringsArg, getEntitiesListArg);

        try {
            Files.writeString(path, getValuesMethod, StandardOpenOption.APPEND);
            Files.writeString(path, getValuesArgMethod, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void generateTabCols(TableJson tab, Path path) throws IOException {
        for (int i = 0; i < tab.getColumns().length; i++) {
            String className = "COL_" + tab.getColumns()[i].getName();
            String colFormat = String.format("""
                                private final ColumnTemplate %1$s = new %1$s();
                            
                                public ColumnTemplate get%1$s() {
                                    return %1$s;
                                }
                            
                            
                            
                            
                            
                            """,
                    className
            );

            Files.writeString(path, colFormat, StandardOpenOption.APPEND);

        }
//        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition
    }

    static void generateTabImports(Path path) throws IOException {
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

    static void generateQueries(TableJson tab, Path path) throws IOException {
        for (int i = 0; i < tab.getTableQueries().length; i++) {
            String className = "QUE_" + tab.getTableQueries()[i].getName();
            String colFormat =
                    String.format("""
                                    
                                    private final QueryTemplate %s = new %s();
                                    public QueryTemplate get%s() {
                                        return %s;
                                    }
                                    
                                    
                                    """,
                            className, className, className, className

                    );
            Files.writeString(path, colFormat, StandardOpenOption.APPEND);

        }
//        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition

    }


    static void generateTabFields(TableJson tab, String gmaName, String maName, Path path) throws IOException {
        String className = "TAB_" + tab.getName();


        // Ensure directory exists
        Files.createDirectories(path.getParent());

        // Build the class string
        String classFormat = String.format("""
                        public class %s extends TableTemplate {
                        
                            public %s() {
                                super(
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
                wrapWithQuotes(gmaName),
                wrapWithQuotes(tab.getIdentifier().getMaName())
        );

// Write or overwrite file
        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
        String context = "\n KDBContext context = KDBContext.KDB_CONTEXT;\n";
        Files.writeString(path, context, StandardOpenOption.APPEND);


    }
}