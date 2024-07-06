package com.later.erp.App.procurement.ApprovalService.service;

import com.later.erp.Authorization.Security.Auth.entities.Employee;
import com.later.erp.App.procurement.ApprovalService.entity.ProcurementActiveApproval;
import com.later.erp.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;
import com.later.erp.App.procurement.ApprovalService.entity.PurchaseOrderWorkflow;
import com.later.erp.App.procurement.ApprovalService.entity.PurchaseRequestWorkflow;
import com.later.erp.App.procurement.ApprovalService.repository.ProcurementActiveApprovalRepo;
import com.later.erp.App.procurement.ApprovalService.repository.ProcurementApprovalSpecialConditionRepo;
import com.later.erp.App.procurement.ApprovalService.repository.PurchaseOrderWorkflowRepo;
import com.later.erp.App.procurement.ApprovalService.repository.PurchaseRequestWorkflowRepo;
import com.later.erp.App.procurement.commentService.service.ProcurementCommentService;
import com.later.erp.CommonModules.company.costCenter.entity.CostCenter;
import com.later.erp.CommonModules.company.division.entity.Division;
import com.later.erp.Exception.ApiException;
import com.later.erp.constants.Status;
import com.later.erp.constants.UserActions;
import com.later.erp.interfaces.WithApproval;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcurementApprovalService {
    private final PurchaseRequestWorkflowRepo purchaseRequestWorkflowRepo;
    private final ProcurementActiveApprovalRepo procurementActiveApprovalRepo;
    private final ProcurementApprovalSpecialConditionRepo procurementApprovalSpecialConditionRepo;
    private final ProcurementCommentService commentService;
    private final PurchaseOrderWorkflowRepo purchaseOrderWorkflowRepo;

    public WithApproval approve(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<ProcurementActiveApproval> procurementActiveApprovals = procurementActiveApprovalRepo
                .getAllBySystemCodeAndRefId(systemCode, withApproval.getId());
        ProcurementActiveApproval currentApprover = procurementActiveApprovals.stream()
                .filter(a -> a.getApproverId().equals(loginEmployee.getId()) &&
                        a.getStatus().equals(withApproval.getStatus()))
                .findFirst().orElse(null);
        if (currentApprover == null) {
            throw new ApiException(401, "Unauthorized");
        }

        List<ProcurementActiveApproval> nextApprovers = procurementActiveApprovals.stream()
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
            commentService.createComment(comment, loginEmployee, withApproval.getId(), systemCode, "Approve");
        }
        return withApproval;
    }


    public WithApproval reject(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<ProcurementActiveApproval> systemsActiveApprovals = procurementActiveApprovalRepo.getAllBySystemCodeAndRefId(systemCode, withApproval.getId());
        ProcurementActiveApproval currentApprover = systemsActiveApprovals.stream()
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
            ;
            commentService.createComment(UserActions.REJECT.action() + " with comment: " + comment, loginEmployee, withApproval.getId(), systemCode, UserActions.REJECT.action());
        } else {
            commentService.createComment(UserActions.REJECT.action() + " with no comment", loginEmployee, withApproval.getId(), systemCode, UserActions.REJECT.action());
        }
        return withApproval;
    }

    public WithApproval rejectForModify(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<ProcurementActiveApproval> systemsActiveApprovals = procurementActiveApprovalRepo.getAllBySystemCodeAndRefId(systemCode, withApproval.getId());
        ProcurementActiveApproval currentApprover = systemsActiveApprovals.stream()
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
            commentService.createComment(comment, loginEmployee, withApproval.getId(), systemCode, UserActions.REJECT.action());
        }
        return withApproval;
    }

    public WithApproval rejectForReturn(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        withApproval.getStatus();
        List<ProcurementActiveApproval> systemsActiveApprovals = procurementActiveApprovalRepo.getAllBySystemCodeAndRefId(systemCode, withApproval.getId());
        ProcurementActiveApproval currentApprover = systemsActiveApprovals.stream()
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
            commentService.createComment(comment, loginEmployee, withApproval.getId(), systemCode, UserActions.REJECT.action());
        }
        return withApproval;
    }

    private void notifyNextApprovers(Long createdBy, List<ProcurementActiveApproval> nextApprovers, String refCode, String systemCode) {

        // todo: implement email notifications.
    }

    private void notifyApprovalLog(ProcurementActiveApproval currentApprover, String refCode, String systemCode, String reject) {
        // todo: implement approval log.
    }

    private void notifyCreator(Long createdBy, ProcurementActiveApproval currentApp, String refCode, String systemCode, String action) {
        // todo: implement email notifications.
    }


    public void saveApproval(List<ProcurementActiveApproval> systemsActiveApprovals, WithApproval withApproval) {
        if (withApproval.getUpdatedAt() != null) {
            procurementActiveApprovalRepo.deleteByRefIdAndSystemCode(withApproval.getId(),
                    systemsActiveApprovals.get(0).getSystemCode());
        }
        for (ProcurementActiveApproval prcurementActiveApproval : systemsActiveApprovals) {
            prcurementActiveApproval.setRefId(withApproval.getId());
        }
        procurementActiveApprovalRepo.saveAll(systemsActiveApprovals);
    }

    public boolean checkPurchaseRequestApproval(Long divisionId, Long disciplineId, Boolean isService) {
        List<PurchaseRequestWorkflow> purchaseRequestWorkflow = purchaseRequestWorkflowRepo
                .findByDivisionIdAndDisciplineId(divisionId, disciplineId);
        purchaseRequestWorkflow.removeIf(w -> !w.getService().equals(isService));
        return !purchaseRequestWorkflow.isEmpty();
    }

    public List<ProcurementActiveApproval> generatePurchaseRequestApprovers(WithApproval withApproval, String systemCode,
                                                                            Long divisionId, Long disciplineId, Boolean isService) {
        List<PurchaseRequestWorkflow> purchaseRequestWorkflow = purchaseRequestWorkflowRepo
                .findByDivisionIdAndDisciplineId(divisionId, disciplineId);
        purchaseRequestWorkflow.removeIf(w -> !w.getService().equals(isService));
        List<ProcurementActiveApproval> activeApprovals = new ArrayList<>();
        if (!purchaseRequestWorkflow.isEmpty()) {
            for (PurchaseRequestWorkflow sw : purchaseRequestWorkflow) {
                ProcurementActiveApproval activeApproval = new ProcurementActiveApproval();
                activeApproval.setStatus(sw.getStatus());
                activeApproval.setRefCode(withApproval.getRefCode());
                activeApproval.setApproverId(sw.getApproverId());
                activeApproval.setApproverName(sw.getApproverName());
                activeApproval.setApproverNameAr(sw.getApproverNameAr());
                activeApproval.setApproverCompanyNumber(sw.getApproverCompanyNumber());
                activeApproval.setSystemCode(systemCode);
                activeApprovals.add(activeApproval);
            }
        }
        if (!activeApprovals.isEmpty()) {
            return activeApprovals;
        }

        return null;
    }

    public List<ProcurementActiveApproval> generatePurchaseOrderApprovers(WithApproval withApproval, String systemCode,
                                                                          Division division, CostCenter costCenter, Boolean pettyCash,
                                                                          Double total) {
        List<PurchaseOrderWorkflow> purchaseOrderWorkflow = purchaseOrderWorkflowRepo
                .findApproval(costCenter.getId(), division.getId(), pettyCash, total);
        List<ProcurementActiveApproval> activeApprovals = new ArrayList<>();
        if (!purchaseOrderWorkflow.isEmpty()) {
            for (PurchaseOrderWorkflow sw : purchaseOrderWorkflow) {
                ProcurementActiveApproval activeApproval = new ProcurementActiveApproval();
                activeApproval.setStatus(sw.getStatus());
                activeApproval.setRefCode(withApproval.getRefCode());
                activeApproval.setApproverId(sw.getApproverId());
                activeApproval.setApproverName(sw.getApproverName());
                activeApproval.setApproverNameAr(sw.getApproverNameAr());
                activeApproval.setApproverCompanyNumber(sw.getApproverCompanyNumber());
                activeApproval.setSystemCode(systemCode);
                activeApprovals.add(activeApproval);
            }
        }
        if (!activeApprovals.isEmpty()) {
            return activeApprovals;
        }

        return null;
    }

    public List<ProcurementApprovalSpecialCondition> checkSpecialConditions(Short status, String systemCode) {
        return procurementApprovalSpecialConditionRepo.findAllBySystemCodeAndStatus(systemCode, status);
    }
}
