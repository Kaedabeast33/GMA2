package org.example.schemas.leads;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@KdbTable(
        description = "table tracking frontier activities",
        name = "frontier_activity",
        tags = {"frontier", "activity", "tracking"},
        type = ""
)
@Component
public class FrontierActivity {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "activity_date")
    private LocalDateTime activityDate;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "department")
    private String department;

    @KdbColumn(name = "disp_date_central")
    private LocalDateTime dispDateCentral;

    @KdbColumn(name = "disp_time")
    private LocalTime dispTime;

    @KdbIndex(indexGroups = {"upsert_idx"},order={1})
    @KdbColumn(name = "eid")
    private String eid;

    @KdbColumn(name = "full_name")
    private String fullName;

    @KdbIndex(indexGroups = {"upsert_idx"},order={2})
    @KdbColumn(name = "lead_disposition_id")
    private String leadDispositionId;

    @KdbColumn(name = "lead_id")
    private String leadId;

    @KdbColumn(name = "office")
    private String office;

    @KdbColumn(name = "postal_code")
    private String postalCode;

    @KdbColumn(name = "region")
    private String region;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbTrigger(name = "trg_after_insert_frontier_activity", description = "", triggerType = TriggerType.AFTER_INSERT, triggerSet = {})
    public static String trg_after_insert_frontier_activity(){
        return """
                BEGIN
                    INSERT INTO master_activity (
                        f_db_id,
                        employee_id,
                        activity_date,
                        changed_activity_date,
                        activity_owner,
                        visit_result,
                        result_translated,
                        result_group,
                       \s
                       \s
                       \s
                        zip,
                        city,
                        state,
                        db_change_date,
                        carrier_system
                       \s
                      \s
                    )
                    SELECT\s
                        NEW.db_id,
                        NEW.eid,
                        NEW.activity_date,
                        DATE(NEW.activity_date),
                        NEW.full_name,
                        NEW.status,
                        k.result_translated,
                        k.result_group,
                        REGEXP_SUBSTR(NEW.postal_code, '^[0-9]{5}', 1),
                        NEW.city,
                        NEW.state,
                        NEW.date_db_changed,
                        'frontier'
                    FROM airtable.align_knocking_status k
                    WHERE k.owner = 'frontier' AND k.result = NEW.status
                    LIMIT 1;
                
                
                    -- call alignMaRefIds;
                   \s
                END""";
    }

    @KdbTrigger(name = "after_update_frontier_activity", description = "", triggerType = TriggerType.AFTER_UPDATE, triggerSet = {})
    public static String after_update_frontier_activity(){
        return """
                BEGIN
                   	declare s_visit_result varchar(50);
                   	declare s_result_group varchar(50);
                
                   	select k.result_translated,k.result_group into s_visit_result,s_result_group
                   	FROM airtable.align_knocking_status k
                   		WHERE k.owner = 'frontier' AND k.result = NEW.status
                   		LIMIT 1;
                
                
                
                   	update master_activity ma
                   	set
                   		ma.employee_id = IF (NEW.eid <> old.eid,new.eid,old.eid),
                   		ma.activity_date=IF (NEW.activity_date<> old.activity_date,new.activity_date,old.activity_date),
                   		ma.changed_activity_date = IF (NEW.activity_date<> old.activity_date,date(new.activity_date),date(old.activity_date)),
                   		ma.activity_owner= IF (NEW.full_name<> old.full_name,new.full_name,old.full_name),
                   		ma.visit_result= IF (NEW.status<> old.status,new.status,old.status),
                   		ma.result_translated= IF (NEW.status<> old.status,s_visit_result,ma.result_translated),
                   		ma.result_group= IF (NEW.status<> old.status,s_result_group,ma.result_group),
                
                   		ma.zip = IF (REGEXP_SUBSTR(NEW.postal_code, '^[0-9]{5}', 1)<> REGEXP_SUBSTR(OLD.postal_code, '^[0-9]{5}', 1),REGEXP_SUBSTR(NEW.postal_code, '^[0-9]{5}', 1) ,REGEXP_SUBSTR(OLD.postal_code, '^[0-9]{5}', 1) ),
                   		ma.city= IF (NEW.city<> old.city,new.city,old.city),
                   		ma.db_change_date = if(new.date_db_changed <> old.date_db_changed,new.date_db_changed ,old.date_db_changed ),
                   		ma.carrier_system= 'frontier'
                   	WHERE ma.f_db_id = NEW.db_id
                   	;
                
                   	-- call alignMaRefIds;
                      \s
                   END""";
    }
}
