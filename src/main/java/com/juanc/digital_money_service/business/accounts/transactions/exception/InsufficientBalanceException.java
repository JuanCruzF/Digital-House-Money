package com.juanc.digital_money_service.business.accounts.transactions.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
