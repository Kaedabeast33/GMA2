package org.example.schemas.productalignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks current product classification details for revenue and commission alignment",
        name = "align_product_classification_current",
        tags = {"product", "classification", "current", "revenue", "commission"},
        type = ""
)
@Component
public class ProductClassificationCurrent {

    @KdbColumn(name = "base_commission")
    private Double baseCommission;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "expected_revenue")
    private Double expectedRevenue;

    @KdbColumn(name = "package_name")
    @KdbPrimaryKey
    private String packageName;

    @KdbColumn(name = "product")
    private String product;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "program_id")
    @KdbPrimaryKey
    private Integer programId;

    @KdbColumn(name = "purchase_type")
    @KdbPrimaryKey
    private String purchaseType;

    @KdbColumn(name = "speed")
    private String speed;

    @KdbColumn(name = "speed_bucket")
    private String speedBucket;

    @KdbColumn(name = "system_type")
    @KdbPrimaryKey
    private String systemType;

    @KdbColumn(name = "unit_weight")
    private Double unitWeight;


    @KdbColumn(name = "db_id",isNullable = false)
    private String dbId;

    @KdbColumn(name = "record_id")
    private String recordId;

    // Getters and Setters

}
