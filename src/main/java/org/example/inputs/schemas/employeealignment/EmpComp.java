package org.example.inputs.schemas.employeealignment;

import org.example.bank.Annotations.*;

import java.time.LocalDateTime;

@KdbTable(
        description = "tracks employee compensation details including business unit, status, and dates",
        name = "align_emp_comp",
        tags = {"employee", "compensation", "business unit"},
        type = ""
)
public class EmpComp {

    @KdbColumn(name = "comp_id")
    @KdbPrimaryKey
    private String compId;

    @KdbColumn(name = "business_unit")
    private String businessUnit;

    @KdbColumn(name = "comp_name")
    private String compName;

    @KdbColumn(name = "end_date")
    private LocalDateTime endDate;

    @KdbColumn(name = "start_date")
    private LocalDateTime startDate;

    @KdbColumn(name = "status")
    private String status;
}
