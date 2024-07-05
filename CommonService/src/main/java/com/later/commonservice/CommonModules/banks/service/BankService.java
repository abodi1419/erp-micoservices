package com.later.commonservice.CommonModules.banks.service;

import com.later.commonservice.CommonModules.banks.entity.Bank;
import com.later.commonservice.CommonModules.banks.repository.BankRepo;
import com.later.commonservice.Exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepo bankRepo;

    public List<Bank> findAll() {
        return bankRepo.findAll();
    }

    public Bank findById(Long id) throws ApiException {
        Bank bank = bankRepo.findById(id).orElse(null);
        if (bank == null) {
            throw new ApiException(404, "Bank not found");
        }
        return bank;
    }

    public Bank findByIdOrElseNull(Long id) {
        return bankRepo.findById(id).orElse(null);
    }
}
