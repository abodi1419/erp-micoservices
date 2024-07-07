package com.later.supplierservice.App.suppliers.ApprovalService.service;

import com.later.supplierservice.Security.Auth.entities.Employee;
import com.later.supplierservice.App.suppliers.ApprovalService.entity.SupplierActiveApproval;
import com.later.supplierservice.App.suppliers.ApprovalService.entity.SupplierApprovalWorkflow;
import com.later.supplierservice.App.suppliers.ApprovalService.repository.SupplierActiveApprovalRepo;
import com.later.supplierservice.App.suppliers.ApprovalService.repository.SupplierApprovalWorkflowRepo;
import com.later.supplierservice.App.suppliers.commentService.service.SupplierCommentService;
import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.Status;
import com.later.supplierservice.constants.UserActions;
import com.later.supplierservice.interfaces.WithApproval;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierApprovalService {
    private final SupplierApprovalWorkflowRepo supplierApprovalWorkflowRepo;
    private final SupplierActiveApprovalRepo supplierActiveApprovalRepo;
    private final SupplierCommentService supplierCommentService;

    public WithApproval approve(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<SupplierActiveApproval> supplierActiveApprovals = supplierActiveApprovalRepo.getAllByRefIdAndSystemCode(withApproval.getId(), systemCode);

        SupplierActiveApproval currentApprover = supplierActiveApprovals.stream()
                .filter(a -> a.getApproverId().equals(loginEmployee.getId()) &&
                        a.getStatus().equals(withApproval.getStatus()))
                .findFirst().orElse(null);
        if (currentApprover == null) {
            throw new ApiException(401, "Unauthorized");
        }

        List<SupplierActiveApproval> nextApprovers = supplierActiveApprovals.stream()
                .filter(a -> a.getStatus() == withApproval.getStatus() + 1)
                .collect(Collectors.toList());

        if (nextApprovers.isEmpty()) {
            withApproval.setStatus(Status.APPROVED.code());
        } else {
            withApproval.setStatus((short) (withApproval.getStatus() + (short) 1));
        }
        withApproval.setLastApprovalDate(LocalDateTime.now());

        notifyApprovalLog(currentApprover, withApproval.getRefCode(), systemCode, "Approve");
        notifyCreator(withApproval.getCreatedById(), currentApprover, withApproval.getRefCode(), systemCode, "Approve");
        notifyNextApprovers(withApproval.getCreatedById(), nextApprovers, withApproval.getRefCode(), systemCode);
        if (comment != null && !comment.isEmpty()) {
            supplierCommentService.createComment(comment, loginEmployee, withApproval.getId(), withApproval.getRefCode(), "Approve");
        }
        return withApproval;
    }


    public WithApproval reject(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<SupplierActiveApproval> supplierActiveApprovals = supplierActiveApprovalRepo.getAllByRefIdAndSystemCode(withApproval.getId(), systemCode);

        SupplierActiveApproval currentApprover = supplierActiveApprovals.stream()
                .filter(a -> a.getApproverId().equals(loginEmployee.getId()) &&
                        a.getStatus().equals(withApproval.getStatus()))
                .findFirst().orElse(null);
        if (currentApprover == null) {
            throw new ApiException(401, "Unauthorized");
        }

        notifyApprovalLog(currentApprover, withApproval.getRefCode(), systemCode, "Reject");
        withApproval.setStatus(Status.REJECTED.code());
        withApproval.setLastApprovalDate(LocalDateTime.now());

        notifyCreator(withApproval.getCreatedById(), currentApprover, withApproval.getRefCode(), systemCode, UserActions.REJECT.action());
        if (comment != null && !comment.isEmpty()) {
            supplierCommentService.createComment(comment, loginEmployee, withApproval.getId(), withApproval.getRefCode(), UserActions.REJECT.action());
        }
        return withApproval;
    }

    public WithApproval rejectForModify(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<SupplierActiveApproval> supplierActiveApprovals = supplierActiveApprovalRepo.getAllByRefIdAndSystemCode(withApproval.getId(), systemCode);

        SupplierActiveApproval currentApprover = supplierActiveApprovals.stream()
                .filter(a -> a.getApproverId().equals(loginEmployee.getId()) &&
                        a.getStatus().equals(withApproval.getStatus()))
                .findFirst().orElse(null);
        if (currentApprover == null) {
            throw new ApiException(401, "Unauthorized");
        }

        notifyApprovalLog(currentApprover, withApproval.getRefCode(), systemCode, "Reject");
        withApproval.setStatus(Status.REJECTED_FOR_MODIFY.code());
        withApproval.setLastApprovalDate(LocalDateTime.now());

        notifyCreator(withApproval.getCreatedById(), currentApprover, withApproval.getRefCode(), systemCode, UserActions.REJECT.action());
        if (comment != null && !comment.isEmpty()) {
            supplierCommentService.createComment(comment, loginEmployee, withApproval.getId(), withApproval.getRefCode(), UserActions.REJECT.action());
        }
        return withApproval;
    }

    public WithApproval rejectForReturn(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<SupplierActiveApproval> supplierActiveApprovals = supplierActiveApprovalRepo.getAllByRefIdAndSystemCode(withApproval.getId(), systemCode);
        SupplierActiveApproval currentApprover = supplierActiveApprovals.stream()
                .filter(a -> a.getApproverId().equals(loginEmployee.getId()) &&
                        a.getStatus().equals(withApproval.getStatus()))
                .findFirst().orElse(null);
        if (currentApprover == null) {
            throw new ApiException(401, "Unauthorized");
        }

        notifyApprovalLog(currentApprover, withApproval.getRefCode(), systemCode, UserActions.REJECT.action());
        withApproval.setStatus(Status.REJECTED_FOR_RETURN.code());
        withApproval.setLastApprovalDate(LocalDateTime.now());
        notifyCreator(withApproval.getCreatedById(), currentApprover, withApproval.getRefCode(), systemCode, "Reject");
        if (comment != null && !comment.isEmpty()) {
            supplierCommentService.createComment(comment, loginEmployee, withApproval.getId(), withApproval.getRefCode(), UserActions.REJECT.action());
        }
        return withApproval;
    }

    private void notifyNextApprovers(Long createdBy, List<SupplierActiveApproval> nextApprovers, String refCode, String systemCode) {

        // todo: implement email notifications.
    }

    private void notifyApprovalLog(SupplierActiveApproval currentApprover, String refCode, String systemCode, String reject) {
        // todo: implement approval log.
    }

    private void notifyCreator(Long createdBy, SupplierActiveApproval currentApp, String refCode, String systemCode, String action) {
        // todo: implement email notifications.
    }


    public void saveApproval(List<SupplierActiveApproval> supplierActiveApprovals, WithApproval withApproval) {
        if (withApproval.getUpdatedAt() != null) {
            supplierActiveApprovalRepo.deleteAllByRefIdAndSystemCode(withApproval.getId(),
                    supplierActiveApprovals.get(0).getSystemCode());
        }
        for (SupplierActiveApproval supplierActiveApproval : supplierActiveApprovals) {
            supplierActiveApproval.setRefId(withApproval.getId());
        }
        supplierActiveApprovalRepo.saveAll(supplierActiveApprovals);
    }

    public List<SupplierActiveApproval> generateApprovers(WithApproval withApproval, String systemCode) {
        List<SupplierApprovalWorkflow> SupplierApprovalWorkflows = supplierApprovalWorkflowRepo
                .findAll();

        List<SupplierActiveApproval> activeApprovals = new ArrayList<>();
        if (!SupplierApprovalWorkflows.isEmpty()) {
            for (SupplierApprovalWorkflow sw : SupplierApprovalWorkflows) {
                SupplierActiveApproval activeApproval = new SupplierActiveApproval();
                activeApproval.setStatus(sw.getStatus());
                activeApproval.setRequestRefCode(withApproval.getRefCode());
                activeApproval.setSystemCode(systemCode);
                activeApproval.setApproverId(sw.getApproverId());
                activeApproval.setApproverName(sw.getApproverName());
                activeApproval.setApproverNameAr(sw.getApproverNameAr());
                activeApproval.setApproverCompanyNumber(sw.getApproverCompanyNumber());
                activeApprovals.add(activeApproval);
            }
        }
        if (!activeApprovals.isEmpty()) {
            return activeApprovals;
        }
        return null;
    }


}
