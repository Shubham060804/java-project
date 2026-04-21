package com.example.complaintsystem.model;

public enum Department {
    CUSTOMER_SERVICE("Customer Service"),
    BILLING("Billing"),
    TECHNICAL_SUPPORT("Technical Support"),
    QUALITY_ASSURANCE("Quality Assurance");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
