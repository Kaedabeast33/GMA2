package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@KdbTable(
        description = "Aggregated MDU deal counts per property per day",
        name = "ag_mdu_deal_property_day",
        tags = {"mdu", "deal", "property", "aggregation"},
        type = ""
)
@Component
public class AgMduDealPropertyDay {

    @KdbPrimaryKey
    @KdbColumn(name = "comp_hub_id")
    private String compHubId;

    @KdbPrimaryKey
    @KdbColumn(name = "create_date")
    private LocalDate createDate;

    @KdbColumn(name = "owner_id",isNullable = false)
    private String ownerId;

    @KdbColumn(name = "deal_count")
    private Integer dealCount;

    @KdbColumn(name = "empalign_ref_id")
    private String empalignRefId;

    @KdbColumn(name = "markalign_ref_id")
    private String markalignRefId;

    @KdbColumn(name = "regalign_ref_id")
    private String regalignRefId;

    @KdbColumn(name = "divalign_ref_id")
    private String divalignRefId;

    @KdbColumn(name = "carrier")
    private String carrier;
}
