package com.example.Games.user.balance.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WithdrawRequest(
        @NotNull @DecimalMin("0.01")
        BigDecimal amount
) {}
