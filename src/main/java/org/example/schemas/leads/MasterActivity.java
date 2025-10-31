package org.example.schemas.leads;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@KdbTable(
        description = "table storing master activity records",
        name = "master_activity",
        tags = {"activity", "tracking", "master"},
        type = ""
)
@Component
public class MasterActivity {

    @KdbColumn(name = "m_id")
    @KdbPrimaryKey
    private Integer mId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "cs_db_id",unique = true)
    private String csDbId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "v_db_id",unique = true)
    private String vDbId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "csr_db_id",unique = true)
    private String csrDbId;

    @KdbIndex(indexGroups = {"idx_date"})
    @KdbColumn(name = "activity_date")
    private LocalDateTime activityDate;

    @KdbIndex(indexGroups = {"idx_master_activity_employee_id"})
    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "activity_owner")
    private String activityOwner;

    @KdbColumn(name = "activity_owner_email")
    private String activityOwnerEmail;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbIndex(indexGroups = {"idx_db_date"})
    @KdbColumn(name = "db_change_date")
    private LocalDateTime dbChangeDate;

    @KdbColumn(name = "address1")
    private String address1;

    @KdbColumn(name = "address2")
    private String address2;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbIndex(indexGroups = {"zip_idx"})
    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "visit_result")
    private String visitResult;

    @KdbColumn(name = "company_owner")
    private String companyOwner;

    @KdbColumn(name = "company_system")
    private String companySystem;

    @KdbColumn(name = "result_translated")
    private String resultTranslated;

    @KdbColumn(name = "result_group")
    private String resultGroup;

    @KdbColumn(name = "activity_type")
    private String activityType;

    @KdbColumn(name = "visit_verification_feet")
    private Integer visitVerificationFeet;

    @KdbColumn(name = "changed_activity_date")
    private LocalDate changedActivityDate;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "f_db_id",unique = true)
    private String fDbId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id")
    private String sellerRefId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id")
    private String uplineRefId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id_level_1")
    private String sellerRefIdLevel1;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id_level_1")
    private String uplineRefIdLevel1;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id_level_2")
    private String sellerRefIdLevel2;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id_level_2")
    private String uplineRefIdLevel2;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id_level_3")
    private String sellerRefIdLevel3;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id_level_3")
    private String uplineRefIdLevel3;

    @KdbTrigger(name = "before_update_activity", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_activity(){
        return """
                BEGIN
                   \s
                    IF NOT (OLD.employee_id <=> NEW.employee_id)
                       OR NOT (OLD.activity_date <=> NEW.activity_date)
                       OR (NEW.seller_ref_id IS NULL AND OLD.seller_ref_id IS NOT NULL)
                       OR (NEW.upline_ref_id IS NULL AND OLD.upline_ref_id IS NOT NULL)
                       OR (NEW.seller_ref_id_level_1 IS NULL AND OLD.seller_ref_id_level_1 IS NOT NULL)
                       OR (NEW.upline_ref_id_level_1 IS NULL AND OLD.upline_ref_id_level_1 IS NOT NULL)
                       OR (NEW.seller_ref_id_level_2 IS NULL AND OLD.seller_ref_id_level_2 IS NOT NULL)
                       OR (NEW.upline_ref_id_level_2 IS NULL AND OLD.upline_ref_id_level_2 IS NOT NULL)
                       OR (NEW.seller_ref_id_level_3 IS NULL AND OLD.seller_ref_id_level_3 IS NOT NULL)
                       OR (NEW.upline_ref_id_level_3 IS NULL AND OLD.upline_ref_id_level_3 IS NOT NULL)\s
                    THEN
                        -- Clear all alignment fields
                        SET NEW.seller_ref_id = NULL;
                        SET NEW.upline_ref_id = NULL;
                        SET NEW.seller_ref_id_level_1 = NULL;
                        SET NEW.upline_ref_id_level_1 = NULL;
                        SET NEW.seller_ref_id_level_2 = NULL;
                        SET NEW.upline_ref_id_level_2 = NULL;
                        SET NEW.seller_ref_id_level_3 = NULL;
                        SET NEW.upline_ref_id_level_3 = NULL;
                    END IF;
                END""";
    }
}
