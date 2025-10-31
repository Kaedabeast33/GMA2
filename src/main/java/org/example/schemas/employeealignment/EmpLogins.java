package org.example.schemas.employeealignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks employee login information including system and username",
        name = "align_emp_logins",
        tags = {"employee", "logins", "system", "username"},
        type = ""
)
@Component
public class EmpLogins {


    @KdbColumn(name = "db_id",isNullable = false)
    private String dbId;

    @KdbColumn(name = "created_time")
    private String createdTime;

    @KdbColumn(name = "employee_id")
    @KdbPrimaryKey
    private String employeeId;

    @KdbColumn(name = "employee_name")
    private String employeeName;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "system_column")
    @KdbPrimaryKey
    private String systemColumn;

    @KdbColumn(name = "username")
    @KdbPrimaryKey
    private String username;
}
