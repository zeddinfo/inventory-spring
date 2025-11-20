package com.inventory.app.controllers;

import com.inventory.app.exceptions.ResponseUtil;
import com.inventory.app.models.Variant;
import com.inventory.app.requests.TransactionRequest;
import com.inventory.app.services.HistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public class TransactionController {
    private final HistoryService historyService;

    public TransactionController(HistoryService transactionService) {
        this.historyService = transactionService;
    }

    @PostMapping
    public Map<String, Object> processTransaction(@RequestBody TransactionRequest req) {
        try {
            List<Variant> updatedVariants = historyService.processTransaction(req.getVariants(), req.getReference());
            return ResponseUtil.Ok(updatedVariants);
        } catch (RuntimeException e) {
            return ResponseUtil.error(e.getMessage(), null);
        }
    }
}
