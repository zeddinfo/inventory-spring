package com.inventory.app.requests;

import java.util.Map;

public class TransactionRequest {
    private Map<Long, Integer> variants;
    private String reference;

    public TransactionRequest() {}

    public TransactionRequest(Map<Long, Integer> variants, String reference) {
        this.variants = variants;
        this.reference = reference;
    }

    public Map<Long, Integer> getVariants() {
        return variants;
    }

    public void setVariants(Map<Long, Integer> variants) {
        this.variants = variants;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
