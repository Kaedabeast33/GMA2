package org.example.classbuilder.creator;

import org.example.jsonBuilder.gma.ma.tables.QueryJson;
import org.example.jsonBuilder.gma.ma.tables.columns.QueryGroupDTO;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static org.example.classbuilder.builder.ClassCreator.*;
import static org.example.classbuilder.creator.GMAClassCreator.generatePackageDeclaration;

public class QueryClassCreator {

    String name;

    public QueryClassCreator(List<String> curDir, List<String> pkgDir, QueryJson query) throws IOException {
        List<String> safeDir = new ArrayList<>(curDir);
        List<String> safePkgDir = new ArrayList<>(pkgDir);
//        System.out.println("creating query for " + query);
        safeDir.add("queries");

        this.name = query.getName();

        generateQueFields(query, safeDir,safePkgDir);
    }



    private static void generateQueFields(QueryJson queryJson, List<String> curDir, List<String> pkgDir) throws IOException {
        String className = "QUE_" + queryJson.getName();
        Path path = Path.of(String.join(File.separator, curDir) + File.separator + className + ".java");
        if (Files.exists(path) && Files.isDirectory(path)) {
            throw new IOException("The path " + path + " is a directory, not a file.");
        }
        Files.createDirectories(path.getParent());
        Files.writeString(path,"",StandardOpenOption.CREATE);
        pkgDir.add("queries");
        generatePackageDeclaration(pkgDir,new ArrayList<>(),new ArrayList<>(List.of(new String[]{"QueryTemplate"})),path,false);

        String classFormat = """
        public class %s extends QueryTemplate {

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

        }
        """.formatted(
                className,
                className,
                wrapWithQuotes(queryJson.getName()),
                wrapWithQuotes(queryJson.getDescription()),
                toArrayLiteral(safeArray(queryJson.getTags())),
                wrapWithQuotes(queryJson.getId()),

                wrapWithQuotes(queryJson.getQueryType()),
                toArrayLiteral(safeArray(queryJson.getGroups()
                        .stream()
                        .map(QueryGroupDTO::getName)
                        .toArray(String[]::new))),



                wrapWithQuotes(queryJson.getContentString())

        );

        Files.writeString(path, classFormat, StandardOpenOption.APPEND);
    }
}
