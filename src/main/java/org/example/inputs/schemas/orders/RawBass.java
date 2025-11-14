package org.example.inputs.schemas.orders;



import org.example.bank.Annotations.*;
import org.example.bank.commonValues.ValueTypes;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.sql.Timestamp;

@KdbTable(
        description = "Table storing raw BASS order records",
        name = "raw_bass",
        tags = {"orders", "bass", "raw"},
        type = ""
)
public class RawBass {

    @KdbColumn(name = "account_number")
    @CsvBindByName(column = "Account Number")
    private String accountNumber;

    @KdbIndex(indexGroups = {"order_id_idx"})
    @KdbColumn(name = "bass_order_id")
    @CsvBindByName(column = "BASS Order ID")
    private String bassOrderId;

    @KdbColumn(name = "billing_number")
    @CsvBindByName(column = "Billing Number")
    private String billingNumber;

    @KdbColumn(name = "city")
    @CsvBindByName(column = "City")
    private String city;

    @KdbIndex(indexGroups = {"create_time_idx"})
    @KdbColumn(name = "create_time")
//    @CsvCustomBindByName(column = "Create Time", converter = MultiFormatDateConverter.class)
    private Timestamp createTime;

    @KdbColumn(name = "customer_name")
    @CsvBindByName(column = "Customer Name")
    private String customerName;

