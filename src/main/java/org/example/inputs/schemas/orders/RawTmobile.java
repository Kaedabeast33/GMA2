package org.example.inputs.schemas.orders;

//import com.chipr.DORM.schemas.dorm.orders.raw_tmobile.TAB_raw_tmobile;

import org.example.bank.Annotations.KdbColumn;
import org.example.bank.Annotations.KdbPrimaryKey;
import org.example.bank.Annotations.KdbTable;
import org.example.bank.commonValues.TableTypes;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.sql.Timestamp;
import java.util.UUID;


@KdbTable(description = "these are the orders from tmobile consisting of fiber sales", name = "raw_tmobile", type = TableTypes.RAW)
public class RawTmobile {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId = UUID.randomUUID().toString();


    @KdbColumn(name = "weekending_date")
    private Timestamp weekendingDate;

    @KdbColumn(name = "order_date")
    private Timestamp orderDate;

    @KdbColumn(name = "track_until_date")
    private Timestamp trackUntilDate;

    @KdbColumn(name = "order_type")
    @CsvBindByName(column = "Order Type")
    private String orderType;

    @KdbColumn(name = "order_number", unique = true)
    @CsvBindByName(column = "Order #")
    private String orderNumber;

    @KdbColumn(name = "rep_id")
    @CsvBindByName(column = "Rep ID")
    private String repId;

    @KdbColumn(name = "promo_value")
    @CsvBindByName(column = "promo_value")
    private String promoValue;

    @KdbColumn(name = "fiber_plan")
    @CsvBindByName(column = "Fiber Plan")
    private String fiberPlan;

    @KdbColumn(name = "mrc")
    @CsvBindByName(column = "MRC")
    private String mrc;

    @KdbColumn(name = "address_id")
    @CsvBindByName(column = "address_id")
    private String addressId;

    @KdbColumn(name = "street_address")
    @CsvBindByName(column = "Street Address")
    private String streetAddress;

    @KdbColumn(name = "unit")
    @CsvBindByName(column = "Unit")
    private String unit;

    @KdbColumn(name = "city")
    @CsvBindByName(column = "City")
    private String city;

    @KdbColumn(name = "state")
    @CsvBindByName(column = "State")
    private String state;

    @KdbColumn(name = "zip_code")
    @CsvBindByName(column = "Zip Code")
    private String zipCode;

    @KdbColumn(name = "account_status")
    @CsvBindByName(column = "Account Status")
    private String accountStatus;

    @KdbColumn(name = "est_installation_date")
    private Timestamp estInstallationDate;

    @KdbColumn(name = "order_cancellation_date")
    private Timestamp orderCancellationDate;

    @KdbColumn(name = "activation_date")
    private Timestamp activationDate;

    @KdbColumn(name = "termination_request_date")
    private Timestamp terminationRequestDate;

    @KdbColumn(name = "deactivation_date")
    private Timestamp deactivationDate;

    @KdbColumn(name = "vol_involuntary")
    @CsvBindByName(column = "Vol/Involuntary")
    private String volInvoluntary;

    @KdbColumn(name = "cancellation_reason")
    @CsvBindByName(column = "Cancellation Reason")
    private String cancellationReason;

    @KdbColumn(name = "eligibility_date")
    private Timestamp eligibilityDate;

    @KdbColumn(name = "market")
    @CsvBindByName(column = "market")
    private String market;

    @KdbColumn(name = "partner")
    @CsvBindByName(column = "partner")
    private String partner;

    @KdbColumn(name = "db_insert_date")
    private Timestamp dbInsertDate = Timestamp.from(java.time.Instant.now());


    public Timestamp getDbInsertDate() {
        return dbInsertDate;
    }

    public void setDbInsertDate(Timestamp dbInsertDate) {
        this.dbInsertDate = dbInsertDate;
    }

    public String getDbId() {
        return dbId;
    }

    // Getters and Setters


    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public Timestamp getWeekendingDate() {
        return weekendingDate;
    }

    public void setWeekendingDate(Timestamp weekendingDate) {
        this.weekendingDate = weekendingDate;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getTrackUntilDate() {
        return trackUntilDate;
    }

    public void setTrackUntilDate(Timestamp trackUntilDate) {
        this.trackUntilDate = trackUntilDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getPromoValue() {
        return promoValue;
    }

    public void setPromoValue(String promoValue) {
        this.promoValue = promoValue;
    }

    public String getFiberPlan() {
        return fiberPlan;
    }

    public void setFiberPlan(String fiberPlan) {
        this.fiberPlan = fiberPlan;
    }

    public String getMrc() {
        return mrc;
    }

    public void setMrc(String mrc) {
        this.mrc = mrc;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Timestamp getEstInstallationDate() {
        return estInstallationDate;
    }

    public void setEstInstallationDate(Timestamp estInstallationDate) {
        this.estInstallationDate = estInstallationDate;
    }

    public Timestamp getOrderCancellationDate() {
        return orderCancellationDate;
    }

    public void setOrderCancellationDate(Timestamp orderCancellationDate) {
        this.orderCancellationDate = orderCancellationDate;
    }

    public Timestamp getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Timestamp activationDate) {
        this.activationDate = activationDate;
    }

    public Timestamp getTerminationRequestDate() {
        return terminationRequestDate;
    }

    public void setTerminationRequestDate(Timestamp terminationRequestDate) {
        this.terminationRequestDate = terminationRequestDate;
    }

    public Timestamp getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Timestamp deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public String getVolInvoluntary() {
        return volInvoluntary;
    }

    public void setVolInvoluntary(String volInvoluntary) {
        this.volInvoluntary = volInvoluntary;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Timestamp getEligibilityDate() {
        return eligibilityDate;
    }

    public void setEligibilityDate(Timestamp eligibilityDate) {
        this.eligibilityDate = eligibilityDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }


}




