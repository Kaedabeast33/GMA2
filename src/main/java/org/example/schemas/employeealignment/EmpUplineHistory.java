package org.example.schemas.employeealignment;

import org.example.Annotations.*;
import org.example.CommonValues.QueryType;
import org.springframework.stereotype.Component;

@KdbTableRef(

        name = "align_emp_upline_history"

)
@Component
public class EmpUplineHistory {

//    @KdbQuery(name = "getQuery", description = "getEmpUplineHistory", queryType = QueryType.SELECT)
//    public  static String getQuery(){
//        return "SELECT * FROM align_emp_upline_history";
//    };
}
