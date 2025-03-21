package com.juanc.digital_money_service.business.accounts.cards.dto;

import java.time.LocalDate;

public record ResponseGetCard(
        String cardHolder,
        String cardNumber,
        String cvv,
        LocalDate expirationDate
) {
}
