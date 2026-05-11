package org.example.ClassOutputCreator.ai;

import org.example.JsonBuilder.json.ma.AirMAJson;
import org.example.JsonBuilder.json.ma.tables.AirTableJson;

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
    List<AirTableClassCreator> tableList;
    List<String> curDir;
    List<String> pkgDir;

    public AiMAClassCreator(AirMAJson ma, List<String> curDir, List<String> pkgDir, String gmaName) throws IOException {
        this.name = ma.getName();

        // Create a new directory path for this MA level
        this.curDir = new ArrayList<>(curDir); // ← makes a shallow copy
        this.curDir.add(this.name); // now unique to this MA
//        System.out.println(curDir+" CURDIR");

        this.pkgDir = new ArrayList<>(pkgDir);
        this.pkgDir.add(this.name);


        System.out.println("creating MA for " + this.name);

        this.tableList = Arrays.stream(ma.getTables())

                .map(table -> {
                    try {
                        return new AirTableClassCreator(table, this.curDir, this.pkgDir, gmaName, this.name,ma.getAppId()); // pass unique path
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        createMaClass(ma, this.curDir, this.pkgDir, gmaName);
    }

    private void createMaClass(AirMAJson ma, List<String> curDir, List<String> pkgDir, String gmaName) throws IOException {
        String className = "AIRMA_" + ma.getName();
        Path path = Path.of(String.join(File.separator, curDir), className + ".java");
//        System.out.println("creating ma for " + ma.getName());
        List<String> childDirs = new ArrayList<>();
        for (AirTableJson table : ma.getTables()) {
            childDirs.add(table.getName() + ".AIRTAB_" + table.getName());
        }

        Files.writeString(path, "", StandardOpenOption.CREATE);

        generatePackageDeclaration(pkgDir, childDirs, List.of(new String[]{"MATemplate", "AirTableTemplate"}), path, false);
        generatePomImports(path,List.of("java.text.ParseException","java.util.List","org.springframework.stereotype.Component"));
        generateBankImports(path, List.of("AirtableInterface","KdbAirColumnPersona"));
        generateContextImport(path);
        generateAirMaFields(ma, gmaName, path);
        generateAirMaTables(ma, path);
        generateAirMaSaveAll(ma,path);


        Files.writeString(path, "\n}", StandardOpenOption.APPEND); // Close the class definitionKDBCO

    }

    private void generateAirMaSaveAll(AirMAJson ma, Path path) {
        String initial;



            initial = """
                        public void saveAllAirtable(AirtableInterface table, List<AirtableInterface> entities, String bearer, List<KdbAirColumnPersona> upsertValues,List<KdbAirColumnPersona> byCols) throws ParseException {
                             context.saveAllAirtable(table,entities,bearer,upsertValues,byCols);
                        }
                        
                        // public void saveAllAirtable(AirtableInterface table, List<AirtableInterface> entities, String bearer, List<KdbAirColumnPersona> upsertValues,List<KdbAirColumnPersona> byCols) throws ParseException {
                           //  context.saveAllAirtable(table,entities,upsertStrings,checks);
                        // }
                    """;





        try {
            Files.writeString(path, initial, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void generateAirMaTables(AirMAJson ma, Path path) throws IOException {
        for (int i = 0; i < ma.getTables().length; i++) {
            String className = "AIRTAB_" + ma.getTables()[i].getName();
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


    static void generateAirMaFields(AirMAJson ma, String gmaName, Path path) throws IOException {
        String className = "AIRMA_" + ma.getName();

        // Ensure directory exists
        Files.createDirectories(path.getParent());
        String persistenceContext = "";
        // corrected guard: check for null OR empty before using value

        // Build the class string
        String classFormat = String.format("""

                        public class %s extends MATemplate {

                            %s
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
                persistenceContext,
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
