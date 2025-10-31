package org.example.jsonBuilder.gma.ma.tables;

import org.example.Annotations.KdbQuery;
import org.example.jsonBuilder.gma.ma.tables.columns.GroupDTO;
import org.example.jsonBuilder.gma.ma.tables.columns.QueryGroupDTO;
import org.example.jsonBuilder.gma.ref.RefQueryGroupJson;
import org.example.jsonBuilder.gma.ref.RefSetJson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class QueryJson  extends BaseQueryJson{





    public QueryJson(KdbQuery kdbQuery, Method method ) throws InvocationTargetException, IllegalAccessException {
//        String[] parameters =Arrays.stream(method.getParameters()).map(p->p.getType().getSimpleName()+" "+p.getName()).toArray(String[]::new);
//        System.out.println("Method: " + (String) method.invoke(null));

        //set method to string


        this.contentString = (String) method.invoke(null);
        this.name = kdbQuery.name();
        this.description = kdbQuery.description();
        this.tags = kdbQuery.tags();
        this.queryType = kdbQuery.queryType();
        this.id = "que" + UUID.randomUUID();

        //create Query Group DTO ( to show what group it exists in)
        for(String groupName: kdbQuery.queryGroups()){
            QueryGroupDTO groupDTO = new QueryGroupDTO();
            groupDTO.setName(groupName);
            groupDTO.setGrId("gr" + UUID.randomUUID());
            this.groups.add(groupDTO);
        }

//        this.groups = new RefQueryGroupJson[kdbQuery.queryGroups().length];
//        for (int i = 0; i < groups.length; i++) {
//            groups[i] = new RefQueryGroupJson(kdbQuery.queryGroups()[i], kdbQuery.queryGroupsOrder()[i]);
//        }
//
//        this.sets = new RefSetJson[kdbQuery.querySet().length];
//        for (int i = 0; i < sets.length; i++) {
//            sets[i] = new RefSetJson(kdbQuery.querySet()[i]);
//        }








    }

    public QueryJson() {

    }




}
