package com.sparta.currency_user.dto;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class ExchangeUpdateRequestDto {

    @NotNull(message = "통화 아이디는 필수 입력 항목입니다.")
    private Long currencyId;

    private String status = "canceled";
}