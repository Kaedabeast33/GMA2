package org.example.schemas.commissions;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "Tracks matching info between MO and MP records",
        name = "mo_mp_match",
        tags = {"mo", "mp", "match"},
        type = ""
)
@Component
public class MoMpMatch {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbIndex(indexGroups = {"idx_mo_mp_db_id"},order = {2})
    @KdbColumn(name = "mo_db_id")
    private String moDbId;

    @KdbIndex(indexGroups = {"idx_mo_mp_db_id"},order = {1})
    @KdbColumn(name = "mp_db_id")
    private String mpDbId;

    @KdbColumn(name = "mp_pay_type")
    private String mpPayType;


    @KdbColumn(name = "min_id", type = "DECIMAL(21,0)",isNullable = false)
    private BigDecimal minId;

    @KdbColumn(name = "max_id", type = "DECIMAL(21,0)",isNullable = false)
    private BigDecimal maxId;

    @KdbColumn(name = "s_min_id", type = "DECIMAL(21,0)",isNullable = false)
    private BigDecimal sMinId;

    @KdbColumn(name = "s_max_id", type = "DECIMAL(21,0)",isNullable = false)
    private BigDecimal sMaxId;

    @KdbColumn(name = "mp_amount", type = "DECIMAL(10,2)")
    private BigDecimal mpAmount;

    @KdbColumn(name = "mp_statement_date")
    private LocalDateTime mpStatementDate;

    @KdbColumn(name = "mp_posted_date")
    private LocalDateTime mpPostedDate;

    @KdbColumn(name = "mp_churn_date")
    private LocalDateTime mpChurnDate;

    @KdbColumn(name = "mp_order_number")
    private String mpOrderNumber;

    @KdbColumn(name = "mp_account_number")
    private String mpAccountNumber;

    @KdbColumn(name = "mp_mobile_number")
    private String mpMobileNumber;

    @KdbColumn(name = "mp_product_type")
    private String mpProductType;

    @KdbColumn(name = "mp_carrier_system")
    private String mpCarrierSystem;

    @KdbColumn(name = "mp_pay_product")
    private String mpPayProduct;

    @KdbColumn(name = "mp_component")
    private String mpComponent;

    @KdbColumn(name = "mp_carrier")
    private String mpCarrier;

    @KdbColumn(name = "pay_type")
    private String payType;

    @KdbColumn(name = "mp_ref_id")
    private String mpRefId;

    @KdbColumn(name = "mo_ref_id")
    private String moRefId;

    @KdbColumn(name = "mp_ref_id_2")
    private String mpRefId2;

    @KdbColumn(name = "mo_ref_id_2")
    private String moRefId2;

    @KdbColumn(name = "mp_ref_id_3")
    private String mpRefId3;

    @KdbColumn(name = "mo_ref_id_3")
    private String moRefId3;

    @KdbColumn(name = "mp_ref_id_4")
    private String mpRefId4;

    @KdbColumn(name = "mo_ref_id_4")
    private String moRefId4;

    @KdbColumn(name = "mo_carrier_system")
    private String moCarrierSystem;

    @KdbColumn(name = "mo_employee_id")
    private String moEmployeeId;

    @KdbColumn(name = "mo_salesperson")
    private String moSalesPerson;

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

    @KdbColumn(name = "mo_customer_name")
    private String moCustomerName;

    @KdbColumn(name = "mo_package_name")
    private String moPackageName;

    @KdbColumn(name = "mo_product_type")
    private String moProductType;

    @KdbColumn(name = "mo_speed_bucket")
    private String moSpeedBucket;

    @KdbColumn(name = "mo_order_date")
    private LocalDateTime moOrderDate;

    @KdbColumn(name = "mo_install_date")
    private LocalDateTime moInstallDate;

    @KdbColumn(name = "mo_carrier")
    private String moCarrier;

    @KdbColumn(name = "mo_order_number")
    private String moOrderNumber;

    @KdbColumn(name = "mo_account_number")
    private String moAccountNumber;

    @KdbColumn(name = "mo_mobile_number")
    private String moMobileNumber;

    @KdbColumn(name = "mo_purchase_type")
    private String moPurchaseType;

    @KdbColumn(name = "mo_status")
    private String moStatus;
}
