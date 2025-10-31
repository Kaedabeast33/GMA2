package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing master orders",
        name = "master_orders",
        tags = {"orders", "master", "tracking"},
        type = ""
)
@Component
public class MasterOrders {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbIndex(indexGroups = {"idx_master_orders_db_id"})
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbIndex(indexGroups = {"idx_order_date"})
    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbIndex(indexGroups = {"idx_employee_id"})
    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "start_date")
    private LocalDateTime startDate;

    @KdbColumn(name = "seller_name")
    private String sellerName;

    @KdbColumn(name = "business_unit")
    private String businessUnit;

    @KdbColumn(name = "referrer_eid")
    private String referrerEid;

    @KdbColumn(name = "referrer_name")
    private String referrerName;

    @KdbColumn(name = "comp_id")
    private String compId;

    @KdbColumn(name = "comp_name")
    private String compName;

    @KdbColumn(name = "role_id")
    private String roleId;

    @KdbColumn(name = "universal_role_name")
    private String universalRoleName;

    @KdbColumn(name = "ref_login_username_id")
    private String refLoginUsernameId;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "customer_contact_number")
    private String customerContactNumber;

    @KdbColumn(name = "customer_address_1")
    private String customerAddress1;

    @KdbColumn(name = "customer_address_2")
    private String customerAddress2;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "zip_5")
    private String zip5;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "install_date")
    private LocalDateTime installDate;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "purchase_type")
    private String purchaseType;

    @KdbColumn(name = "package_name")
    private String packageName;

    @KdbColumn(name = "product")
    private String product;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "carrier_speed")
    private String carrierSpeed;

    @KdbColumn(name = "speed_bucket")
    private String speedBucket;

    @KdbColumn(name = "commission")
    private Double commission;

    @KdbColumn(name = "unit_gross",isNullable = false)
    private Integer unitGross;

    @KdbColumn(name = "unit_net",isNullable = false)
    private Integer unitNet;

    @KdbColumn(name = "unit_weighted_gross",isNullable = false)
    private Double unitWeightedGross;

    @KdbColumn(name = "unit_weighted_net",isNullable = false)
    private Double unitWeightedNet;

    @KdbColumn(name = "addon_count",isNullable = false)
    private Long addonCount;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbColumn(name = "order_method")
    private String orderMethod;

    @KdbIndex(indexGroups = {"idx_ref_1"})
    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbIndex(indexGroups = {"idx_ref_2"})
    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;

    @KdbIndex(indexGroups = {"idx_ref_3"})
    @KdbColumn(name = "mo_reconciliation_ref_id_3")
    private String moReconciliationRefId3;

    @KdbIndex(indexGroups = {"idx_ref_4"})
    @KdbColumn(name = "mo_reconciliation_ref_id_4")
    private String moReconciliationRefId4;

    @KdbColumn(name = "mo_general_ref_id")
    private String moGeneralRefId;

    @KdbColumn(name = "hub_fun_id")
    private String hubFunId;

    @KdbColumn(name = "original_team_id")
    private String originalTeamId;

    @KdbColumn(name = "original_team_name")
    private String originalTeamName;

    @KdbColumn(name = "original_team_id_owner")
    private String originalTeamIdOwner;

    @KdbColumn(name = "original_team_owner_name")
    private String originalTeamOwnerName;

    @KdbColumn(name = "team_id_level_1")
    private String teamIdLevel1;

    @KdbColumn(name = "team_name_level_1")
    private String teamNameLevel1;

    @KdbColumn(name = "team_id_owner_level_1")
    private String teamIdOwnerLevel1;

    @KdbColumn(name = "owner_name_level_1")
    private String ownerNameLevel1;

    @KdbColumn(name = "role_name_level_1")
    private String roleNameLevel1;

    @KdbColumn(name = "comp_name_level_1")
    private String compNameLevel1;

    @KdbColumn(name = "team_id_level_2")
    private String teamIdLevel2;

    @KdbColumn(name = "team_name_level_2")
    private String teamNameLevel2;

    @KdbColumn(name = "team_id_owner_level_2")
    private String teamIdOwnerLevel2;

    @KdbColumn(name = "owner_name_level_2")
    private String ownerNameLevel2;

    @KdbColumn(name = "role_name_level_2")
    private String roleNameLevel2;

    @KdbColumn(name = "comp_name_level_2")
    private String compNameLevel2;

    @KdbColumn(name = "team_id_level_3")
    private String teamIdLevel3;

    @KdbColumn(name = "team_name_level_3")
    private String teamNameLevel3;

    @KdbColumn(name = "team_id_owner_level_3")
    private String teamIdOwnerLevel3;

    @KdbColumn(name = "owner_name_level_3")
    private String ownerNameLevel3;

    @KdbColumn(name = "role_name_level_3")
    private String roleNameLevel3;

    @KdbColumn(name = "comp_name_level_3")
    private String compNameLevel3;
}
