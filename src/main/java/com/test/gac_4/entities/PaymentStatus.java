package com.test.gac_4.entities;

public enum PaymentStatus {
    PENDING("En attente"),
    CONFIRMED("Confirmé"),
    FAILED("Échoué"),
    REFUNDED("Remboursé");

    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
