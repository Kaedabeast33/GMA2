package org.example.jsonBuilder.gma.ma.tables;

import org.example.Annotations.KdbProcedure;

import org.example.jsonBuilder.gma.ma.tables.columns.QueryGroupDTO;
import org.example.jsonBuilder.gma.ref.RefQueryGroupJson;
import org.example.jsonBuilder.gma.ref.RefSetJson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class ProcedureJson extends BaseQueryJson {




    public ProcedureJson(KdbProcedure kdbProcedure, Method method) throws InvocationTargetException, IllegalAccessException {
        //set Method to String
        this.name = kdbProcedure.name();
        this.description = kdbProcedure.description();
        this.tags = kdbProcedure.tags();
        this.contentString = (String) method.invoke(null);
        this.id  ="pro" + UUID.randomUUID();
        //cretae queryGroup DTO
        for(String groupName: kdbProcedure.procedureGroups()){
            QueryGroupDTO groupDTO = new QueryGroupDTO();
            groupDTO.setName(groupName);
            groupDTO.setGrId("gr" + UUID.randomUUID());
            this.groups.add(groupDTO);
        }

//        if(kdbProcedure.procedureGroups().length!= kdbProcedure.procedureGroupsOrder().length){
//            throw new IllegalAccessException("Mismatch: order length ("
//                    + kdbProcedure.procedureGroupsOrder().length + ") != indexGroups length ("
//                    + kdbProcedure.procedureGroups().length + ")\n on "+kdbProcedure.name());
//        }
//        this.groups = new RefQueryGroupJson[kdbProcedure.procedureGroups().length];
//        for(int i = 0;i<procedureGroups.length; i++){
//            procedureGroups[i]= new RefQueryGroupJson(kdbProcedure.procedureGroups()[i],kdbProcedure.procedureGroupsOrder()[i]);
//        }

        ;

    }

    public ProcedureJson() {
    }
}
