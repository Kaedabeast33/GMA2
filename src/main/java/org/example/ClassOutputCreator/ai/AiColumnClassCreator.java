package org.example.ClassOutputCreator.ai;


import org.example.JsonBuilder.json.ma.tables.columns.AirColumnJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static org.example.ClassOutputCreator.ClassCreator.*;
import static org.example.ClassOutputCreator.GMAClassCreator.generatePackageDeclaration;
import static org.example.bank.commonValues.ValueTypes.FIELD_TYPE_MAP;

public class AiColumnClassCreator {
    String name;


    public AiColumnClassCreator(List<String> curDir, List<String> pkgDir, AirColumnJson column) throws IOException {
        List<String> safeDir = new ArrayList<>(curDir); // Make a safe copy of the directory path
        List<String> safePkgDir = new ArrayList<>(pkgDir);
//        System.out.println("creating Column for " + column);
        safeDir.add("columns");

        this.name = column.getName();

        // Make a safe copy of the directory path

//        colDir.add(name); // Append the column name (or omit if not needed in the path)

        generateColFields(column, safeDir, safePkgDir);
    }


    private static void generateColFields(AirColumnJson columnJson, List<String> curDir, List<String> pkgDir) throws IOException {
        String className = "AIRCOL_" + columnJson.getName();
        Path path = Path.of(String.join(File.separator, curDir) + File.separator + className + ".java");

        // Check if the path exists and is a directory
//        if (Files.exists(path) && Files.isDirectory(path)) {
//            throw new IOException("The path " + path + " is a directory, not a file.");
//        }

        // Ensure parent directories exist
        Files.createDirectories(path.getParent());
        Files.writeString(path, "", StandardOpenOption.CREATE);

        List<String> childDirs = new ArrayList<>();
        pkgDir.add("columns");
        generatePackageDeclaration(pkgDir, childDirs, new ArrayList<>(List.of(new String[]{"AirColumnTemplate"})), path, false);


//        if(columnJson.getType())

        String classFormat = """
                public class %s extends AirColumnTemplate {
                
                    public %s() {
                        super(
                            %s,  // name
                            %s,  // columnId
                            %s,  // description
                            %s,  // tags
                            
                            %s,  // fieldtype
                            %s,  // defaultValue
                            %s, // columnGroups
                            %s.class // kdbConverter
                            
                
                
                        );
                    }
                
                }
                """.formatted(
                className,
                className,
                wrapWithQuotes(columnJson.getName()),
                wrapWithQuotes(columnJson.getColumnId()),
                wrapWithQuotes(columnJson.getDescription()),
                toArrayLiteral(safeArray(columnJson.getTags())),

                FIELD_TYPE_MAP.get(columnJson.getFieldType()),
                wrapWithQuotes(columnJson.getDefaultValue()),
                toArrayLiteral(safeNameList(columnJson.getColumnGroups(), obj -> obj.getName()).toArray(new String[0])),
                columnJson.getKdbConverter()


        );

        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
    }
}
