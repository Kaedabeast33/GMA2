package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@KdbTable(
        description = "Aggregated sales data per zip code per employee",
        name = "ag_sales_employee_zip",
        tags = {"sales", "zip", "aggregation"},
        type = ""
)
@Component
public class AgSalesEmployeeZip {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "month_of_year")
    private Integer monthOfYear;

    @KdbColumn(name = "week_of_year")
    private Integer weekOfYear;

    @KdbColumn(name = "cancelled")
    private BigDecimal cancelled;

    @KdbColumn(name = "pending")
    private BigDecimal pending;

    @KdbColumn(name = "installed")
    private BigDecimal installed;

    @KdbColumn(name = "unit_net")
    private BigDecimal unitNet;

    @KdbColumn(name = "unit_gross")
    private BigDecimal unitGross;

    @KdbColumn(name = "unit_weighted_net")
    private Double unitWeightedNet;

    @KdbColumn(name = "unit_weighted_gross")
    private Double unitWeightedGross;

    @KdbColumn(name = "attachment_net")
    private BigDecimal attachmentNet;

    @KdbColumn(name = "attachment_gross")
    private BigDecimal attachmentGross;

    @KdbColumn(name = "internet_net")
    private BigDecimal internetNet;

    @KdbColumn(name = "internet_gross")
    private BigDecimal internetGross;

    @KdbColumn(name = "new_internet_net")
    private BigDecimal newInternetNet;

    @KdbColumn(name = "new_internet_gross")
    private BigDecimal newInternetGross;

    @KdbColumn(name = "upgrade_net")
    private BigDecimal upgradeNet;

    @KdbColumn(name = "tv_net")
    private BigDecimal tvNet;

    @KdbColumn(name = "landline_net")
    private BigDecimal landlineNet;

    @KdbColumn(name = "mobility_net")
    private BigDecimal mobilityNet;

    @KdbColumn(name = "unit_gross_no_landline")
    private BigDecimal unitGrossNoLandline;

    @KdbColumn(name = "unit_net_no_landline")
    private BigDecimal unitNetNoLandline;
}
