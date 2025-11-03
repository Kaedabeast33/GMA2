package org.example.inputs.schemas.employeealignment;

import org.example.bank.Annotations.*;

@KdbTable(
        description = "tracks employee roles including business unit, hierarchy, and role names",
        name = "align_emp_role",
        tags = {"employee", "role", "business unit", "hierarchy"},
        type = ""
)
public class EmpRole {

    @KdbColumn(name = "role_id")
    @KdbPrimaryKey
    private String roleId;

    @KdbColumn(name = "business_unit")
    private String businessUnit;

    @KdbColumn(name = "hierarchy_level")
    private Float hierarchyLevel;

    @KdbColumn(name = "legacy_role_name")
    private String legacyRoleName;

    @KdbColumn(name = "universal_role_name")
    private String universalRoleName;
}
