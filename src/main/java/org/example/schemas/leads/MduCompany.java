package org.example.schemas.leads;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@KdbTable(
        description = "table storing MDU company information",
        name = "mdu_company",
        tags = {"mdu", "company", "telecom"},
        type = ""
)
@Component
public class MduCompany {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "name")
    private String name;

    @KdbColumn(name = "type")
    private String type;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "postal_code")
    private String postalCode;

    @KdbColumn(name = "living_units")
    private String livingUnits;

    @KdbColumn(name = "building_type")
    private String buildingType;

    @KdbColumn(name = "onsite_leasing_office")
    private Boolean onsiteLeasingOffice;

    @KdbColumn(name = "agreement_type")
    private String agreementType;

    @KdbIndex(indexGroups = {"idx_mdu_company_owner_id"})
    @KdbColumn(name = "owner_id")
    private Integer ownerId;

    @KdbColumn(name = "property_tier")
    private String propertyTier;

    @KdbColumn(name = "property_origin")
    private String propertyOrigin;

    @KdbColumn(name = "property_status")
    private String propertyStatus;

    @KdbColumn(name = "partner_property_id")
    private String partnerPropertyId;

    @KdbIndex(indexGroups = {"idx_mdu_company_hub_id"})
    @KdbColumn(name = "hub_id",unique = true)
    private String hubId;

    @KdbColumn(name = "sub_building_type")
    private String subBuildingType;

    @KdbColumn(name = "chipr_territory")
    private String chiprTerritory;

    @KdbColumn(name = "penetration_rate")
    private BigDecimal penetrationRate;

    @KdbColumn(name = "monthly_fiber_target")
    private Integer monthlyFiberTarget;

    @KdbColumn(name = "monthly_visit_target")
    private Integer monthlyVisitTarget;

    @KdbColumn(name = "translated_status")
    private String translatedStatus;

    @KdbColumn(name = "priority_level")
    private String priorityLevel;
}
