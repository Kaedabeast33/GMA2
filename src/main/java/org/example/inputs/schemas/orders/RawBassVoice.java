package org.example.inputs.schemas.orders;



import org.example.bank.Annotations.*;
import org.example.bank.commonValues.ValueTypes;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.sql.Timestamp;
import java.util.UUID;

@KdbTable(
        description = "Table storing raw BASS voice order records",
        name = "raw_bass_voice",
        tags = {"orders", "bass", "voice", "raw"},
        type = ""
)
public class RawBassVoice {

    @KdbColumn(name = "account_number")
    @CsvBindByName(column = "Account Number")
    private String accountNumber;

    @KdbIndex(indexGroups = {"order_id_idx"})
    @KdbColumn(name = "bass_order_id")
    @CsvBindByName(column = "BASS Order ID")
    private String bassOrderId;

    @KdbColumn(name = "city")
    @CsvBindByName(column = "City")
    private String city;

    @KdbIndex(indexGroups = {"create_time_idx"})
    @KdbColumn(name = "create_time")
    private Timestamp createTime;

    @KdbColumn(name = "customer_name")
    @CsvBindByName(column = "Customer Name")
    private String customerName;

    @KdbColumn(name = "due_date")
    private Timestamp dueDate;

    @KdbColumn(name = "latest_insert_date")
    private Timestamp latestInsertDate =Timestamp.from(java.time.Instant.now());

    @KdbColumn(name = "local_product_family")
    @CsvBindByName(column = "Local Product Family")
    private String localProductFamily;

    @KdbColumn(name = "local_product_name")
    @CsvBindByName(column = "Local Product Name")
    private String localProductName;

    @KdbColumn(name = "local_product_status")
    @CsvBindByName(column = "Local Product Status")
    private String localProductStatus;

    @KdbColumn(name = "local_quantity")
    @CsvBindByName(column = "Local Quantity")
    private Double localQuantity;

