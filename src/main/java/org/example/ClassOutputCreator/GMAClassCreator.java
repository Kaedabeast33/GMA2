package org.example.ClassOutputCreator;


import org.example.ClassOutputCreator.ai.AiMAClassCreator;
import org.example.ClassOutputCreator.airtable.AirMAClassCreator;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.AirMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.bank.AppConfig;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.example.ClassOutputCreator.ClassCreator.*;
import static org.example.ClassOutputCreator.TableClassCreator.generateContextImport;


public class GMAClassCreator {
    List<MAClassCreator> maList;
    List<AirMAClassCreator> airMaList;
    List<AiMAClassCreator> aiMaList;
    String name;
    List<String> curDir;
    List<String> pkgDir = new java.util.ArrayList<>();



    static String javaDir = AppConfig.getJavaDir();
    static String resourceDir = ".bank.OutputClassBank.";


    private static final String templateDir ="ClassOutputCreator.templates";


    public GMAClassCreator(GMAJson gma, List<String> curDir) throws IOException {

        this.name = gma.getName();

        this.curDir = curDir;
        this.curDir.add(name);
//        System.out.println(curDir+" CURDIR GMA" +"added" +name);

        this.pkgDir.add(javaDir);
        String outputDir = AppConfig.getOutputDir();
        this.pkgDir.add(outputDir);
        this.pkgDir.add(name);
//        packageDefault.add(name);
        System.out.println(pkgDir+" PKGDIR GMA" +"added" +name);

//        this.aiMaList  = gma.getAiMa().stream()

        this.airMaList = gma.getAirMa().stream()
                .map(ma -> {
                    try {
                        return new AirMAClassCreator(ma, this.curDir, this.pkgDir, this.name);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        this.maList = gma.getMa().stream()

                .map(ma -> {
                    try {
                        return new MAClassCreator(ma, this.curDir, this.pkgDir, this.name);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        this.aiMaList = gma.getAiMa().stream()

                .map(ma -> {
                    try {
                        return new AiMAClassCreator(ma, this.curDir, this.pkgDir, this.name);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        createGmaClass(gma, this.curDir, this.pkgDir);
    }

    private void createGmaClass(GMAJson gma, List<String> curDir, List<String> pkgDir) throws IOException {
        System.out.println("creating GMA for " + gma.getName());
        String className = "GMA_" + gma.getName();
        Path path = Path.of(String.join(File.separator, curDir), className + ".java");
        Files.createDirectories(path.getParent());

        Files.writeString(path, "", StandardOpenOption.CREATE);

        List<String> childDirs = new java.util.ArrayList<>();
        for (MAJson ma : gma.getMa()) {
            childDirs.add(ma.getName() + ".MA_" + ma.getName());
        }

        for (AirMAJson ma : gma.getAirMa()) {
            System.out.println("adding child dir for " + ma.getName());
            childDirs.add(ma.getName() + ".AIRMA_" + ma.getName());
        }




        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{"GMATemplate", "MATemplate"}), path, false);

        generateContextImport(path);
        generateGmaFields(gma, path);
        generateGmaMas(gma, path);
        generateGmaAirMas(gma, path);
        Files.writeString(path, "\n}", StandardOpenOption.APPEND); // Close the class definition


    }

    private void generateGmaAirMas(GMAJson gma, Path path) {
        for (int i = 0; i < gma.getAirMa().size(); i++) {
            String className = "AIRMA_" + gma.getAirMa().get(i).getName();
            String maFormat =
                    String.format("""
                                        
                                            private final %s %s = new %s();
                                            public %s get%s() {
                                                return %s;
                                            }
                                        
                                        """,
                            className, className, className, className, className, className

                    );
            try {
                Files.writeString(path, maFormat, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void generatePomImports(Path path, List<String> imports) {

        for( String importStr : imports){
            try {
                Files.writeString(path, "import " +importStr + ";\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void generateBankImports(Path path, List<String> imports){
        StringBuilder importDecleration = new StringBuilder();
        for (String importStr : imports) {
            importDecleration.append("import ").append(javaDir).append(".bank.OutputClassBank").append(".").append(importStr).append(";\n");
        }
        try {
            Files.writeString(path, importDecleration + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

//    package cl.airtable.raw_k_airtable.columns;

//import com.chipr.GMA.gma.templates.ColumnTemplate;

    static void generateExceptions(List<String> exceptions, Path path) {
        for (String exception : exceptions) {
            try {
                Files.writeString(path, exception + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    ;

    public static void generatePackageDeclaration(List<String> pkgDir, List<String> childDirs, List<String> templates, Path path, boolean skipPackage) {
//        List<String> importDir = new java.util.ArrayList<>(pkgDir.stream().toList());
//        importDir.remove(importDir.size()-1);
        StringBuilder templatesDecleration = new StringBuilder();
        for (String template : templates) {
            templatesDecleration.append("import ").append(javaDir).append(".").append(templateDir).append(".").append(template).append(";\n");
        }

        String templateImports = "import " + String.join(".", pkgDir);
        String packageName = String.join(".", pkgDir);
        String packageDeclaration = String.format("package %s;%n%n", packageName);


        StringBuilder importDecleration = new StringBuilder();
        for (String child : childDirs) {
            System.out.println("adding child dir for import decleration: " + child);
            importDecleration.append(templateImports).append(".").append(child).append(";\n");
        }


        try {
            if (!skipPackage) Files.writeString(path, packageDeclaration + "\n", StandardOpenOption.APPEND);

            if (!templates.isEmpty()) Files.writeString(path, templatesDecleration + "\n", StandardOpenOption.APPEND);
            Files.writeString(path, importDecleration + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void generatePackageDeclaration(List<String> pkgDir, List<String> childDirs, List<String> templates,List<String> jsons, Path path, boolean skipPackage) {
//        List<String> importDir = new java.util.ArrayList<>(pkgDir.stream().toList());
//        importDir.remove(importDir.size()-1);

        StringBuilder jsonDecleration = new StringBuilder();
        for(String json : jsons){
            jsonDecleration.append("import ").append(javaDir).append(".").append(json).append(";\n");
        }
        StringBuilder templatesDecleration = new StringBuilder();
        for (String template : templates) {
            templatesDecleration.append("import ").append(javaDir).append(".").append(templateDir).append(".").append(template).append(";\n");
        }

        String templateImports = "import " + String.join(".", pkgDir);
        String packageName = String.join(".", pkgDir);
        String packageDeclaration = String.format("package %s;%n%n", packageName);


        StringBuilder importDecleration = new StringBuilder();
        for (String child : childDirs) {
            importDecleration.append(templateImports).append(".").append(child).append(";\n");
        }


        try {
            if (!skipPackage) Files.writeString(path, packageDeclaration + "\n", StandardOpenOption.APPEND);

            if (!templates.isEmpty()) Files.writeString(path, templatesDecleration + "\n", StandardOpenOption.APPEND);
            if(!jsons.isEmpty()) Files.writeString(path, jsonDecleration + "\n", StandardOpenOption.APPEND);
            Files.writeString(path, importDecleration + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generatePackageDeclaration(List<String> pkgDir, List<String> childDirs, List<String> templates, List<String> jsons, List<String> statics, Path path, boolean skipPackage) {
//        List<String> importDir = new java.util.ArrayList<>(pkgDir.stream().toList());
//        importDir.remove(importDir.size()-1);

        StringBuilder staticDecleration = new StringBuilder();

        for(String stat: statics){
            staticDecleration.append("import static ").append(javaDir).append(".").append(stat).append(";\n");
        }

        StringBuilder jsonDecleration = new StringBuilder();
        for(String json : jsons){
            jsonDecleration.append("import ").append(javaDir).append(".").append(json).append(";\n");
        }
        StringBuilder templatesDecleration = new StringBuilder();
        for (String template : templates) {
            templatesDecleration.append("import ").append(javaDir).append(".").append(templateDir).append(".").append(template).append(";\n");
        }

        String templateImports = "import " + String.join(".", pkgDir);
        String packageName = String.join(".", pkgDir);
        String packageDeclaration = String.format("package %s;%n%n", packageName);


        StringBuilder importDecleration = new StringBuilder();
        for (String child : childDirs) {
            importDecleration.append(templateImports).append(".").append(child).append(";\n");
        }


        try {
            if (!skipPackage) Files.writeString(path, packageDeclaration + "\n", StandardOpenOption.APPEND);

            if (!templates.isEmpty()) Files.writeString(path, templatesDecleration + "\n", StandardOpenOption.APPEND);
            if(!jsons.isEmpty()) Files.writeString(path, jsonDecleration + "\n", StandardOpenOption.APPEND);
            if(!statics.isEmpty()) Files.writeString(path, staticDecleration + "\n", StandardOpenOption.APPEND);
            Files.writeString(path, importDecleration + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateGmaMas(GMAJson gma, Path path) throws IOException {
        for (int i = 0; i < gma.getMa().size(); i++) {
            String className = "MA_" + gma.getMa().get(i).getName();
            String maFormat =
                    String.format("""
                                    
                                        private final %s %s = new %s();
                                        public %s get%s() {
                                            return %s;
                                        }
                                    
                                    """,
                            className, className, className, className, className, className

                    );
            Files.writeString(path, maFormat, StandardOpenOption.APPEND);

        }

    }

    static void generateGmaFields(GMAJson gma, Path path) throws IOException {
        String className = "GMA_" + gma.getName();


        // Ensure directory exists
        Files.createDirectories(path.getParent());

        // Build the class string
        String classFormat = String.format("""
                        public class %s extends GMATemplate {
                        
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
                wrapWithQuotes(gma.getName()),
                wrapWithQuotes(gma.getDescription()),
                toArrayLiteral(safeArray(gma.getTags())),
                wrapWithQuotes(gma.getGmaId()),
                wrapWithQuotes(gma.getGmaSettings().get("DbType")),
                wrapWithQuotes(gma.getGmaSettings().get("Dialect"))
        );


        // Write or overwrite file
        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
        String context = "\n KDBContext context = KDBContext.KDB_CONTEXT;\n";
        Files.writeString(path, context, StandardOpenOption.APPEND);

    }




}
