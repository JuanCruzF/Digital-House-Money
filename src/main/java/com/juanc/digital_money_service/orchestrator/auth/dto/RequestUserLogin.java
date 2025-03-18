package com.juanc.digital_money_service.orchestrator.auth.dto;

public record RequestUserLogin(

        String email,
        String password
) {
}
