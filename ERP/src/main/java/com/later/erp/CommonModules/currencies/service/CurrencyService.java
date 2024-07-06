package com.later.erp.CommonModules.currencies.service;

import com.later.erp.CommonModules.currencies.entity.Currency;
import com.later.erp.CommonModules.currencies.repository.CurrencyRepo;
import com.later.erp.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepo currencyRepo;

    public List<Currency> findAll() {
        return currencyRepo.findAll();
    }

    public Currency findById(Long id) throws ApiException {
        Currency currency = currencyRepo.findById(id).orElse(null);
        if (currency == null || !currency.getActive()) {
            throw new ApiException(404, "Currency not found");
        }
        return currency;
    }

    public Currency findByIdOrElseNull(Long id) throws ApiException {
        return currencyRepo.findById(id).orElse(null);
    }
}
