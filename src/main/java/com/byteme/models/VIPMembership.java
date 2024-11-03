// VIPMembership.java
package com.byteme.models;

import java.util.Date;

public class VIPMembership {
    private String memberId;
    private String customerId;
    private Date startDate;
    private Date endDate;
    private boolean active;
    private static final double VIP_MEMBERSHIP_FEE = 99.99;

    public VIPMembership(String memberId, String customerId) {
        this.memberId = memberId;
        this.customerId = customerId;
        this.startDate = new Date();
        // Set membership end date to 1 year from now
        this.endDate = new Date(startDate.getTime() + 365L * 24 * 60 * 60 * 1000);
        this.active = true;
    }

    // Getters and setters
    public String getMemberId() { return memberId; }
    public String getCustomerId() { return customerId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public boolean isActive() { 
        return active && new Date().before(endDate); 
    }
    public void setActive(boolean active) { this.active = active; }
    public static double getVipMembershipFee() { return VIP_MEMBERSHIP_FEE; }

    @Override
    public String toString() {
        return String.format("VIP Membership - Customer: %s, Valid until: %s, Status: %s",
            customerId, endDate, isActive() ? "Active" : "Inactive");
    }
}