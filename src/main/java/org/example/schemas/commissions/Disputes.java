package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.example.CommonValues.ValueTypes;
import org.example.GmaChecker;
import org.example.jsonBuilder.gma.ma.tables.TriggerJson;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@KdbTable(
        description = "tracks dispute records including submission dates, status, and notes",
        name = "disputes",
        tags = {"dispute", "customer", "order", "status"},
        type = ""
)
@Component
public class Disputes {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "date_submitted")
    private LocalDateTime dateSubmitted;

    @KdbColumn(name = "date_updated")
    private LocalDateTime dateUpdated;

    @KdbColumn(name = "dispute_notes",type = ValueTypes.TEXT)
    private String disputeNotes;

    @KdbColumn(name = "dispute_status",isNullable = false)
    private String disputeStatus;

    @KdbColumn(name = "md_account_number")
    private String mdAccountNumber;

    @KdbColumn(name = "md_carrier")
    private String mdCarrier;

    @KdbColumn(name = "md_customer_name")
    private String mdCustomerName;

    @KdbColumn(name = "md_mobile_number")
    private String mdMobileNumber;

    @KdbColumn(name = "md_order_date")
    private LocalDateTime mdOrderDate;

    @KdbColumn(name = "md_order_number")
    private String mdOrderNumber;

    @KdbColumn(name = "md_pos_status")
    private String mdPosStatus;

    @KdbColumn(name = "md_product")
    private String mdProduct;

    @KdbIndex(indexGroups = {"idx_mo_db_id_disputes"})
    @KdbColumn(name = "mo_db_id",isNullable = false)
    private String moDbId;

    @KdbColumn(name = "payment_found")
    private String paymentFound;

    @KdbColumn(name = "return_notes",type = ValueTypes.TEXT)
    private String returnNotes;

    @KdbColumn(name = "db_insert_date")
    private LocalDateTime dbInsertDate;

    @KdbColumn(name = "db_update_date")
    private LocalDateTime dbUpdateDate;


    @KdbTrigger(name = "before_insert_disputes", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public static String before_insert_disputes(){
        return """
                BEGIN
                    SET NEW.db_insert_date = NOW();
                END
                """;
    }

    @KdbTrigger(name = "before_update_disputes", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_disputes(){
        return """
                BEGIN
                    SET NEW.db_update_date = NOW();
                END
                """;
    }


}
