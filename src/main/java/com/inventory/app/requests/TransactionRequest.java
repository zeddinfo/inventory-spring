package com.inventory.app.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public class TransactionRequest {
    @Schema(
            description = "Map of variant IDs to quantity to purchase. Key = variantId, Value = quantity",
            example = "{ \"1\": 2, \"3\": 1, \"5\": 4 }"
    )
    private Map<Long, Integer> variants;

    @Schema(
            description = "Reference / invoice code, optional, auto-generated if not provided",
            example = "ORDER-20251120-093012-123"
    )
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
