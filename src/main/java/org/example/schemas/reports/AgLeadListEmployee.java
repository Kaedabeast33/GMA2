package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "Aggregated lead list data per employee",
        name = "ag_lead_list_employee",
        tags = {"lead", "employee", "aggregation"},
        type = ""
)
@Component
public class AgLeadListEmployee {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbColumn(name = "lead_owner")
    private String leadOwner;

    @KdbColumn(name = "lead_owner_email")
    private String leadOwnerEmail;

    @KdbColumn(name = "date_last_knocked")
    private LocalDateTime dateLastKnocked;

    @KdbColumn(name = "lead_count")
    private BigDecimal leadCount;

    @KdbColumn(name = "total_knocks")
    private BigDecimal totalKnocks;

    @KdbColumn(name = "total_interactions")
    private BigDecimal totalInteractions;

    @KdbColumn(name = "total_not_home")
    private BigDecimal totalNotHome;

    @KdbColumn(name = "total_not_interested")
    private BigDecimal totalNotInterested;

    @KdbColumn(name = "total_go_back")
    private BigDecimal totalGoBack;

    @KdbColumn(name = "total_sales_pin")
    private BigDecimal totalSalesPin;

    @KdbColumn(name = "total_non_workable")
    private BigDecimal totalNonWorkable;

    @KdbColumn(name = "total_new_green")
    private BigDecimal totalNewGreen;

    @KdbColumn(name = "total_carrier_assigned")
    private BigDecimal totalCarrierAssigned;

    @KdbColumn(name = "total_event_request")
    private BigDecimal totalEventRequest;

    @KdbColumn(name = "total_self_created")
    private BigDecimal totalSelfCreated;
}
