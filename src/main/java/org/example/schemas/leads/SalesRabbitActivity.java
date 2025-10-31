package org.example.schemas.leads;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.Annotations.KdbTrigger;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.sql.Timestamp;

@KdbTable(
        description = "table storing Sales Rabbit activity records",
        name = "sales_rabbit_activity",
        tags = {"sales", "activity", "lead"},
        type = ""
)
@Component
public class SalesRabbitActivity {

    @KdbColumn(name = "lead_owner")
    private String leadOwner;

    @KdbColumn(name = "lead_owner_email")
    private String leadOwnerEmail;

    @KdbColumn(name = "cms_id")
    private String cmsId;

    @KdbColumn(name = "org_unit")
    private String orgUnit;

    @KdbColumn(name = "business_name")
    private String businessName;

    @KdbColumn(name = "first_name")
    private String firstName;

    @KdbColumn(name = "last_name")
    private String lastName;

    @KdbColumn(name = "address1")
    private String address1;

    @KdbColumn(name = "address2")
    private String address2;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "latitude")
    private Double latitude;

    @KdbColumn(name = "longitude")
    private Double longitude;

    @KdbColumn(name = "primary_phone")
    private String primaryPhone;

    @KdbColumn(name = "alternate_phone")
    private String alternatePhone;

    @KdbColumn(name = "email")
    private String email;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "appointment")
    private String appointment;

    @KdbColumn(name = "note")
    private String note;

    @KdbColumn(name = "date_created")
    private Timestamp dateCreated;

    @KdbColumn(name = "date_modified")
    private Timestamp dateModified;

    @KdbColumn(name = "date_status_modified")
    private Timestamp dateStatusModified;

    @KdbColumn(name = "new_hire_shadowing")
    private Integer newHireShadowing;

    @KdbColumn(name = "door_hanger")
    private Integer doorHanger;

    @KdbColumn(name = "full_name")
    private String fullName;

    @KdbColumn(name = "current_internet_speed")
    private String currentInternetSpeed;

    @KdbColumn(name = "wireless_opportunity")
    private String wirelessOpportunity;

    @KdbColumn(name = "existing_video")
    private String existingVideo;

    @KdbColumn(name = "pref_lang")
    private String prefLang;

    @KdbColumn(name = "job_no")
    private String jobNo;

    @KdbColumn(name = "fiber_green")
    private String fiberGreen;

    @KdbColumn(name = "lead_type")
    private String leadType;

    @KdbColumn(name = "dpt_code")
    private String dptCode;

    @KdbColumn(name = "organization")
    private String organization;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "campaign_code")
    private String campaignCode;

    @KdbColumn(name = "event_code")
    private String eventCode;

    @KdbColumn(name = "green_code")
    private String greenCode;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbTrigger(name = "after_salesrabbit_insert", description = "", triggerType = TriggerType.AFTER_INSERT, triggerSet = {})
    public static String after_salesrabbit_insert(){
        return """
                BEGIN
                    INSERT INTO master_activity (
                        cs_db_id, v_db_id, csr_db_id, f_db_id,
                        activity_date, changed_activity_date, employee_id, activity_owner, activity_owner_email,\s
                        address1, address2, city, state, zip,\s
                        visit_verification_feet,
                        activity_type,
                        visit_result, company_owner, company_system, result_translated, result_group,\s
                        carrier_system, db_change_date
                    )
                    SELECT\s
                        NULL, NULL, NEW.db_id, null,
                        NEW.date_status_modified,
                        date(new.date_status_modified),
                        NEW.employee_id,\s
                        (SELECT MAX(e.name) FROM airtable.align_employee_alignment e WHERE e.employee_id = NEW.employee_id),
                        NEW.lead_owner_email,\s
                        NEW.address1, NEW.address2, NEW.city, NEW.state, NEW.zip,
                        0,
                        'Visit',
                        NEW.status, k.owner, k.system_column, k.result_translated, k.result_group,\s
                        NEW.carrier_system, NEW.date_db_changed
                    FROM airtable.align_knocking_status k
                    WHERE k.owner = 'chipr' AND k.system_column = 'sales rabbit' AND NEW.status = k.result;
                   \s
                    -- call alignMaRefIds;
                END""";
    }
}
