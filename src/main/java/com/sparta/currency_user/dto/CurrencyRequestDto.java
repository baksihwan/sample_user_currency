package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull(message = "환율은 필수 입력 항목입니다.")
    private BigDecimal exchangeRate;

    @NotBlank(message = "통화 이름은 필수 입력 항목입니다.")
    private String currencyName;

    @NotBlank(message = "표시는 필수 입력 항목입니다.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.exchangeRate,
                this.currencyName,
                this.symbol
        );
    }
}