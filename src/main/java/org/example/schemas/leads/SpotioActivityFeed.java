package org.example.schemas.leads;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "table storing Spotio activity feed records",
        name = "spotio_activity_feed",
        tags = {"spotio", "activity", "lead"},
        type = ""
)
@Component
public class SpotioActivityFeed {

    @KdbIndex(indexGroups = {"idx_activity_date_v"})
    @KdbColumn(name = "activity_date")
    private LocalDateTime activityDate;

    @KdbColumn(name = "activity_owner_email")
    private String activityOwnerEmail;

    @KdbIndex(indexGroups = {"emp_id_idx"})
    @KdbColumn(name = "activity_owner_external_userid")
    private String activityOwnerExternalUserId;

    @KdbIndex(indexGroups = {"idx_activity_type_v"})
    @KdbColumn(name = "activity_type")
    private String activityType;

    @KdbColumn(name = "address")
    private String address;

    @KdbColumn(name = "carrier_status")
    private String carrierStatus;

    @KdbColumn(name = "content")
    private String content;

    @KdbColumn(name = "direction")
    private String direction;

    @KdbColumn(name = "duration")
    private String duration;

    @KdbIndex(indexGroups = {"idx_field_name_v"})
    @KdbColumn(name = "field_name")
    private String fieldName;

    @KdbColumn(name = "first_name")
    private String firstName;

    @KdbIndex(indexGroups = {"idx_from_field_v"})
    @KdbColumn(name = "from_field")
    private String fromField;

    @KdbColumn(name = "from_stage")
    private String fromStage;

    @KdbColumn(name = "last_name")
    private String lastName;

    @KdbColumn(name = "latitude")
    private String latitude;

    @KdbColumn(name = "longitude")
    private String longitude;

    @KdbColumn(name = "new_object_owner")
    private String newObjectOwner;

    @KdbColumn(name = "notes")
    private String notes;

    @KdbColumn(name = "object")
    private String object;

    @KdbColumn(name = "object_owner_email")
    private String objectOwnerEmail;

    @KdbColumn(name = "object_owner_external_userid")
    private String objectOwnerExternalUserId;

    @KdbIndex(indexGroups = {"idx_objectid_v"})
    @KdbColumn(name = "objectid")
    private String objectId;

    @KdbColumn(name = "owner")
    private String owner;

    @KdbColumn(name = "previous_object_owner")
    private String previousObjectOwner;

    @KdbColumn(name = "report_type")
    private String reportType;

    @KdbIndex(indexGroups = {"idx_result_v"})
    @KdbColumn(name = "result")
    private String result;

    @KdbColumn(name = "territory")
    private String territory;

    @KdbColumn(name = "title")
    private String title;

    @KdbIndex(indexGroups = {"idx_to_field_v"})
    @KdbColumn(name = "to_field")
    private String toField;

    @KdbColumn(name = "to_stage")
    private String toStage;

    @KdbColumn(name = "visit_count")
    private String visitCount;

    @KdbColumn(name = "visit_verification_feet")
    private String visitVerificationFeet;

