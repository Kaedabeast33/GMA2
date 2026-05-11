package org.example.ClassOutputCreator.ai;

import org.example.JsonBuilder.json.ma.AiMAJson;
import org.example.JsonBuilder.json.ma.tables.columns.AiColumnJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.bank.AppConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.ClassOutputCreator.ClassCreator.*;
import static org.example.ClassOutputCreator.GMAClassCreator.*;
import static org.example.ClassOutputCreator.TableClassCreator.generateContextImport;

public class AiMAClassCreator {
    String name;
    static String javaDir = AppConfig.getJavaDir();
    List<AiColumnClassCreator> colList;
    List<String> curDir;
    List<String> pkgDir;

    public AiMAClassCreator(AiMAJson ma, List<String> curDir, List<String> pkgDir, String gmaName) throws IOException {
        this.name = ma.getName();

        // Create a new directory path for this MA level
        this.curDir = new ArrayList<>(curDir); // ← makes a shallow copy
        this.curDir.add(this.name); // now unique to this MA
//        System.out.println(curDir+" CURDIR");

        this.pkgDir = new ArrayList<>(pkgDir);
        this.pkgDir.add(this.name);


        System.out.println("creating AiMA for " + this.name);

        this.colList = ma.getAllCols().stream()

                .map(col -> {
                    try {
                        return new AiColumnClassCreator(this.curDir, this.pkgDir, col); // pass unique path
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        createMaClass(ma, this.curDir, this.pkgDir);
    }

    private void createMaClass(AiMAJson ma, List<String> curDir, List<String> pkgDir) throws IOException {
        String className = "AIMA_" + ma.getName();
        Path path = Path.of(String.join(File.separator, curDir), className + ".java");
        Files.createDirectories(path.getParent());
//        System.out.println("creating ma for " + ma.getName());
        List<String> childDirs = new ArrayList<>();
        for (AiColumnJson col : ma.getAllCols()) {
            childDirs.add("columns" + "."+getAiColName(col));
        }




        Files.writeString(path, "", StandardOpenOption.CREATE);

        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{"ai.AiMATemplate", "ai.AiColumnTemplate"}), path, false);
        generatePomImports(path,List.of("java.text.ParseException","java.util.List","org.springframework.stereotype.Component","java.io.File;","jakarta.persistence.EntityManager","java.util.concurrent.ExecutionException"));
        generateBankImports(path, List.of("AiTableInterface","KdbAiColumnPersona"));
        generateAiImports(path,List.of("AiRagSchemaJson"));
        generateContextImport(path);
        generateAiMaFields(ma,  path);
        generateAiMaCols(ma, path);
        generateAiMaRagUpload(ma,path);


        Files.writeString(path, "\n}", StandardOpenOption.APPEND); // Close the class definitionKDBCO

    }

    private void generateAiImports(Path path, List<String> aiImports) {
        StringBuilder sb = new StringBuilder();
        for (String imp : aiImports) {
            sb.append("import ").append(javaDir) .append(".ai.").append(imp).append(";\n");
        }
        sb.append("\n");
        try {
            Files.writeString(path, sb.toString(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateAiMaRagUpload(AiMAJson ma, Path path) {
        String initial;
        initial = """
                public void reloadRag( List<File> files, AiRagSchemaJson json, String uploadGroup,EntityManager entityManager) throws Exception {
                             contextAi.reloadRag(this, files, json, uploadGroup,entityManager);
                        }
                        
                public void addDbSkeleton(List<File> files, AiRagSchemaJson json, String uploadGroup,  EntityManager entityManager)  {
                                contextAi.addDbSkeleton(this, files,json, uploadGroup , entityManager);
                        }        
                        
                public void addDbSkeleton(List<File> files,  String uploadGroup,  EntityManager entityManager) throws ExecutionException, InterruptedException {
                        contextAi.addDbSkeleton(this, files, uploadGroup , entityManager);
                }        
                """;
        try {
            Files.writeString(path, initial, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static  String getAiColName (AiColumnJson col){
        if(col.isKey()){
            return "K_"+col.getName();
        } else if(col.isPrimaryKey()){
            return "Pk_"+col.getName();
        } else {
            return "AiCOL_"+col.getName();
        }
    }


    private void generateAiMaCols(AiMAJson ma, Path path) throws IOException {
        for (int i = 0; i < ma.getAllCols().size(); i++) {
            String className =getAiColName(ma.getAllCols().get(i));
            String tabFormat =
                    String.format("""
                                    
                                        private final %s %s = new %s();
                                        public %s get%s() {
                                            return %s;
                                        }
                                    
                                    """,
                            className, className, className, className, className, className

                    );
            Files.writeString(path, tabFormat, StandardOpenOption.APPEND);

        }



    }


    static void generateAiMaFields(AiMAJson ma,  Path path) throws IOException {
        String className = "AIMA_" + ma.getName();

        // Ensure directory exists
        Files.createDirectories(path.getParent());
        String persistenceContext = "";
        List<String> primaryKeys = Arrays.stream(ma.getPrimaryKeys()).map(c-> "new "+ getAiColName(c)+"()").toList();
        List<String> keys = Arrays.stream(ma.getKeys()).map(c-> "new "+ getAiColName(c)+"()").toList();
        List<String> cols = Arrays.stream(ma.getCols()).map(c-> "new "+ getAiColName(c)+"()").toList();
        // corrected guard: check for null OR empty before using value

        // Build the class string
        String classFormat = String.format("""

                        public class %s extends AiMATemplate {

                            %s
                            public %s() {
                                super(
                                    %s,
                                    %s,
                                    %s,
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
                persistenceContext,
                className,

                wrapWithQuotes(ma.getName()),
                wrapWithQuotes(ma.getCustom()),
                wrapWithQuotes(ma.getDescription()),
                toArrayLiteral(safeArray(ma.getTags())),


                wrapWithQuotes(ma.getMaId()),
                toArrayLiteralNoQuotes(keys),
                toArrayLiteralNoQuotes(primaryKeys),
                toArrayLiteralNoQuotes(cols),
                toArrayLiteral(safeArray(ma.getUploadTypes())),
                wrapWithQuotes(ma.getSchema())
        );

        // Write or overwrite file
        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
        String context = "\n KDBContext context = KDBContext.KDB_CONTEXT;\nKdbContextAi contextAi = KdbContextAi.KDB_CONTEXT_AI;\n";
        Files.writeString(path, context, StandardOpenOption.APPEND);
    }

}
