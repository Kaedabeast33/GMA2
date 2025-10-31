package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing overwrite order records",
        name = "overwrite",
        tags = {"orders", "overwrite", "tracking"},
        type = ""
)
@Component
public class Overwrite {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbColumn(name = "id")
    private Long id;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "area_name")
    private String areaName;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "carrier_speed")
    private String carrierSpeed;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "commission")
    private Double commission;

    @KdbColumn(name = "customer_address_1")
    private String customerAddress1;

    @KdbColumn(name = "customer_address_2")
    private String customerAddress2;

    @KdbColumn(name = "customer_contact_number")
    private String customerContactNumber;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "dept_code")
    private String deptCode;

    @KdbColumn(name = "div_code")
    private String divCode;

    @KdbColumn(name = "division_name")
    private String divisionName;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "install_date")
    private LocalDateTime installDate;

    @KdbColumn(name = "manager_id")
    private String managerId;

    @KdbColumn(name = "manager_name")
    private String managerName;

    @KdbColumn(name = "mdu_d2d")
    private String mduD2d;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "purchase_type")
    private String purchaseType;

    @KdbColumn(name = "package_name")
    private String packageName;

    @KdbColumn(name = "product")
    private String product;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "rd_id")
    private String rdId;

    @KdbColumn(name = "region_code")
    private String regionCode;

    @KdbColumn(name = "region_name")
    private String regionName;

    @KdbColumn(name = "regional_director")
    private String regionalDirector;

    @KdbColumn(name = "rgn_code")
    private String rgnCode;

    @KdbColumn(name = "salesperson")
    private String salesperson;

    @KdbColumn(name = "speed_bucket")
    private String speedBucket;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "unit_gross")
    private Integer unitGross;

    @KdbColumn(name = "ref_login_username_id")
    private String refLoginUsernameId;

    @KdbColumn(name = "vp")
    private String vp;

    @KdbColumn(name = "vp_id")
    private String vpId;

    @KdbColumn(name = "zip_5")
    private String zip5;

    @KdbColumn(name = "latest_insert_date")
    private LocalDateTime latestInsertDate;

    @KdbColumn(name = "unit_weighted_gross")
    private Double unitWeightedGross;

    @KdbColumn(name = "order_method")
    private String orderMethod;

    @KdbColumn(name = "staged_at")
    private LocalDateTime stagedAt;

    @KdbColumn(name = "referrer_eid")
    private String referrerEid;

    @KdbColumn(name = "unit_net")
    private Integer unitNet;

    @KdbColumn(name = "unit_weighted_net")
    private Double unitWeightedNet;

    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "employee_start_date")
    private LocalDateTime employeeStartDate;

    @KdbColumn(name = "name")
    private String name;

    @KdbColumn(name = "addon")
    private String addon;

    @KdbColumn(name = "addon_count")
    private Integer addonCount;

    @KdbColumn(name = "mo_general_ref_id")
    private String moGeneralRefId;

    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;

    @KdbColumn(name = "mo_reconciliation_ref_id_3")
    private String moReconciliationRefId3;

    @KdbColumn(name = "mo_reconciliation_ref_id_4")
    private String moReconciliationRefId4;

    @KdbColumn(name = "afrom")
    private String afrom;

    @KdbColumn(name = "customer_email")
    private String customerEmail;

    @KdbColumn(name = "order_type_new_upgrade")
    private String orderTypeNewUpgrade;

    @KdbColumn(name = "username")
    private String username;

    @KdbColumn(name = "business_unit")
    private String businessUnit;
}
