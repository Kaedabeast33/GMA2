package org.example.classbuilder.creator;

import org.example.jsonBuilder.gma.ma.MAJson;
import org.example.jsonBuilder.gma.ma.tables.TableJson;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.classbuilder.builder.ClassCreator.*;
import static org.example.classbuilder.creator.GMAClassCreator.generatePackageDeclaration;
import static org.example.classbuilder.creator.TableClassCreator.generateContextImport;

public class MAClassCreator {
    String name;
    List<TableClassCreator> tableList;
    List<String> curDir;
    List<String> pkgDir;

    public MAClassCreator(MAJson ma, List<String> curDir, List<String> pkgDir, String gmaName) throws IOException {
        this.name = ma.getName();

        // Create a new directory path for this MA level
        this.curDir = new ArrayList<>(curDir); // ← makes a shallow copy
        this.curDir.add(this.name); // now unique to this MA
//        System.out.println(curDir+" CURDIR");

        this.pkgDir = new ArrayList<>(pkgDir);
        this.pkgDir.add(this.name);




//        System.out.println("creating MA for " + this.name);

        this.tableList = Arrays.stream(ma.getTables())
                .map(table -> {
                    try {
                        return new TableClassCreator(table, this.curDir,this.pkgDir,gmaName,this.name); // pass unique path
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        createMaClass(ma,this.curDir,this.pkgDir,gmaName);
    }
    private void createMaClass(MAJson ma, List<String> curDir, List<String> pkgDir, String gmaName) throws IOException {
        String className = "MA_" + ma.getName();
        Path path = Path.of(String.join(File.separator, curDir),className + ".java");
//        System.out.println("creating ma for " + ma.getName());
        List<String> childDirs = new ArrayList<>();
        for(TableJson table : ma.getTables()){
            childDirs.add(table.getName()+".TAB_"+table.getName());
        }

        Files.writeString(path, "", StandardOpenOption.CREATE);

        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{"MATemplate", "TableTemplate"}),path,false);
        generateContextImport(path);
        generateMaFields(ma,gmaName,path);
        generateMaTables(ma,path);

        Files.writeString(path,"\n}", StandardOpenOption.APPEND); // Close the class definitionKDBCO

    }



    private void generateMaTables(MAJson ma, Path path) throws IOException {
        for(int i=0; i< ma.getTables().length; i++) {
            String className = "TAB_" + ma.getTables()[i].getName();
            String tabFormat=
                    String.format("""
                    
                        private final %s %s = new %s();
                        public %s get%s() {
                            return %s;
                        }
                    
                    """,
                            className,className,className,className,className,className

                    );
            Files.writeString(path,tabFormat, StandardOpenOption.APPEND);

        }

    }


    static void generateMaFields(MAJson ma,String gmaName, Path path) throws IOException {
        String className = "MA_" + ma.getName();


        // Ensure directory exists
        Files.createDirectories(path.getParent());

        // Build the class string
        String classFormat = String.format("""
        public class %s extends MATemplate {

            public %s() {
                super(
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

                wrapWithQuotes(ma.getName()),
                wrapWithQuotes(ma.getDescription()),
                toArrayLiteral(safeArray(ma.getTags())),
                wrapWithQuotes(ma.getMaId()),
                wrapWithQuotes(gmaName)

        );

        // Write or overwrite file
        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
        String context = "\n KDBContext context = KDBContext.KDB_CONTEXT;\n";
        Files.writeString(path, context, StandardOpenOption.APPEND);
    }


}
