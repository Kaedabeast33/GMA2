package org.example.schemas.leads;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "table storing Vexus activity records",
        name = "vexus_activity",
        tags = {"vexus", "activity"},
        type = ""
)
@Component
public class VexusActivity {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "company_knock")
    private String companyKnock;

    @KdbIndex(indexGroups = {"idx_update"},order={2})
    @KdbColumn(name = "disposition_date",isNullable = false)
    private LocalDateTime dispositionDate;

    @KdbColumn(name = "lcp")
    private String lcp;

    @KdbColumn(name = "notes")
    private String notes;

    @KdbIndex(indexGroups = {"idx_update"},order={1})
    @KdbColumn(name = "rep")
    private String rep;

    @KdbIndex(indexGroups = {"idx_update"},order={3})
    @KdbColumn(name = "rep_email")
    private String repEmail;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "street")
    private String street;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "db_insert_date")
    private LocalDateTime dbInsertDate;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbTrigger(name = "after_vexus_update", description = "", triggerType = TriggerType.AFTER_UPDATE, triggerSet = {})
    public static String after_vexus_update(){
        return """
                BEGIN
                    DECLARE v_e_employee_id VARCHAR(100);
                    DECLARE v_s_employee_id VARCHAR(100);
                    DECLARE v_activity_owner VARCHAR(150);
                
                    -- Get employee_id from align_employee_alignment
                    select employee_id into v_e_employee_id
                	from employee_alignment.align_emp_seller e
                	where new.rep_email = e.email
                	limit 1;
                
                	select SUBSTRING_INDEX(NEW.rep_email, '.', 1) into v_s_employee_id;
                
                	SELECT e.name INTO v_activity_owner
                	FROM employee_alignment.align_emp_seller e
                	WHERE e.employee_id = COALESCE(v_e_employee_id, v_s_employee_id)
                	LIMIT 1;
                
                    -- Update master_activity table with aggregated values
                    UPDATE master_activity m
                    LEFT JOIN airtable.align_knocking_status k
                        ON k.owner = 'vexus'\s
                        AND k.system_column = 'sales rabbit'\s
                        AND NEW.status = k.result
                       \s
                    SET
                        v_db_id = IF(NEW.db_id <> OLD.db_id, NEW.db_id, v_db_id),
                        activity_date = IF(NEW.disposition_date <> OLD.disposition_date , NEW.disposition_date , activity_date),
                        changed_activity_date = IF(NEW.disposition_date  <> OLD.disposition_date , DATE(NEW.disposition_date), changed_activity_date),
                        m.employee_id = IF(
                            NEW.rep_email <> OLD.rep_email,
                            CASE
                                WHEN v_s_employee_id REGEXP '^[0-9]+$'\s
                                THEN v_s_employee_id\s
                                ELSE COALESCE(v_e_employee_id, 'error')
                            END,
                            m.employee_id
                        ),
                        activity_owner = IF(NEW.rep_email<> OLD.rep_email, v_activity_owner, activity_owner),
                        activity_owner_email = IF(NEW.rep_email <> OLD.rep_email, NEW.rep_email, activity_owner_email),
                        carrier_system = IF(NEW.carrier_system <> OLD.carrier_system, NEW.carrier_system, carrier_system),
                        db_change_date = NOW(),
                        address1 = IF(NEW.street <> OLD.street, NEW.street, address1),
                        city = IF(NEW.city <> OLD.city , NEW.city, city),
                        state = IF(NEW.state <> OLD.state, NEW.state, state),
                        zip = IF(NEW.zip <> OLD.zip, NEW.zip, zip),
                        visit_result = IF(NEW.status <> OLD.status, NEW.status, visit_result),
                        m.company_owner = IF(NEW.status <> OLD.status, k.owner, company_owner),
                        m.company_system = IF(NEW.status <> OLD.status, k.system_column, company_system),
                        m.result_translated = IF(NEW.status <> OLD.status,\s
                                                  k.result_translated, -- Aggregate with MAX()
                                                  m.result_translated),
                        m.result_group = IF(NEW.status <> OLD.status, k.result_group, m.result_group)
                    WHERE m.v_db_id = OLD.db_id;
                   \s
                    -- call alignMaRefIds;
                END
                """;
    }
    @KdbTrigger(name = "after_vexus_insert", description = "", triggerType = TriggerType.AFTER_INSERT, triggerSet = {})
    public static String after_vexus_insert(){
        return """
                BEGIN
                
                declare v_e_employee_id varchar(100);
                declare v_s_employee_id varchar(100);
                declare v_activity_owner varchar(150);
                
                select employee_id into v_e_employee_id
                from employee_alignment.align_emp_seller e
                where new.rep_email = e.email
                limit 1;
                
                select SUBSTRING_INDEX(NEW.rep_email, '.', 1) into v_s_employee_id;
                
                SELECT e.name INTO v_activity_owner
                FROM employee_alignment.align_emp_seller e
                WHERE e.employee_id = COALESCE(v_e_employee_id, v_s_employee_id)
                LIMIT 1;
                
                
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
                        NULL,\s
                        NEW.db_id,\s
                        NULL,\s
                        NULL,
                        NEW.disposition_date,\s
                        DATE(NEW.disposition_date),
                       \s
                        CASE
                            WHEN v_s_employee_id REGEXP '^[0-9]+$'\s
                            THEN v_s_employee_id
                            ELSE COALESCE(v_e_employee_id, v_s_employee_id)
                        END,
                       \s
                        v_activity_owner,
                       \s
                        NEW.rep_email,\s
                       \s
                        NEW.street,\s
                        '',\s
                        NEW.city,\s
                        NEW.state,\s
                        NEW.zip,\s
                       \s
                        0,
                       \s
                        'Visit',
                       \s
                        NEW.status,\s
                        k.owner,\s
                        k.system_column,\s
                        k.result_translated,\s
                        k.result_group,\s
                       \s
                        NEW.carrier_system,\s
                        NEW.db_insert_date
                       \s
                    FROM airtable.align_knocking_status k
                   \s
                       \s
                    WHERE k.owner = 'vexus'\s
                      AND k.system_column = 'sales rabbit'\s
                      AND NEW.status = k.result;
                     \s
                      -- call alignMaRefIds;
                END
                """;
    }
}
