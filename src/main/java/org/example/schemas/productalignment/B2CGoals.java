package org.example.schemas.productalignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks B2C goals and targets for departments",
        name = "align_b2c_goals",
        tags = {"B2C", "goals", "targets"},
        type = ""
)
@Component
public class B2CGoals {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "dpt_code")
    private String dptCode;

    @KdbColumn(name = "head_count_target")
    private String headCountTarget;

    @KdbColumn(name = "month_start")
    private String monthStart;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "weighted_unit_target")
    private String weightedUnitTarget;

    @KdbColumn(name = "addon")
    private String addon;

    @KdbColumn(name = "base_revenue")
    private Double baseRevenue;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "unit_weight")
    private String unitWeight;


}
