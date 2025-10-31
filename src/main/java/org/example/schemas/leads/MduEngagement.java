package org.example.schemas.leads;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@KdbTable(
        description = "table storing MDU engagement activities",
        name = "mdu_engagement",
        tags = {"mdu", "engagement", "telecom"},
        type = ""
)
@Component
public class MduEngagement {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "activity_type")
    private String activityType;

    @KdbColumn(name = "activity_date")
    private LocalDateTime activityDate;

    @KdbColumn(name = "created_by")
    private String createdBy;

    @KdbColumn(name = "notes",type= ValueTypes.TEXT)
    private String notes;

    @KdbColumn(name = "outcome")
    private String outcome;

    @KdbColumn(name = "cont_hub_id")
    private String contHubId;

    @KdbColumn(name = "comp_hub_id")
    private String compHubId;

    @KdbColumn(name = "owner_id")
    private String ownerId;

    @KdbColumn(name = "hub_id",isNullable = false,unique = true)
    private String hubId;

    @KdbColumn(name = "engagement_type")
    private String engagementType;

    @KdbColumn(name = "empalign_ref_id")
    private String empalignRefId;

    @KdbColumn(name = "markalign_ref_id")
    private String markalignRefId;

    @KdbColumn(name = "regalign_ref_id")
    private String regalignRefId;

    @KdbColumn(name = "divalign_ref_id")
    private String divalignRefId;

    @KdbColumn(name = "updated_at")
    private LocalDateTime updatedAt;

    @KdbColumn(name = "created_at")
    private LocalDateTime createdAt;

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

    @KdbTrigger(name = "before_update_engagement", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public  static String before_update_engagement(){
        return """
                begin
                IF NOT (OLD.owner_id <=> NEW.owner_id)
                      \s
                       OR NOT (OLD.activity_date<=> NEW.activity_date)
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
                set new.date_db_update = now();
                end""";
    }
    @KdbTrigger(name = "before_insert_engagement", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public  static String before_insert_engagement(){
        return """
                BEGIN
                      \s
                
                
                
                       set new.date_db_insert = now();
                
                
                   END""";
    }
}
