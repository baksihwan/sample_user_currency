package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.CurrencyResponseDto;
import com.sparta.currency_user.dto.UserResponseDto;
import com.sparta.currency_user.entity.Exchange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    @Transactional
    public ExchangeResponseDto save(Long userId, Long currencyId, BigDecimal amountInKrw ) {
        UserResponseDto userDto = userService.findById(userId);
        CurrencyResponseDto currencyDto = currencyService.findById(currencyId);

        Long userDtoId = userDto.getId();
        Long currencyDtoId = currencyDto.getId();
        BigDecimal exchangeRate = currencyDto.getExchangeRate();

        BigDecimal amountAfterExchange = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);

        Exchange exchange = new Exchange(userDtoId, currencyDtoId, amountInKrw, amountAfterExchange);
        Exchange saved = exchangeRepository.save(exchange);

        return new ExchangeResponseDto(saved);
    }

    public List<ExchangeResponseDto> findAllByUserId(Long userId) {
        return exchangeRepository.findAllByUserIdOrderByModifiedAt(userId).stream().map(ExchangeResponseDto::toDto).toList();
    }

    public ExchangeResponseDto updateStatus(Long currencyId, String status) {
        Exchange exchange = exchangeRepository.findExchangeByCurrencyIdOrElseThrow(currencyId);
        exchange.setStatus(status);
        exchangeRepository.save(exchange);

        return new ExchangeResponseDto(exchange);
    }
}