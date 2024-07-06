package com.later.erp.App.suppliers.supplier.service;

import com.later.erp.App.suppliers.supplier.entity.Supplier;
import com.later.erp.App.suppliers.supplier.entity.SupplierBankAccount;
import com.later.erp.App.suppliers.supplier.model.validation.SupplierBankAccountCreationModel;
import com.later.erp.App.suppliers.supplier.repository.SupplierBankAccountRepo;
import com.later.erp.App.suppliers.supplier.repository.SupplierRepo;
import com.later.erp.CommonModules.banks.entity.Bank;
import com.later.erp.CommonModules.banks.service.BankService;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.Status;
import com.later.erp.util.IBANValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierBankAccountService {
    private final SupplierRepo supplierRepo;
    private final SupplierBankAccountRepo supplierBankAccountRepo;
    private final BankService bankService;

    public String create(SupplierBankAccountCreationModel supplierBankAccountCreationModel, Long supplierId) throws ApiException {
        SupplierBankAccount supplierBankAccount = validateCreationModel(supplierBankAccountCreationModel,
                null, supplierId);
        supplierBankAccountRepo.save(supplierBankAccount);
        return "Created successfully";
    }

    public String update(SupplierBankAccountCreationModel supplierBankAccountCreationModel, Long id) throws ApiException {
        SupplierBankAccount oldSupplierBankAccount = supplierBankAccountRepo.findById(id).orElse(null);
        if (oldSupplierBankAccount == null) {
            throw new ApiException(404, "Supplier bank account not found");
        }
        SupplierBankAccount supplierBankAccount = validateCreationModel(supplierBankAccountCreationModel,
                oldSupplierBankAccount, oldSupplierBankAccount.getSupplier().getId());
        supplierBankAccountRepo.save(supplierBankAccount);
        return "Updated successfully";
    }

    private SupplierBankAccount validateCreationModel(SupplierBankAccountCreationModel supplierBankAccountCreationModel, SupplierBankAccount oldSupplierBankAccount, Long supplierId) throws ApiException {
        SupplierBankAccount supplierBankAccount = new SupplierBankAccount();
        if (oldSupplierBankAccount == null) {
            Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
            if (supplier == null) {
                throw new ApiException(404, "Supplier not found");
            }
            supplierBankAccount.setSupplier(supplier);
            supplierBankAccount.setPreferred(
                    supplierBankAccountCreationModel.getPreferred() != null && supplierBankAccountCreationModel.getPreferred() ?
                            supplierBankAccountRepo.getCurrentPreferred() : (short) 0);
        } else {
            supplierBankAccount.setSupplier(oldSupplierBankAccount.getSupplier());
            supplierBankAccount.setId(oldSupplierBankAccount.getId());
            supplierBankAccount.setPreferred(
                    supplierBankAccountCreationModel.getPreferred() != null && supplierBankAccountCreationModel.getPreferred() ?
                            oldSupplierBankAccount.getPreferred() : (short) 0);
        }
        if (!IBANValidator.isValidIBANNumber(supplierBankAccountCreationModel.getAccountIBAN())) {
            throw new ApiException(400, "Invalid IBAN");
        }

        Bank bank = bankService.findById(supplierBankAccountCreationModel.getBankId());
        supplierBankAccount.setBankId(bank.getId());
        supplierBankAccount.setBankName(bank.getName());
        supplierBankAccount.setBankNameAr(bank.getNameAr());
        supplierBankAccount.setLocalBank(bank.getLocal());

        supplierBankAccount.setAccountNo(supplierBankAccountCreationModel.getAccountNo());
        supplierBankAccount.setAccountIBAN(supplierBankAccountCreationModel.getAccountIBAN());
        supplierBankAccount.setAccountName(supplierBankAccountCreationModel.getAccountName());
        supplierBankAccount.setAccountNameAr(supplierBankAccountCreationModel.getAccountNameAr());

        supplierBankAccount.setBankName(bank.getName());
        supplierBankAccount.setBankNameAr(bank.getNameAr());
        supplierBankAccount.setLocal(bank.getLocal());

        return supplierBankAccount;

    }

    public String delete(Long id) throws ApiException {
        SupplierBankAccount oldSupplierBankAccount = supplierBankAccountRepo.findById(id).orElse(null);
        if (oldSupplierBankAccount == null) {
            throw new ApiException(404, "Supplier bank account not found");
        }
        if (oldSupplierBankAccount.getSupplier().getStatus().equals(Status.APPROVED.code())) {
            Integer count = supplierBankAccountRepo.countBySupplierId(oldSupplierBankAccount.getSupplier().getId());
            if (count.equals(1)) {
                throw new ApiException(400, "No other accounts found, account could not be deleted");
            }
        }
        supplierBankAccountRepo.delete(oldSupplierBankAccount);
        return "Deleted successfully";
    }
}
