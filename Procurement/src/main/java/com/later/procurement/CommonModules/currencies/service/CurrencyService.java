package com.later.procurement.CommonModules.currencies.service;


import com.later.procurement.CommonModules.currencies.entity.Currency;
import com.later.procurement.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    public List<Currency> findAll() {
        return List.of();
    }

    public Currency findById(Long id) throws ApiException {
        return null;
    }

    public Currency findByIdOrElseNull(Long id) throws ApiException {
        return null;
    }
}
