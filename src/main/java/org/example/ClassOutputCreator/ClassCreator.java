package org.example.ClassOutputCreator;

import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ref.ReferenceColumnJson;
import org.example.bank.AppConfig;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.example.bank.AppConfig.getGmaName;


public class ClassCreator {
    static String workingDir = Paths.get("").toAbsolutePath().toString();



    public static void jsonToObjects(GMAJson gma) throws IOException {
        String[] safeJavaDir = safeArray(AppConfig.getJavaDir().split("\\."));

        StringBuilder outputDirBuilder = new StringBuilder(workingDir)
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("java");

        for (String segment : safeJavaDir) {
            outputDirBuilder.append(File.separator).append(segment);
        }

        outputDirBuilder.append(File.separator).append(AppConfig.getOutputDir());


        String outputDir = outputDirBuilder.toString();


        Path dir = Path.of(outputDir, getGmaName());
        if (Files.exists(dir)) {

            Files.walk(dir)
                    .sorted(Comparator.reverseOrder()) // delete children first
                    .forEach(p -> {
                        try {

                            Files.delete(p);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to delete " + p, e);
                        }
                    });
        }

        List<String> currDir = new ArrayList<>(List.of(outputDir));

        Map<String, List<String>> tableGroupsJsonMap = new TreeMap<>();
        Map<String, List<String>> groupColumnsJsonMap = new TreeMap<>();
        GMAClassCreator gmaClassCreator = new GMAClassCreator(gma, currDir);
    }

    public static String getWorkingDirectory() {
        String workingDir = System.getProperty("user.dir");
//
        return workingDir;

    }

    public static <T> List<String> safeNameList(T[] array, java.util.function.Function<T, String> mapper) {
        if (array == null) return List.of();
        return Arrays.stream(array).map(mapper).toList();
    }

    public static String[] safeArray(String[] input) {
        return input != null ? input : new String[0];
    }


    public static String toArrayLiteral(String[] values) {
        if (values.length == 0) return "new String[]{\"\"}";
        return "new String[]{" + String.join(", ", wrapWithQuotes(values)) + "}";
    }
    public static String toArrayLiteralNoQuotes(List<String> values) {
        if (values.isEmpty()) return "List.of()";
        return "List.of(" + String.join(", " ,values) + ")";
    }

    public static String[] wrapWithQuotes(String[] arr) {
        if (arr == null) return new String[]{""};
        String[] wrapped = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            wrapped[i] = "\"" + arr[i] + "\"";
        }
        return wrapped;
    }

    public static String wrapWithQuotes(String arr) {
        if (arr == null || arr.isEmpty()) return "\"\"";

        return String.format(""" 
                "%s"
                """, arr);

    }

    public static String formatReferenceColumns(ReferenceColumnJson referenceColumn) {
        return null;
    }


    public static List<String> folderCreator(List<String> currentPath, String newPath) throws IOException {
        // Normalize current path
        if (currentPath.get(0).endsWith("/")) {
            currentPath.set(0, currentPath.get(0).substring(0, currentPath.get(0).length() - 1));
        }
        currentPath.add(newPath);

        String currentPathString = getPath(currentPath);

        // Start with the base path
        Path fullPath = Paths.get(currentPathString);

        // Split the new path into folder parts


        // Create each folder in sequence
        for (String folder : currentPath) {
            fullPath = fullPath.resolve(folder);
            if (!Files.exists(fullPath)) {
                Files.createDirectory(fullPath);
            }
        }

        return currentPath;
    }

    public static String getPath(List<String> path) {
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append("/");
        }
        return sb.toString();
    }

}
