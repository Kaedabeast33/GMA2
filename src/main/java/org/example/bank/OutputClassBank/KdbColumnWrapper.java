package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.AirColumnTemplate;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.bank.JsonBuilderRef.EntityValue;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class KdbColumnWrapper {


    public static String safeGetValue(ColumnTemplate col) {
        String returnValue = "";

        if (col == null) {
//            System.out.println("safeGetValue called with null column");
            return "null";
        }


//        System.out.println("safeGetValue called with col: " + col.getName());
        if (col.isPrimaryKey() && col.getEntityValue().getValue() == "null") {
            returnValue = "DEFAULT";
        } else if (col.isEmbedding()) {
            Object value = col.getEntityValue().getValue();

            if (value == null) {
                returnValue = "null";
            } else {
                Double[] arr = null;
                try {
                    if (value instanceof Double[]) {
                        arr = (Double[]) value;
                    } else if (value instanceof double[]) {
                        double[] primitive = (double[]) value;
                        arr = Arrays.stream(primitive).boxed().toArray(Double[]::new);
                    } else if (value instanceof java.util.Collection<?>) {
                        java.util.Collection<?> coll = (java.util.Collection<?>) value;
                        arr = coll.stream()
                                .map(o -> (o instanceof Number) ? ((Number) o).doubleValue() : null)
                                .toArray(Double[]::new);
                    } else if (value instanceof Number) {
                        arr = new Double[]{((Number) value).doubleValue()};
                    } else {
//                        System.err.println("[WARN] Embedding value is not an array/collection/number: " + value.getClass().getName());
                    }

                    if (arr == null) {
                        returnValue = "null";
                    } else {
                        String jsonArray = Arrays.stream(arr)
                                .map(d -> d == null ? "null" : d.toString())
                                .collect(Collectors.joining(",", "[", "]"));
                        returnValue = jsonArray.replace("'", "''");
                    }
                } catch (Exception e) {
                    System.err.println("[WARN] Failed to serialize embedding value to vector JSON: " + e.getMessage());
                    returnValue = "null";
                }
            }
        }



        else if ( col.getEntityValue().getType() == null || col.getEntityValue().getValue() == "null") {
//            System.out.println("here1");
            if (col.getDefaultValue() ==null || col.getDefaultValue()=="") {
//                System.out.println("here2");
                returnValue= "null";
            }else{
//                System.out.println("here3");
                returnValue= "DEFAULT";
            }
        }
        else{

            returnValue = col.getEntityValue().getValue()!=null? col.getEntityValue().getValue().toString().replace("'","''"):"null";

        }
//        System.out.println("safeGetValue returning: " + returnValue);
        if(!col.isEmbedding()){
            returnValue = "'" + returnValue + "'";
        }
        else{
            returnValue = "STRING_TO_VECTOR('" + returnValue + "')";
        }
        return returnValue;
    }

    public static String safeGetValue(AirColumnTemplate col) {
        String returnValue = "";

        if (col == null) {
//            System.out.println("safeGetValue called with null column");
            return "null";
        }


//        System.out.println("safeGetValue called with col: " + col.getName());




        else if ( col.getEntityValue().getType() == null || col.getEntityValue().getValue() == "null") {
//            System.out.println("here1");
            if (col.getDefaultValue() ==null || Objects.equals(col.getDefaultValue(), "")) {
//                System.out.println("here2");
                returnValue= "null";
            }else{
//                System.out.println("here3");
                returnValue= "null";
            }
        }
        else{

            returnValue = col.getEntityValue().getValue()!=null? col.getEntityValue().getValue().toString().replace("'","''"):"null";

        }
//        System.out.println("safeGetValue returning: " + returnValue);

        return returnValue;
    }



}
