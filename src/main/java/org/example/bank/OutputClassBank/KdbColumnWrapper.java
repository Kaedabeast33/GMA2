package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.bank.JsonBuilderRef.EntityValue;

public class KdbColumnWrapper {



    public static String safeGetValue(ColumnTemplate col) {
        System.out.println(col.getEntityValue().getValue().toString());
        System.out.println("safeGetValue called with col: " + col.getName());
        if(col == null){
            return "null";
        }
        if(col.isPrimaryKey() && col.getEntityValue().getValue() =="null"){
            return "DEFAULT";
        }

        if ( col.getEntityValue().getType() == null || col.getEntityValue().getValue() == "null") {
            System.out.println("here1");
            if (col.getDefaultValue() ==null || col.getDefaultValue()=="") {
                System.out.println("here2");
                return "null";
            }else{
                System.out.println("here3");
                return "DEFAULT";
            }
        }
        return col.getEntityValue().getValue().toString().replace("'","''");
    }



}
