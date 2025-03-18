package com.juanc.digital_money_service.business.accounts.dto;

public record ResponseGetAccountInfo(
        String cvu,
        String alias
) {
}