    @KdbColumn(name = "visit_verification_meter")
    private String visitVerificationMeter;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;



    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbTrigger(name = "after_spotio_insert", description = "", triggerType = TriggerType.AFTER_INSERT, triggerSet = {})
    public static String after_spotio_insert(){
        return """
                BEGIN
                if new.activity_type = 'visit' then
                    INSERT INTO master_activity (
                        cs_db_id, v_db_id, csr_db_id, f_db_id,
                        activity_date,changed_activity_date, employee_id, activity_owner, activity_owner_email,\s
                        address1, address2, city, state, zip,\s
                        visit_verification_feet,
                        activity_type,
                        visit_result, company_owner, company_system, result_translated, result_group,\s
                        carrier_system, db_change_date
                    )
                    SELECT\s
                        NEW.db_id, NULL, NULL, NULL,
                       \s
                        NEW.activity_date,
                        date(new.activity_date),
                         NEW.activity_owner_external_userid,\s
                        (SELECT MAX(e.name) FROM airtable.align_employee_alignment e WHERE e.employee_id = NEW.activity_owner_external_userid),
                        NEW.activity_owner_email,\s
                       \s
                		SUBSTRING_INDEX(NEW.address, ',', 1),\s
                        '',\s
                        SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.address, ',', 2), ',', -1),\s
                        regexp_substr(SUBSTRING_INDEX(SUBSTRING_INDEX(new.address, ',', 3), ',', -1),'[a-zA-Z]+'),\s
                        regexp_substr(SUBSTRING_INDEX(SUBSTRING_INDEX(new.address, ',', 3), ',', -1),'[0-9]{5}'),\s
                       \s
                        new.visit_verification_feet,
                        new.activity_type,
                        NEW.result, k.owner, k.system_column, k.result_translated, k.result_group,\s
                        NEW.carrier_system, NEW.date_db_changed
                    FROM airtable.align_knocking_status k
                    WHERE k.owner = 'chipr' AND k.system_column = 'spotio' AND NEW.result = k.result and new.activity_type = 'visit';
                end if;
                
                -- call alignMaRefIds;
                END
                """;
    }
    @KdbTrigger(name = "after_spotio_update", description = "", triggerType = TriggerType.AFTER_UPDATE, triggerSet = {})
    public static String after_spotio_update(){
        return """
                BEGIN
                    UPDATE master_activity m
                    LEFT JOIN airtable.align_knocking_status k
                        ON k.owner = 'chipr'\s
                        AND k.system_column = 'spotio'\s
                        AND NEW.result = k.result
                    SET\s
                        cs_db_id = IF(NEW.db_id <> OLD.db_id, NEW.db_id, cs_db_id),
                        activity_date = IF(NEW.activity_date <> OLD.activity_date, NEW.activity_date, activity_date),
                        changed_activity_date = IF(NEW.activity_date <> OLD.activity_date, DATE(NEW.activity_date), changed_activity_date),
                        employee_id = IF(NEW.activity_owner_external_userid <> OLD.activity_owner_external_userid, NEW.activity_owner_external_userid, employee_id),
                        activity_owner = IF(NEW.activity_owner_external_userid <> OLD.activity_owner_external_userid,\s
                            COALESCE((SELECT MAX(e.name) FROM airtable.align_employee_alignment e WHERE e.employee_id = NEW.activity_owner_external_userid), activity_owner),\s
                            activity_owner),
                        activity_owner_email = IF(NEW.activity_owner_email <> OLD.activity_owner_email, NEW.activity_owner_email, activity_owner_email),
                        carrier_system = IF(NEW.carrier_system <> OLD.carrier_system, NEW.carrier_system, carrier_system),
                        db_change_date = NOW(),
                        address1 = IF(NEW.address <> OLD.address, SUBSTRING_INDEX(NEW.address, ',', 1), address1),
                        city = IF(NEW.address <> OLD.address, SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.address, ',', 2), ',', -1), city),
                        state = IF(NEW.address <> OLD.address, regexp_substr(SUBSTRING_INDEX(SUBSTRING_INDEX(new.address, ',', 3), ',', -1),'[a-zA-Z]+'), state),
                        zip = IF(NEW.address <> OLD.address, regexp_substr(SUBSTRING_INDEX(SUBSTRING_INDEX(new.address, ',', 3), ',', -1),'[0-9]{5}'), zip),
                        visit_verification_feet = IF(NEW.visit_verification_feet <> OLD.visit_verification_feet, NEW.visit_verification_feet, visit_verification_feet),
                        activity_type = IF(NEW.activity_type <> OLD.activity_type, NEW.activity_type, activity_type),
                        visit_result = IF(NEW.result <> OLD.result, NEW.result, visit_result),
                        m.company_owner = IF(NEW.result <> OLD.result, k.owner, company_owner),
                        m.company_system = IF(NEW.result <> OLD.result, k.system_column, company_system),
                        m.result_translated = IF(NEW.result <> OLD.result, k.result_translated, m.result_translated),
                        m.result_group = IF(NEW.result <> OLD.result, k.result_group, m.result_group)
                    WHERE m.cs_db_id = OLD.db_id and new.activity_type = 'visit';
                   \s
                    -- call alignMaRefIds;
                END
                """;
    }

}
