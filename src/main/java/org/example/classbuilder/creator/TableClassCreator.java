package org.example.classbuilder.creator;


import org.example.jsonBuilder.gma.ma.tables.QueryJson;
import org.example.jsonBuilder.gma.ma.tables.TableJson;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static org.example.classbuilder.builder.ClassCreator.*;
import static org.example.classbuilder.creator.GMAClassCreator.generatePackageDeclaration;
import static org.example.classbuilder.creator.GMAClassCreator.javaDir;

public class TableClassCreator {
    String name;
    List<ColumnClassCreator> columnList;
    List<QueryClassCreator> queryList;
    List<String> curDir;
    List<String> pkgDir;


    public TableClassCreator(TableJson table, List<String> curDir, List<String> pkgDir,String gmaName,String maName) throws IOException {

        this.name = table.getName();

        // Make a copy so each table has its own unique directory path
        this.curDir = new java.util.ArrayList<>(curDir);
        this.curDir.add(name); // e.g. add table name

//        System.out.println(curDir+" CURDIR");

        this.pkgDir = new java.util.ArrayList<>(pkgDir);
        this.pkgDir.add(name);

//        System.out.println("creating Table for " + name);
//        System.out.println("package name = "+String.join(".",pkgDir));
                ;

        this.columnList = new java.util.ArrayList<>();
        this.queryList = new java.util.ArrayList<>();

        for (int i = 0; i < table.getColumns().length; i++) {

            ColumnClassCreator colCreator = new ColumnClassCreator(this.curDir,this.pkgDir, table.getColumns()[i]);
            this.columnList.add(colCreator);
        }
        for(int j = 0; j< table.getTableQueries().length;j++){
            QueryClassCreator queryCreator = new QueryClassCreator(this.curDir,this.pkgDir,table.getTableQueries()[j]);
            this.queryList.add(queryCreator);
        }

        createTabClass(table,this.curDir,this.pkgDir,gmaName,maName);

    }
    private void createTabClass(TableJson tab, List<String> curDir,List<String>pkgDir,String gmaName,String maName) throws IOException {
        String className = "TAB_" + tab.getName();

        Path path = Path.of( String.join(File.separator, curDir), className + ".java");
        Files.createDirectories(path.getParent());
        Files.writeString(path, "", StandardOpenOption.CREATE);

        List<String> childDirs = new ArrayList<>();
        for(ColumnJson col : tab.getColumns()){
            childDirs.add("columns"+".COL_"+col.getName());
        }
        List<String> childDirQuery = new ArrayList<>();
        for(QueryJson query: tab.getTableQueries()){
            childDirs.add("queries"+".QUE_"+query.getName());
        }



            generatePackageDeclaration(pkgDir, childDirQuery, List.of(new String[]{"TableTemplate", "QueryTemplate","ColumnTemplate"}),path,false);
            generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{}),path,true);
            generateTabImports(path);
            generateContextImport(path);

//            generateExceptions(exceptions,path);
            generateTabFields(tab,gmaName,maName,path);

            generateQueries(tab,path);
            generateTabCols(tab,path);
            generateTabGetSet(tab,path);
            generateColumnMethods(tab,gmaName,maName,path);



            Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition
    }

    static public void generateContextImport( Path path) {
        StringBuilder packagePath = new StringBuilder(javaDir);

        packagePath.append(".ContextInstance.KDBContext;\n\n");
        String importStatement = String.format("import %s", packagePath.toString());
        try {
            Files.writeString(path, importStatement, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateTabGetSet(TableJson tab, Path path) {

    }




    private void generateColumnMethods(TableJson tab,String gmaName,String maName, Path path) throws IOException {
//        generateGetColumnsMethod(tab.getColumns(),path);
//        generateGetColumnsByNameMethod(tab.getColumnGroups(),path);
            generateGetColumnNamesMethod(gmaName,maName,path);

            generateGetValues(tab.getColumns(),path);



    }

    private void generateGetColumnNamesMethod(String gmaName, String maName, Path path) throws IOException {
        String get ="""
                    @Override
                    public List<String> getColumns(){
                       List<String> list = context.getColumns(this.getGmaName(),this.getMaName(),this.getName());
                       System.out.println(list);
                       return list;
                    }
                    \s
                    @Override
                    public List<String> getColumnsByGroupName(String groupName){
                        List<String> list = context.getColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
                        System.out.println(list);
                        return list;
                    }
                    \s
                    @Override
                    public List<String> getUniqueIdentifierColumns(){
                        List<String> list = context.getUniqueIdentifierColumns(this.getGmaName(),this.getMaName(),this.getName());
                        System.out.println(list);
                        return list;
                    }
                    \s
                    @Override
                    public List<String> getUniqueIdentifierColumnsByGroupName(String groupName){
                        List<String> list = context.getUniqueIdentifierColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
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
        Files.writeString(path,get, StandardOpenOption.APPEND);
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
            getEntitiesList.append(String.format("          get%s().getEntityValue().getValue().toString(),\n", className));

            // Add to variableStrings for String.format placeholders
            variableStrings.append("'%s',");
        }

        // Remove last comma and newline correctly
        if (getEntitiesList.length() > 2) {
            getEntitiesList.setLength(getEntitiesList.length() - 2); // removes last ",\n"
        }
        if (variableStrings.length() > 0) {
            variableStrings.setLength(variableStrings.length() - 1); // removes last ","
        }

        String getValuesMethod = String.format("""
            @Override
            public String getValues()  {
                return String.format("(%s)\\n",
                        %s
                );
            }
            """, variableStrings, getEntitiesList);

        String getValuesArgMethod = String.format("""
            @Override
            public String getValues(String arg) {
                return String.format("(%s)\\n",
%s
                );
            }
            """, variableStrings, getEntitiesList);

        try {
            Files.writeString(path, getValuesMethod, StandardOpenOption.APPEND);
            Files.writeString(path, getValuesArgMethod, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void generateTabCols(TableJson tab,Path path) throws IOException {
        for(int i=0; i< tab.getColumns().length; i++) {
            String className = "COL_" + tab.getColumns()[i].getName();
            String colFormat= String.format("""
                private final ColumnTemplate %1$s = new %1$s();
                
                public ColumnTemplate get%1$s() {
                    return %1$s;
                }
                
                
                
                
                
            """,
                    className
            );

            Files.writeString(path,colFormat, StandardOpenOption.APPEND);

        }
//        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition
    }

    static void generateTabImports(Path path) throws IOException {
        String imports = """
                import java.util.List;
                
                """;
        // Ensure directory exists
        Files.createDirectories(path.getParent());

        // Write imports to the file
        Files.writeString(path, imports, StandardOpenOption.APPEND);
    }

    static void generateQueries(TableJson tab,Path path) throws IOException {
        for(int i=0; i< tab.getTableQueries().length; i++) {
            String className = "QUE_" + tab.getTableQueries()[i].getName();
            String colFormat=
                    String.format("""
                    
                    private final QueryTemplate %s = new %s();
                    public QueryTemplate get%s() {
                        return %s;
                    }
                    
                    
                    """,
                            className,className,className,className

                    );
            Files.writeString(path,colFormat, StandardOpenOption.APPEND);

        }
//        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition

    }


    static void generateTabFields(TableJson tab,String gmaName,String maName, Path path) throws IOException {
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
                wrapWithQuotes(maName)
        );

// Write or overwrite file
        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
        String context = "\n KDBContext context = KDBContext.KDB_CONTEXT;\n";
        Files.writeString(path, context, StandardOpenOption.APPEND);


    }

}
