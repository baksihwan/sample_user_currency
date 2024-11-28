package com.sparta.currency_user.dto;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Getter
public class ExchangeSaveRequestDto {

    @NotNull(message = "사용자 아이디는 필수 입력 항목입니다.")
    private Long userId;

    @NotNull(message = "통화 아이디는 필수 입력 항목입니다.")
    private Long currencyId;

    @NotNull
    private BigDecimal amountInKrw;
}