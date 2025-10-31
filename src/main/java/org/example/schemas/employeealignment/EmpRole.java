package org.example.schemas.employeealignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks employee roles including business unit, hierarchy, and role names",
        name = "align_emp_role",
        tags = {"employee", "role", "business unit", "hierarchy"},
        type = ""
)
@Component
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
