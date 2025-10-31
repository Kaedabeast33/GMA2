package org.example.schemas.productalignment;


import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;

@KdbTable(description = "classifies addon characteristics for revenue alignment", name = "addon_classification", tags = {"airtable", "product alignment", "addon classification"}, type = "")
public class AddonClassification {



    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;



    @KdbColumn(name = "addon")
    private String addon;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "unit_weight")
    private String unitWeight;

    @KdbColumn(name = "base_revenue")
    private Double baseRevenue;




}
