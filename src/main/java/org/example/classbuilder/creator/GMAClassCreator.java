package org.example.classbuilder.creator;

import org.example.jsonBuilder.gma.GMAJson;
import org.example.jsonBuilder.gma.ma.MAJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.example.classbuilder.builder.ClassCreator.*;
import static org.example.classbuilder.creator.TableClassCreator.generateContextImport;

public class GMAClassCreator {
    List<MAClassCreator> maList;
    String name;
    List<String> curDir;
    List<String> pkgDir = new java.util.ArrayList<>();
    static String javaDir = "org.example";
    static String templateDir= "gma.templates";
    static String outputDir = "output";




    public GMAClassCreator(GMAJson gma, List<String> curDir) throws IOException {

        this.name = gma.getName();

        this.curDir = curDir;
        this.curDir.add(name);
//        System.out.println(curDir+" CURDIR GMA" +"added" +name);

        this.pkgDir.add(javaDir);
        this.pkgDir.add(outputDir);
        this.pkgDir.add(name);
//        packageDefault.add(name);

        this.maList = gma.getMa().stream()

                .map(ma -> {
                    try {
                        return new MAClassCreator(ma, this.curDir, this.pkgDir, this.name);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        createGmaClass(gma, this.curDir,this.pkgDir);
    }

    private void createGmaClass(GMAJson gma, List<String> curDir, List<String> pkgDir) throws IOException {
//        System.out.println("creating GMA for " + gma.getName());
        String className = "GMA_" + gma.getName();
        Path path = Path.of(String.join(File.separator, curDir), className + ".java");
        Files.createDirectories(path.getParent());

        Files.writeString(path, "", StandardOpenOption.CREATE);

        List<String> childDirs = new java.util.ArrayList<>();
        for(MAJson ma : gma.getMa()){
            childDirs.add(ma.getName()+".MA_"+ma.getName());
        }



        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{"GMATemplate", "MATemplate"}),path,false);
        generateContextImport(path);
        generateGmaFields(gma,path);
        generateGmaMas(gma,path);


    }

//    package cl.airtable.raw_k_airtable.columns;

//import org.example.gma.templates.ColumnTemplate;

    static void generateExceptions(List<String>exceptions,Path path){
        for (String exception:exceptions){
            try {
                Files.writeString(path, exception + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    };

    static  void generatePackageDeclaration(List<String> pkgDir, List<String> childDirs, List<String> templates,  Path path,boolean skipPackage) {
//        List<String> importDir = new java.util.ArrayList<>(pkgDir.stream().toList());
//        importDir.remove(importDir.size()-1);
        StringBuilder templatesDecleration = new StringBuilder();
        for(String template:templates){
            templatesDecleration.append("import ").append(javaDir).append(".").append(templateDir).append(".").append(template).append(";\n");
        }

        String templateImports = "import "+String.join(".",pkgDir);
        String packageName = String.join(".", pkgDir);
        String packageDeclaration = String.format("package %s;%n%n", packageName);


        StringBuilder importDecleration = new StringBuilder();
        for (String child : childDirs) {
            importDecleration.append(templateImports).append(".").append(child).append(";\n");
        }


        try {
            if(!skipPackage)Files.writeString(path, packageDeclaration+"\n", StandardOpenOption.APPEND);

            if(!templates.isEmpty())Files.writeString(path, templatesDecleration + "\n", StandardOpenOption.APPEND);
            Files.writeString(path, importDecleration + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateGmaMas(GMAJson gma, Path path) throws IOException {
        for(int i=0; i< gma.getMa().size(); i++) {
            String className = "MA_" + gma.getMa().get(i).getName();
            String maFormat=
                    String.format("""
                    
                        private final %s %s = new %s();
                        public %s get%s() {
                            return %s;
                        }
                    
                    """,
                            className,className,className,className,className,className

                    );
            Files.writeString(path,maFormat, StandardOpenOption.APPEND);

        }
        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definition
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
