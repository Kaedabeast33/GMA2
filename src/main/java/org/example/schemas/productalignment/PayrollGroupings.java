package org.example.schemas.productalignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks payroll groupings and actions",
        name = "align_payroll_groupings",
        tags = {"payroll", "groupings", "actions"},
        type = ""
)
@Component
public class PayrollGroupings {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "create_time")
    private String createTime;

    @KdbColumn(name = "payroll_group")
    private String payrollGroup;

    @KdbColumn(name = "payroll_type",unique = true)
    private String payrollType;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "payroll_action")
    private String payrollAction;

    @KdbColumn(name = "sequence")
    private Integer sequence;

    @KdbColumn(name = "grouping_reference")
    private Integer groupingReference;

}
