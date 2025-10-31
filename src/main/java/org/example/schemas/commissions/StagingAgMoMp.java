package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "staging table for MO/MP aggregated payment data",
        name = "staging_ag_mo_mp",
        tags = {"staging", "mo", "mp", "payment"},
        type = ""
)
@Component
public class StagingAgMoMp {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbIndex(indexGroups = {"idx_mp_db_id"})
    @KdbColumn(name = "mo_db_id")
    private String moDbId;

    @KdbColumn(name = "mp_total_amount")
    private BigDecimal mpTotalAmount;

    @KdbColumn(name = "mp_pay_product")
    private String mpPayProduct;

    @KdbColumn(name = "mp_max_statement_date")
    private LocalDateTime mpMaxStatementDate;

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

    @KdbColumn(name = "mp_posted_date")
    private LocalDateTime mpPostedDate;

    @KdbColumn(name = "mp_churn_date")
    private LocalDateTime mpChurnDate;

    @KdbColumn(name = "mp_component")
    private String mpComponent;

    @KdbColumn(name = "mp_carrier")
    private String mpCarrier;

    @KdbColumn(name = "pay_type")
    private String payType;

    @KdbColumn(name = "mo_employee_id")
    private String moEmployeeId;

    @KdbColumn(name = "mo_salesperson")
    private String moSalesperson;

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

    @KdbColumn(name = "mp_base_payment_db_id")
    private String mpBasePaymentDbId;

    @KdbColumn(name = "mp_base_payment_amount")
    private BigDecimal mpBasePaymentAmount;

    @KdbColumn(name = "mp_base_payment_posted_date")
    private LocalDateTime mpBasePaymentPostedDate;

    @KdbColumn(name = "mp_base_payment_statement_date")
    private LocalDateTime mpBasePaymentStatementDate;

    @KdbColumn(name = "mp_base_chargeback_db_id")
    private String mpBaseChargebackDbId;

    @KdbColumn(name = "mp_base_chargeback_amount")
    private BigDecimal mpBaseChargebackAmount;

    @KdbColumn(name = "mp_base_chargeback_churn_date")
    private LocalDateTime mpBaseChargebackChurnDate;

    @KdbColumn(name = "mp_base_chargeback_statement_date")
    private LocalDateTime mpBaseChargebackStatementDate;

    @KdbColumn(name = "mp_aux_payment_db_id")
    private String mpAuxPaymentDbId;

    @KdbColumn(name = "mp_aux_payment_amount")
    private BigDecimal mpAuxPaymentAmount;

    @KdbColumn(name = "mp_aux_payment_posted_date")
    private LocalDateTime mpAuxPaymentPostedDate;

    @KdbColumn(name = "mp_aux_payment_statement_date")
    private LocalDateTime mpAuxPaymentStatementDate;

    @KdbColumn(name = "mp_aux_chargeback_db_id")
    private String mpAuxChargebackDbId;

    @KdbColumn(name = "mp_aux_chargeback_amount")
    private BigDecimal mpAuxChargebackAmount;

    @KdbColumn(name = "mp_aux_chargeback_churn_date")
    private LocalDateTime mpAuxChargebackChurnDate;

    @KdbColumn(name = "mp_aux_chargeback_statement_date")
    private LocalDateTime mpAuxChargebackStatementDate;
}
