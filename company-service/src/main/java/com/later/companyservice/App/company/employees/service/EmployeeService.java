package com.later.companyservice.App.company.employees.service;

import com.later.companyservice.App.company.HR.jobTitles.entity.JobTitle;
import com.later.companyservice.App.company.HR.jobTitles.repository.JobTitleRepo;
import com.later.companyservice.App.company.employees.entity.Employee;
import com.later.companyservice.App.company.employees.entity.EmployeeHistory;
import com.later.companyservice.App.company.employees.model.EmployeeCreationModel;
import com.later.companyservice.App.company.employees.model.EmployeeShortModel;
import com.later.companyservice.App.company.employees.repository.EmployeeHistoryRepo;
import com.later.companyservice.App.company.employees.repository.EmployeeRepo;
import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.constants.ResponseText;
import com.later.companyservice.constants.UserActions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeHistoryRepo employeeHistoryRepo;
    private final JobTitleRepo jobTitleRepo;

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Employee findById(Long id) throws ApiException {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null) {
            throw new ApiException(400, "Employee not found");
        }
        return employee;
    }

    public String create(EmployeeCreationModel employeeCreationModel) throws ApiException {
        JobTitle jobTitle = jobTitleRepo.findById(employeeCreationModel.getJobTitle()).orElse(null);
        if (jobTitle == null) {
            throw new ApiException(400, "Job title not found");
        }

        Employee employee = new Employee();
        employee.setSsn(employeeCreationModel.getSsn());
        employee.setLevel(employeeCreationModel.getLevel());
        employee.setEmail(employeeCreationModel.getEmail());
        employee.setDob(employeeCreationModel.getDob());
        employee.setFirstName(employeeCreationModel.getFirstName());
        employee.setMiddleName(employeeCreationModel.getMiddleName());
        employee.setLastName(employeeCreationModel.getLastName());
        employee.setFullName(employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
        employee.setShorName(employee.getFirstName() + " " + employee.getLastName());
        employee.setFirstNameAr(employeeCreationModel.getFirstNameAr());
        employee.setMiddleNameAr(employeeCreationModel.getMiddleNameAr());
        employee.setLastNameAr(employeeCreationModel.getLastNameAr());
        employee.setFullNameAr(employee.getFirstNameAr() + " " + employee.getMiddleNameAr() + " " +
                employee.getLastNameAr());
        employee.setShorNameAr(employee.getFirstNameAr() + " " + employee.getLastNameAr());
        employee.setActive(1);
        employee.setJobTitle(jobTitle);
        employee.setImagePath(employeeCreationModel.getImagePath());
        employee.setCompanyNumber(employeeCreationModel.getCompanyNumber());
        employee.setDateOfHire(employeeCreationModel.getDateOfHire());
        employee.setLoginUserId(employeeCreationModel.getLoginUserId());
        employeeRepo.save(employee);
        return "Created Successfully";
    }

    public String update(Long id, EmployeeCreationModel employeeCreationModel) throws ApiException {
        Employee employee1 = findById(id);
        EmployeeHistory employeeHistory = createHistory(employee1, UserActions.UPDATE.action(), 1L);
        employee1.setFirstName(employeeCreationModel.getFirstName());
        employee1.setMiddleName(employeeCreationModel.getMiddleName());
        employee1.setLastName(employeeCreationModel.getLastName());
        employee1.setFirstNameAr(employeeCreationModel.getFirstNameAr());
        employee1.setMiddleNameAr(employeeCreationModel.getMiddleNameAr());
        employee1.setLastNameAr(employeeCreationModel.getLastNameAr());
        employee1.setDob(employeeCreationModel.getDob());
        employee1.setEmail(employeeCreationModel.getEmail());
        employee1.setLevel(employeeCreationModel.getLevel());
        employee1.setSsn(employeeCreationModel.getSsn());
        employee1.setCompanyNumber(employeeCreationModel.getCompanyNumber());
        employee1.setDateOfHire(employeeCreationModel.getDateOfHire());
        JobTitle jobTitle = jobTitleRepo.findById(employeeCreationModel.getJobTitle()).orElse(null);
        if (jobTitle == null) {
            throw new ApiException(400, "Job title not found");
        }
        employee1.setJobTitle(jobTitle);
        employee1.setActive(employeeCreationModel.getActive());
        employee1.setImagePath(employeeCreationModel.getImagePath());
        employeeHistoryRepo.save(employeeHistory);
        employeeRepo.save(employee1);
        return ResponseText.UPDATED.text();
    }

    private EmployeeHistory createHistory(Employee employee1, String action, Long actionBy) {
        EmployeeHistory employeeHistory = new EmployeeHistory();
        employeeHistory.setDob(employee1.getDob());
        employeeHistory.setEmail(employee1.getEmail());
        employeeHistory.setLevel(employee1.getLevel());
        employeeHistory.setSsn(employee1.getSsn());
        employeeHistory.setActive(employee1.getActive());
        employeeHistory.setCompanyNumber(employee1.getCompanyNumber());
        employeeHistory.setDateOfHire(employee1.getDateOfHire());
        employeeHistory.setImagePath(employee1.getImagePath());
        employeeHistory.setJobTitle(employee1.getJobTitle().getId());
        employeeHistory.setRefId(employee1.getId());
        employeeHistory.setLoginUserId(employee1.getLoginUserId());
        employeeHistory.setAction(action);
        employeeHistory.setActionBy(actionBy);
        return employeeHistory;
    }

    public List<EmployeeShortModel> listShort() {
        return employeeRepo.listShort();
    }

    public Employee findByIdOrElseNull(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public List<Employee> findAllById(List<Long> employeeIds) {
        return employeeRepo.findAllById(employeeIds);
    }
}
