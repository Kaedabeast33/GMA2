package org.example.bank.OutputClassBank;

import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.bank.JsonBuilderRef.EntityValue;

public class KdbColumnWrapper {



    public static String safeGetValue(ColumnTemplate col) {
    String returnValue = "";

        if(col == null){
            System.out.println("safeGetValue called with null column");
            return "null";
        }
        System.out.println("safeGetValue called with col: " + col.getName());
        if(col.isPrimaryKey() && col.getEntityValue().getValue() =="null"){
            returnValue= "DEFAULT";
        }

        else if ( col.getEntityValue().getType() == null || col.getEntityValue().getValue() == "null") {
            System.out.println("here1");
            if (col.getDefaultValue() ==null || col.getDefaultValue()=="") {
                System.out.println("here2");
                returnValue= "null";
            }else{
                System.out.println("here3");
                returnValue= "DEFAULT";
            }
        }
        else{

        returnValue = col.getEntityValue().getValue()!=null? col.getEntityValue().getValue().toString().replace("'","''"):"null";

        }
        System.out.println("safeGetValue returning: " + returnValue);
        return returnValue;
    }



}
