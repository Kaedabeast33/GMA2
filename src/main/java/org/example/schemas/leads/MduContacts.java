package org.example.schemas.leads;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "table storing MDU contact information",
        name = "mdu_contacts",
        tags = {"mdu", "contacts", "telecom"},
        type = ""
)
@Component
public class MduContacts {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "contact_type")
    private String contactType;

    @KdbColumn(name = "email")
    private String email;

    @KdbColumn(name = "phone")
    private String phone;

    @KdbColumn(name = "owner_id")
    private Integer ownerId;

    @KdbColumn(name = "rewards_program_participant")
    private Boolean rewardsProgramParticipant;

    @KdbColumn(name = "property_partner_type")
    private String propertyPartnerType;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "first_name")
    private String firstName;

    @KdbColumn(name = "last_name")
    private String lastName;

    @KdbColumn(name = "job_title")
    private String jobTitle;

    @KdbColumn(name = "com_hub_id")
    private String comHubId;

    @KdbColumn(name = "hub_id",unique = true)
    private String hubId;

    @KdbColumn(name = "date_created")
    private LocalDateTime dateCreated;
}
