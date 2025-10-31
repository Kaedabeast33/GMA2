package org.example.schemas.leads;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "table storing lead list feed data",
        name = "lead_list_feed",
        tags = {"lead", "feed", "tracking"},
        type = ""
)
@Component
public class LeadListFeed {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "address_house_street_name")
    private String addressHouseStreetName;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "columns")
    private String columns;

    @KdbColumn(name = "country")
    private String country;

    @KdbColumn(name = "created_by")
    private String createdBy;

    @KdbIndex(indexGroups = {"creation_date_idx"})
    @KdbColumn(name = "creation_date")
    private LocalDateTime creationDate;

    @KdbColumn(name = "date_changed_db")
    private LocalDateTime dateChangedDb;

    @KdbColumn(name = "days_since_last_activity")
    private Integer daysSinceLastActivity;

    @KdbColumn(name = "days_since_last_visit")
    private Integer daysSinceLastVisit;

    @KdbColumn(name = "external_lead_id")
    private String externalLeadId;

    @KdbColumn(name = "field_assisted_call")
    private String fieldAssistedCall;

    @KdbColumn(name = "field_cid_1")
    private String fieldCid1;

    @KdbColumn(name = "field_carrier_status")
    private String fieldCarrierStatus;

    @KdbColumn(name = "field_cid_2")
    private String fieldCid2;

    @KdbColumn(name = "field_cid_3")
    private String fieldCid3;

    @KdbColumn(name = "field_current_speed")
    private String fieldCurrentSpeed;

    @KdbColumn(name = "field_email")
    private String fieldEmail;

    @KdbColumn(name = "field_event_code")
    private String fieldEventCode;

    @KdbColumn(name = "field_existing_video")
    private String fieldExistingVideo;

    @KdbColumn(name = "field_fiber_green")
    private String fieldFiberGreen;

    @KdbColumn(name = "field_first_name")
    private String fieldFirstName;

    @KdbColumn(name = "field_green_code")
    private String fieldGreenCode;

    @KdbColumn(name = "field_install_type")
    private String fieldInstallType;

    @KdbColumn(name = "field_job_no")
    private String fieldJobNo;

    @KdbColumn(name = "field_last_name")
    private String fieldLastName;

    @KdbColumn(name = "field_lead_type")
    private String fieldLeadType;

    @KdbColumn(name = "field_loss_reasons_lost")
    private String fieldLossReasonsLost;

    @KdbColumn(name = "field_loss_reasons_non_workable")
    private String fieldLossReasonsNonWorkable;

    @KdbColumn(name = "field_loss_reasons_refresh")
    private String fieldLossReasonsRefresh;

    @KdbColumn(name = "field_loss_reasons_reset")
    private String fieldLossReasonsReset;

    @KdbColumn(name = "field_phone")
    private String fieldPhone;

    @KdbColumn(name = "field_pref_lang")
    private String fieldPrefLang;

    @KdbColumn(name = "field_products_sold")
    private String fieldProductsSold;

    @KdbColumn(name = "fieldqqid")
    private String fieldQqid;

    @KdbColumn(name = "field_refresh_code")
    private String fieldRefreshCode;

    @KdbColumn(name = "field_sales_rabbit_status")
    private String fieldSalesRabbitStatus;


    @KdbColumn(name = "field_siro_recording",type = ValueTypes.TEXT)
    private String fieldSiroRecording;

    @KdbColumn(name = "field_turn_up_date")
    private LocalDateTime fieldTurnUpDate;

    @KdbColumn(name = "field_turn_up_year")
    private String fieldTurnUpYear;

    @KdbColumn(name = "field_unit")
    private String fieldUnit;

    @KdbColumn(name = "field_wireless_opportunity")
    private String fieldWirelessOpportunity;

    @KdbColumn(name = "house_number")
    private String houseNumber;

    @KdbColumn(name = "last_activity_done_owner")
    private String lastActivityDoneOwner;

    @KdbColumn(name = "last_location_status")
    private String lastLocationStatus;

    @KdbColumn(name = "last_visit_date")
    private LocalDateTime lastVisitDate;

    @KdbColumn(name = "last_visit_result")
    private String lastVisitResult;

    @KdbColumn(name = "latitude")
    private Double latitude;

    @KdbColumn(name = "lead_id")
    private String leadId;

    @KdbColumn(name = "lead_owner")
    private String leadOwner;

    @KdbColumn(name = "lead_owner_email")
    private String leadOwnerEmail;

    @KdbColumn(name = "living_unit_id")
    private String livingUnitId;

    @KdbColumn(name = "longitude")
    private Double longitude;

    @KdbColumn(name = "number_of_visit")
    private Integer numberOfVisit;

    @KdbColumn(name = "object")
    private String object;

    @KdbColumn(name = "object_type_parent_child")
    private String objectTypeParentChild;

    @KdbColumn(name = "related_to")
    private String relatedTo;

    @KdbColumn(name = "source")
    private String source;

    @KdbColumn(name = "stage")
    private String stage;

    @KdbColumn(name = "stage_updated_at")
    private LocalDateTime stageUpdatedAt;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "street")
    private String street;

    @KdbColumn(name = "territory")
    private String territory;

    @KdbColumn(name = "updated_by")
    private String updatedBy;

    @KdbColumn(name = "updated_date")
    private LocalDateTime updatedDate;

    @KdbColumn(name = "visit_count")
    private String visitCount;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "field_property_id")
    private String fieldPropertyId;

    @KdbColumn(name = "field_reference_id")
    private String fieldReferenceId;
}
