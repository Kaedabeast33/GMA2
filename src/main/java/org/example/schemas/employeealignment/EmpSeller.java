package org.example.schemas.employeealignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "tracks employee seller information including hire, rehire, termination dates, and CRM details",
        name = "align_emp_seller",
        tags = {"employee", "seller", "hire", "termination", "CRM"},
        type = ""
)
@Component
public class EmpSeller {

    @KdbColumn(name = "employee_id")
    @KdbPrimaryKey
    private String employeeId;

    @KdbColumn(name = "hire_date")
    private LocalDateTime hireDate;

    @KdbColumn(name = "referrer_eid")
    @KdbIndex(indexGroups = {"idx_seller_referrer"})
    private String referrerEid;

    @KdbColumn(name= "crm_id")
    @KdbIndex(indexGroups = {"idx_align_emp_seller_crm_id"})
    private String crmId;

    @KdbColumn(name = "name")
    private String name;

    @KdbColumn(name = "rehire_date")
    private LocalDateTime rehireDate;

    @KdbColumn(name = "termination_date")
    private LocalDateTime terminationDate;

    @KdbColumn(name = "create_date")
    private LocalDateTime createDate;



    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "email")
    private String email;
}
