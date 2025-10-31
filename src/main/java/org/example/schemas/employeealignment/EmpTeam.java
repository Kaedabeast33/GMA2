package org.example.schemas.employeealignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks employee teams including business unit, level, and status",
        name = "align_emp_team",
        tags = {"employee", "team", "business unit", "status"},
        type = ""
)
@Component
public class EmpTeam {

    @KdbColumn(name = "team_id")
    @KdbPrimaryKey
    private String teamId;

    @KdbColumn(name = "business_unit")
    private String businessUnit;

    @KdbColumn(name = "team_level")
    private String teamLevel;

    @KdbColumn(name = "team_name")
    private String teamName;

    @KdbColumn(name = "status")
    private String status;
}
