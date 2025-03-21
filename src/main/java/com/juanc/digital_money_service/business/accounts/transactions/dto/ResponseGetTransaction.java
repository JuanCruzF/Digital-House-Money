package com.juanc.digital_money_service.business.accounts.transactions.dto;

import com.juanc.digital_money_service.business.accounts.transactions.TransactionDirection;
import com.juanc.digital_money_service.business.accounts.transactions.TransactionType;

import java.time.LocalDateTime;

public record ResponseGetTransaction(
        Double amount,
        TransactionType transactionType,
        TransactionDirection direction,
        String senderName,
        String senderCvu,
        String receiverName,
        String receiverCvu,
        LocalDateTime transactionDate
) {
}
