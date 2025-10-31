package org.example.schemas.employeealignment;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "tracks historical records of employee sellers including roles, teams, and status changes",
        name = "align_emp_seller_history",
        tags = {"employee", "seller", "history", "role", "team"},
        type = ""
)
@Component
public class EmpSellerHistory {


    @KdbColumn(name = "db_id",unique = true)
    private String dbId;

    @KdbColumn(name = "comp_id")
    private String compId;

    @KdbColumn(name = "employee_id")
    @KdbPrimaryKey
    private String employeeId;

    @KdbColumn(name = "end_date")
    private LocalDateTime endDate;

    @KdbColumn(name = "is_active",type="BIT")
    private Boolean isActive;



    @KdbColumn(name = "role_id")
    private String roleId;

    @KdbColumn(name = "start_date")
    @KdbPrimaryKey
    private LocalDateTime startDate;

    @KdbColumn(name = "team_id")
    private String teamId;

    @KdbColumn(name = "change_type")
    private String changeType;



    @KdbTrigger(name = "prevent_overlap_seller", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public static String prevent_overlap_seller(){
        return """
                BEGIN
                    DECLARE overlap_count INT DEFAULT 0;
                   \s
                    DECLARE error_msg TEXT;
                   \s
                   \s
                
                    SELECT COUNT(*) INTO overlap_count
                    FROM align_emp_seller_history
                    WHERE employee_id = NEW.employee_id
                      AND NEW.start_date < end_date
                      AND NEW.end_date > start_date;
                     \s
                
                
                    IF overlap_count > 0 THEN
                        SET error_msg = CONCAT('Overlapping records found: ', overlap_count);
                
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = error_msg;
                    END IF;
                END""";
    }
}
