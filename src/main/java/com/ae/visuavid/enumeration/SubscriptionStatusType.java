package com.ae.visuavid.enumeration;

public enum SubscriptionStatusType {
    ACTIVE("Active"),
    INACTIVE("InActive"),
    CANCELLED("Cancelled");

    private String name;

    SubscriptionStatusType(String name) {
        this.name = name;
    }
}
