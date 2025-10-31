package org.example.schemas.leads;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@KdbTable(
        description = "table storing MDU deal information",
        name = "mdu_deals",
        tags = {"mdu", "deals", "telecom"},
        type = ""
)
@Component
public class MduDeals {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "amount")
    private String amount;

    @KdbColumn(name = "close_date")
    private LocalDateTime closeDate;

    @KdbColumn(name = "deal_name")
    private String dealName;

    @KdbColumn(name = "deal_stage")
    private String dealStage;

    @KdbColumn(name = "hs_last_modified_date")
    private LocalDateTime hsLastModifiedDate;

    @KdbColumn(name = "hub_id",isNullable = false,unique = true)
    private String hubId;

    @KdbColumn(name = "num_associated_contacts")
    private Integer numAssociatedContacts;

    @KdbColumn(name = "owner_id",isNullable = false)
    private String ownerId;

    @KdbColumn(name = "pipeline")
    private String pipeline;

    @KdbColumn(name = "comp_hub_id")
    private String compHubId;

    @KdbColumn(name = "create_date")
    private LocalDateTime createDate;

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

    @KdbColumn(name = "date_db_insert")
    private LocalDateTime dateDbInsert;

    @KdbColumn(name = "date_db_update")
    private LocalDateTime dateDbUpdate;

    @KdbTrigger(name = "before_update_deals", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_deals(){
        return """
                begin
                IF NOT (OLD.owner_id <=> NEW.owner_id)
                      \s
                       OR NOT (OLD.create_date <=> NEW.create_date)
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
                   \s
                end
                """;
    }
    @KdbTrigger(name = "after_update_deals", description = "", triggerType = TriggerType.AFTER_UPDATE, triggerSet = {})
    public static String after_update_deals(){
        return """
                begin
                   	set new.date_db_update = now();
                      \s
                   end
                """;
    }
    @KdbTrigger(name = "before_insert", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public static String before_insert(){
        return """
                begin
                set
                	new.date_db_insert = now();
                end
                """;
    }
}