    @KdbColumn(name = "customer_type", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Customer Type")
    private String customerType;

    @KdbColumn(name = "due_date")
//    @CsvCustomBindByName(column = "Due Date", converter = MultiFormatDateConverter.class)
    private Timestamp dueDate;

    @KdbColumn(name = "internet_action_code")
    @CsvBindByName(column = "Internet Action Code")
    private String internetActionCode;

    @KdbColumn(name = "internet_product_family")
    @CsvBindByName(column = "Internet Product Family")
    private String internetProductFamily;

    @KdbColumn(name = "internet_product_name")
    @CsvBindByName(column = "Internet Product Name")
    private String internetProductName;

    @KdbColumn(name = "internet_product_status")
    @CsvBindByName(column = "Internet Product Status")
    private String internetProductStatus;

    @KdbColumn(name = "internet_quantity")
    @CsvBindByName(column = "Internet Quantity")
    private String internetQuantity;

    @KdbColumn(name = "internet_usoc", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Internet USOC")
    private String internetUsoc;

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

    @KdbColumn(name = "order_type")
    @CsvBindByName(column = "Order Type")
    private String orderType;

    @KdbColumn(name = "outside_sales_rep")
    @CsvBindByName(column = "Outside Sales Rep")
    private String outsideSalesRep;

    @KdbColumn(name = "partner_reference_id", type = ValueTypes.TEXT)
    @CsvBindByName(column = "Partner Reference ID")
    private String partnerReferenceId;

    @KdbColumn(name = "regional_sales_manager")
    @CsvBindByName(column = "Regional Sales Manager")
    private String regionalSalesManager;

    @KdbColumn(name = "sales_code")
    @CsvBindByName(column = "Sales Code")
    private String salesCode;

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
//    @CsvCustomBindByName(column = "Status Change Date", converter = MultiFormatDateConverter.class)
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

    @KdbColumn(name = "latest_insert_date")
    private Timestamp latestInsertDate =Timestamp.from(java.time.Instant.now());

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId = java.util.UUID.randomUUID().toString();

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

    public String getBillingNumber() {
        return billingNumber;
    }

    public void setBillingNumber(String billingNumber) {
        this.billingNumber = billingNumber;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getInternetActionCode() {
        return internetActionCode;
    }

    public void setInternetActionCode(String internetActionCode) {
        this.internetActionCode = internetActionCode;
    }

    public String getInternetProductFamily() {
        return internetProductFamily;
    }

    public void setInternetProductFamily(String internetProductFamily) {
        this.internetProductFamily = internetProductFamily;
    }

    public String getInternetProductName() {
        return internetProductName;
    }

    public void setInternetProductName(String internetProductName) {
        this.internetProductName = internetProductName;
    }

    public String getInternetProductStatus() {
        return internetProductStatus;
    }

    public void setInternetProductStatus(String internetProductStatus) {
        this.internetProductStatus = internetProductStatus;
    }

    public String getInternetQuantity() {
        return internetQuantity;
    }

    public void setInternetQuantity(String internetQuantity) {
        this.internetQuantity = internetQuantity;
    }

    public String getInternetUsoc() {
        return internetUsoc;
    }

    public void setInternetUsoc(String internetUsoc) {
        this.internetUsoc = internetUsoc;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOutsideSalesRep() {
        return outsideSalesRep;
    }

    public void setOutsideSalesRep(String outsideSalesRep) {
        this.outsideSalesRep = outsideSalesRep;
    }

    public String getPartnerReferenceId() {
        return partnerReferenceId;
    }

    public void setPartnerReferenceId(String partnerReferenceId) {
        this.partnerReferenceId = partnerReferenceId;
    }

    public String getRegionalSalesManager() {
        return regionalSalesManager;
    }

    public void setRegionalSalesManager(String regionalSalesManager) {
        this.regionalSalesManager = regionalSalesManager;
    }

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
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

    public Timestamp getLatestInsertDate() {
        return latestInsertDate;
    }

    public void setLatestInsertDate(Timestamp latestInsertDate) {
        this.latestInsertDate = latestInsertDate;
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


//    public TAB_raw_bass convertToEntity() throws Exception {
//        TAB_raw_bass bass = new TAB_raw_bass();
//
//        bass.getCOL_account_number().setEntityValue(getAccountNumber());
//        bass.getCOL_bass_order_id().setEntityValue(getBassOrderId());
//        bass.getCOL_billing_number().setEntityValue(getBillingNumber());
//        bass.getCOL_city().setEntityValue(getCity());
//        bass.getCOL_create_time().setEntityValue(getCreateTime());
//        bass.getCOL_customer_name().setEntityValue(getCustomerName());
//        bass.getCOL_customer_type().setEntityValue(getCustomerType());
//        bass.getCOL_due_date().setEntityValue(getDueDate());
//        bass.getCOL_internet_action_code().setEntityValue(getInternetActionCode());
//        bass.getCOL_internet_product_family().setEntityValue(getInternetProductFamily());
//        bass.getCOL_internet_product_name().setEntityValue(getInternetProductName());
//        bass.getCOL_internet_product_status().setEntityValue(getInternetProductStatus());
//        bass.getCOL_internet_quantity().setEntityValue(getInternetQuantity());
//        bass.getCOL_internet_usoc().setEntityValue(getInternetUsoc());
//        bass.getCOL_master_agent_id().setEntityValue(getMasterAgentId());
//        bass.getCOL_master_agent_name().setEntityValue(getMasterAgentName());
//        bass.getCOL_order_number().setEntityValue(getOrderNumber());
//        bass.getCOL_order_status().setEntityValue(getOrderStatus());
//        bass.getCOL_order_type().setEntityValue(getOrderType());
//        bass.getCOL_outside_sales_rep().setEntityValue(getOutsideSalesRep());
//        bass.getCOL_partner_reference_id().setEntityValue(getPartnerReferenceId());
//        bass.getCOL_regional_sales_manager().setEntityValue(getRegionalSalesManager());
//        bass.getCOL_sales_code().setEntityValue(getSalesCode());
//        bass.getCOL_sales_person_id().setEntityValue(getSalesPersonId());
//        bass.getCOL_sales_person_name().setEntityValue(getSalesPersonName());
//        bass.getCOL_sales_persons_username().setEntityValue(getSalesPersonsUsername());
//        bass.getCOL_state().setEntityValue(getState());
//        bass.getCOL_status_change_date().setEntityValue(getStatusChangeDate());
//        bass.getCOL_street_address().setEntityValue(getStreetAddress());
//        bass.getCOL_sub_agent_name().setEntityValue(getSubAgentName());
//        bass.getCOL_sub_agent_username().setEntityValue(getSubAgentUsername());
//        bass.getCOL_subagent_id().setEntityValue(getSubAgentId());
//        bass.getCOL_type_of_sale().setEntityValue(getTypeOfSale());
//        bass.getCOL_unit().setEntityValue(getUnit());
//        bass.getCOL_wtn().setEntityValue(getWtn());
//        bass.getCOL_zip_code().setEntityValue(getZipCode());
//        bass.getCOL_latest_insert_date().setEntityValue(getLatestInsertDate());
//        bass.getCOL_db_id().setEntityValue(getDbId());
//        bass.getCOL_dsl_migrator().setEntityValue(getDslMigrator());
//
//        return bass;
//    }

}