    @KdbColumn(name = "master_agent_id", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Master Agent ID")
    private String masterAgentId;

    @KdbColumn(name = "master_agent_name", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Master Agent Name")
    private String masterAgentName;

    @KdbColumn(name = "order_number")
    @CsvBindByName(column = "Order #")
    private String orderNumber;

    @KdbIndex(indexGroups = {"order_status_idx"})
    @KdbColumn(name = "order_status")
    @CsvBindByName(column = "Order Status")
    private String orderStatus;

    @KdbColumn(name = "partner_reference_id", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Partner Reference ID")
    private String partnerReferenceId;

    @KdbColumn(name = "sales_person_id")
    @CsvBindByName(column = "Sales Person ID")
    private String salesPersonId;

    @KdbColumn(name = "sales_person_name")
    @CsvBindByName(column = "Sales Person Name")
    private String salesPersonName;

    @KdbIndex(indexGroups = {"username_idx"})
    @KdbColumn(name = "sales_persons_username")
    @CsvBindByName(column = "Sales Person Username")
    private String salesPersonsUsername;

    @KdbColumn(name = "state")
    @CsvBindByName(column = "State")
    private String state;

    @KdbColumn(name = "status_change_date")
    private Timestamp statusChangeDate;

    @KdbColumn(name = "street_address")
    @CsvBindByName(column = "Street Address")
    private String streetAddress;

    @KdbColumn(name = "sub_agent_name")
    @CsvBindByName(column = "SubAgent Name")
    private String subAgentName;

    @KdbColumn(name = "sub_agent_username")
    @CsvBindByName(column = "SubAgent Username")
    private String subAgentUsername;

    @KdbColumn(name = "subagent_id", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Subagent ID")
    private String subAgentId;

    @KdbColumn(name = "type_of_sale")
    @CsvBindByName(column = "Type Of Sale")
    private String typeOfSale;

    @KdbColumn(name = "unit")
    @CsvBindByName(column = "Unit")
    private String unit;

    @KdbColumn(name = "wtn")
    @CsvBindByName(column = "WTN")
    private String wtn;

    @KdbColumn(name = "zip_code")
    @CsvBindByName(column = "Zip Code")
    private String zipCode;

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId = UUID.randomUUID().toString();

    @KdbColumn(name = "dsl_migrator")
    @CsvBindByName(column = "DSL Migrator")
    private String dslMigrator;


    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBassOrderId() {
        return bassOrderId;
    }

    public void setBassOrderId(String bassOrderId) {
        this.bassOrderId = bassOrderId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getLatestInsertDate() {
        return latestInsertDate;
    }

    public void setLatestInsertDate(Timestamp latestInsertDate) {
        this.latestInsertDate = latestInsertDate;
    }

    public String getLocalProductFamily() {
        return localProductFamily;
    }

    public void setLocalProductFamily(String localProductFamily) {
        this.localProductFamily = localProductFamily;
    }

    public String getLocalProductName() {
        return localProductName;
    }

    public void setLocalProductName(String localProductName) {
        this.localProductName = localProductName;
    }

    public String getLocalProductStatus() {
        return localProductStatus;
    }

    public void setLocalProductStatus(String localProductStatus) {
        this.localProductStatus = localProductStatus;
    }

    public Double getLocalQuantity() {
        return localQuantity;
    }

    public void setLocalQuantity(Double localQuantity) {
        this.localQuantity = localQuantity;
    }

    public String getMasterAgentId() {
        return masterAgentId;
    }

    public void setMasterAgentId(String masterAgentId) {
        this.masterAgentId = masterAgentId;
    }

    public String getMasterAgentName() {
        return masterAgentName;
    }

    public void setMasterAgentName(String masterAgentName) {
        this.masterAgentName = masterAgentName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPartnerReferenceId() {
        return partnerReferenceId;
    }

    public void setPartnerReferenceId(String partnerReferenceId) {
        this.partnerReferenceId = partnerReferenceId;
    }

    public String getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(String salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public String getSalesPersonsUsername() {
        return salesPersonsUsername;
    }

    public void setSalesPersonsUsername(String salesPersonsUsername) {
        this.salesPersonsUsername = salesPersonsUsername;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Timestamp statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSubAgentName() {
        return subAgentName;
    }

    public void setSubAgentName(String subAgentName) {
        this.subAgentName = subAgentName;
    }

    public String getSubAgentUsername() {
        return subAgentUsername;
    }

    public void setSubAgentUsername(String subAgentUsername) {
        this.subAgentUsername = subAgentUsername;
    }

    public String getSubAgentId() {
        return subAgentId;
    }

    public void setSubAgentId(String subAgentId) {
        this.subAgentId = subAgentId;
    }

    public String getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(String typeOfSale) {
        this.typeOfSale = typeOfSale;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWtn() {
        return wtn;
    }

    public void setWtn(String wtn) {
        this.wtn = wtn;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getDslMigrator() {
        return dslMigrator;
    }

    public void setDslMigrator(String dslMigrator) {
        this.dslMigrator = dslMigrator;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getManualEmployeeId() {
        return manualEmployeeId;
    }

    public void setManualEmployeeId(String manualEmployeeId) {
        this.manualEmployeeId = manualEmployeeId;
    }


//    public TAB_raw_bass_voice convertToEntity() throws Exception {
//        TAB_raw_bass_voice bassVoice = new TAB_raw_bass_voice();
//
//        bassVoice.getCOL_account_number().setEntityValue(getAccountNumber());
//        bassVoice.getCOL_bass_order_id().setEntityValue(getBassOrderId());
//        bassVoice.getCOL_city().setEntityValue(getCity());
//        bassVoice.getCOL_create_time().setEntityValue(getCreateTime());
//        bassVoice.getCOL_customer_name().setEntityValue(getCustomerName());
//        bassVoice.getCOL_due_date().setEntityValue(getDueDate());
//        bassVoice.getCOL_latest_insert_date().setEntityValue(getLatestInsertDate());
//        bassVoice.getCOL_local_product_family().setEntityValue(getLocalProductFamily());
//        bassVoice.getCOL_local_product_name().setEntityValue(getLocalProductName());
//        bassVoice.getCOL_local_product_status().setEntityValue(getLocalProductStatus());
//        bassVoice.getCOL_local_quantity().setEntityValue(getLocalQuantity());
//        bassVoice.getCOL_master_agent_id().setEntityValue(getMasterAgentId());
//        bassVoice.getCOL_master_agent_name().setEntityValue(getMasterAgentName());
//        bassVoice.getCOL_order_number().setEntityValue(getOrderNumber());
//        bassVoice.getCOL_order_status().setEntityValue(getOrderStatus());
//        bassVoice.getCOL_partner_reference_id().setEntityValue(getPartnerReferenceId());
//        bassVoice.getCOL_sales_person_id().setEntityValue(getSalesPersonId());
//        bassVoice.getCOL_sales_person_name().setEntityValue(getSalesPersonName());
//        bassVoice.getCOL_sales_persons_username().setEntityValue(getSalesPersonsUsername());
//        bassVoice.getCOL_state().setEntityValue(getState());
//        bassVoice.getCOL_status_change_date().setEntityValue(getStatusChangeDate());
//        bassVoice.getCOL_street_address().setEntityValue(getStreetAddress());
//        bassVoice.getCOL_sub_agent_name().setEntityValue(getSubAgentName());
//        bassVoice.getCOL_sub_agent_username().setEntityValue(getSubAgentUsername());
//        bassVoice.getCOL_subagent_id().setEntityValue(getSubAgentId());
//        bassVoice.getCOL_type_of_sale().setEntityValue(getTypeOfSale());
//        bassVoice.getCOL_unit().setEntityValue(getUnit());
//        bassVoice.getCOL_wtn().setEntityValue(getWtn());
//        bassVoice.getCOL_zip_code().setEntityValue(getZipCode());
//        bassVoice.getCOL_db_id().setEntityValue(getDbId());
//        bassVoice.getCOL_dsl_migrator().setEntityValue(getDslMigrator());
//        bassVoice.getCOL_employee_id().setEntityValue(getEmployeeId());
//        bassVoice.getCOL_manual_employee_id().setEntityValue(getManualEmployeeId());
//
//        return bassVoice;
//    }

}
