package org.example.inputs.schemas.orders;

import org.example.bank.Annotations.KdbColumn;
import org.example.bank.Annotations.KdbPrimaryKey;
import org.example.bank.Annotations.KdbTable;
import org.example.bank.commonValues.TableTypes;


@KdbTable(description = "these are the orders from tmobile consisting of fiber sales", name = "raw_tmobile", type = TableTypes.RAW)
public class RawTmobile {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "weekending_date")
    private String weekendingDate;

    @KdbColumn(name = "order_date")
    private String orderDate;

    @KdbColumn(name = "track_until_date")
    private String trackUntilDate;

    @KdbColumn(name = "order_type")
    private String orderType;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "rep_id")
    private String repId;

    @KdbColumn(name = "promo_value")
    private String promoValue;

    @KdbColumn(name = "fiber_plan")
    private String fiberPlan;

    @KdbColumn(name = "mrc")
    private String mrc;

    @KdbColumn(name = "address_id")
    private String addressId;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "unit")
    private String unit;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "zip_code")
    private String zipCode;

    @KdbColumn(name = "account_status")
    private String accountStatus;

    @KdbColumn(name = "est_installation_date")
    private String estInstallationDate;

    @KdbColumn(name = "order_cancellation_date")
    private String orderCancellationDate;

    @KdbColumn(name = "activation_date")
    private String activationDate;

    @KdbColumn(name = "termination_request_date")
    private String terminationRequestDate;

    @KdbColumn(name = "deactivation_date")
    private String deactivationDate;

    @KdbColumn(name = "vol_involuntary")
    private String volInvoluntary;

    @KdbColumn(name = "cancellation_reason")
    private String cancellationReason;

    @KdbColumn(name = "eligibility_date")
    private String eligibilityDate;

    @KdbColumn(name = "market")
    private String market;

    @KdbColumn(name = "partner")
    private String partner;

    // Getters and Setters
    public String getWeekendingDate() { return weekendingDate; }
    public void setWeekendingDate(String weekendingDate) { this.weekendingDate = weekendingDate; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getTrackUntilDate() { return trackUntilDate; }
    public void setTrackUntilDate(String trackUntilDate) { this.trackUntilDate = trackUntilDate; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getRepId() { return repId; }
    public void setRepId(String repId) { this.repId = repId; }

    public String getPromoValue() { return promoValue; }
    public void setPromoValue(String promoValue) { this.promoValue = promoValue; }

    public String getFiberPlan() { return fiberPlan; }
    public void setFiberPlan(String fiberPlan) { this.fiberPlan = fiberPlan; }

    public String getMrc() { return mrc; }
    public void setMrc(String mrc) { this.mrc = mrc; }

    public String getAddressId() { return addressId; }
    public void setAddressId(String addressId) { this.addressId = addressId; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }

    public String getEstInstallationDate() { return estInstallationDate; }
    public void setEstInstallationDate(String estInstallationDate) { this.estInstallationDate = estInstallationDate; }

    public String getOrderCancellationDate() { return orderCancellationDate; }
    public void setOrderCancellationDate(String orderCancellationDate) { this.orderCancellationDate = orderCancellationDate; }

    public String getActivationDate() { return activationDate; }
    public void setActivationDate(String activationDate) { this.activationDate = activationDate; }

    public String getTerminationRequestDate() { return terminationRequestDate; }
    public void setTerminationRequestDate(String terminationRequestDate) { this.terminationRequestDate = terminationRequestDate; }

    public String getDeactivationDate() { return deactivationDate; }
    public void setDeactivationDate(String deactivationDate) { this.deactivationDate = deactivationDate; }

    public String getVolInvoluntary() { return volInvoluntary; }
    public void setVolInvoluntary(String volInvoluntary) { this.volInvoluntary = volInvoluntary; }

    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }

    public String getEligibilityDate() { return eligibilityDate; }
    public void setEligibilityDate(String eligibilityDate) { this.eligibilityDate = eligibilityDate; }

    public String getMarket() { return market; }
    public void setMarket(String market) { this.market = market; }

    public String getPartner() { return partner; }
    public void setPartner(String partner) { this.partner = partner; }
}



