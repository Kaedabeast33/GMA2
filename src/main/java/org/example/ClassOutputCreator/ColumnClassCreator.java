package org.example.ClassOutputCreator;


import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

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

public class ColumnClassCreator {
    String name;


    public ColumnClassCreator(List<String> curDir, List<String> pkgDir, ColumnJson column) throws IOException {
        List<String> safeDir = new ArrayList<>(curDir); // Make a safe copy of the directory path
        List<String> safePkgDir = new ArrayList<>(pkgDir);
//        System.out.println("creating Column for " + column);
        safeDir.add("columns");

        this.name = column.getName();

        // Make a safe copy of the directory path

//        colDir.add(name); // Append the column name (or omit if not needed in the path)

        generateColFields(column, safeDir, safePkgDir);
    }


    private static void generateColFields(ColumnJson columnJson, List<String> curDir, List<String> pkgDir) throws IOException {
        String className = "COL_" + columnJson.getName();
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
        generatePackageDeclaration(pkgDir, childDirs, new ArrayList<>(List.of(new String[]{"ColumnTemplate"})), path, false);


//        if(columnJson.getType())

        String classFormat = """
                public class %s extends ColumnTemplate {
                
                    public %s() {
                        super(
                            %s,  // name
                            %s,  // columnId
                            %s,  // description
                            %s,  // tags
                            %s,  // isNullable
                            %s,  // isEditable
                            %s,  // isUnique
                            %s,  // isRequired
                            %s,  // type
                            %s,  // defaultValue
                            %s,  // columnGroups
                            %s,  // isUniqueIdentifier
                            %s,  // uniqueIdentifierGroups
                            %s,  // isIndex
                            %s,  // indexGroups
                            %s,  // referenceColumns
                            %s,  // fieldType
                            %s.class,  // kdbConverter
                            %s,  // isPrimaryKey
                            %s  // isEmbedding
                
                
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
                columnJson.isNullable(),
                columnJson.isEditable(),
                columnJson.isUnique(),
                columnJson.isRequired(),
                wrapWithQuotes(columnJson.getType()),
                wrapWithQuotes(columnJson.getDefaultValue()),
                toArrayLiteral(safeNameList(columnJson.getColumnGroups(), obj -> obj.getName()).toArray(new String[0])),
                columnJson.isUniqueIdentifier(),
                toArrayLiteral(safeNameList(columnJson.getUniqueIdentifierGroups(), obj -> obj.getName()).toArray(new String[0])),
                columnJson.isIndex(),
                toArrayLiteral(safeNameList(columnJson.getIndexGroups(), obj -> obj.getName()).toArray(new String[0])),
                formatReferenceColumns(columnJson.getReferenceColumn()),
                FIELD_TYPE_MAP.get(columnJson.getFieldType()),
                columnJson.getKdbConverter(),
                columnJson.isPrimaryKey(),
                columnJson.isEmbedding()

        );

        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
    }


}


