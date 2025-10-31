package org.example.classbuilder.builder;

import org.example.classbuilder.creator.GMAClassCreator;
import org.example.jsonBuilder.gma.GMAJson;
import org.example.jsonBuilder.gma.ref.ReferenceColumnJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassCreator {
    static String workingDir = Paths.get("").toAbsolutePath().toString();
    public static void jsonToObjects(GMAJson gma) throws IOException {
        String outputDir = workingDir+ File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"org"+File.separator+"example"+File.separator+"output";
        Path dir = Path.of(outputDir, "dorm");
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

        List<String> currDir = new java.util.ArrayList<>(List.of(outputDir));

        Map<String, List<String>> tableGroupsJsonMap = new TreeMap<>();
        Map<String, List<String>> groupColumnsJsonMap = new TreeMap<>();
        GMAClassCreator gmaClassCreator = new GMAClassCreator(gma,currDir);
    }

    public static String getWorkingDirectory(){
        String workingDir = System.getProperty("user.dir");
//        System.out.println("Working Directory = " + workingDir);
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
    public static String[] wrapWithQuotes(String[] arr) {
        if(arr==null ) return new String[]{""};
        String[] wrapped = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            wrapped[i] = "\"" + arr[i] + "\"";
        }
        return wrapped;
    }
    public static String wrapWithQuotes(String arr) {
        if(arr==null || arr.isEmpty()) return "\"\"";

        return String.format(""" 
                "%s"
                """,arr);

    }

    public static String formatReferenceColumns(ReferenceColumnJson referenceColumn){
        return null;
    }




    public static List<String> folderCreator(List<String> currentPath, String newPath) throws IOException {
        // Normalize current path
        if (currentPath.get(0).endsWith("/")) {
            currentPath.set(0,currentPath.get(0).substring(0, currentPath.get(0).length() - 1))  ;
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

    public static String getPath(List<String> path){
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append("/");
        }
        return sb.toString();
    }

}
