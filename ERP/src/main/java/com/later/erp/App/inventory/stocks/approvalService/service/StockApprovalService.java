package com.later.erp.App.inventory.stocks.approvalService.service;


import com.later.erp.App.inventory.stocks.approvalService.entity.StockActiveApproval;
import com.later.erp.App.inventory.stocks.approvalService.entity.StockApprovalWorkflow;
import com.later.erp.App.inventory.stocks.approvalService.repository.StockActiveApprovalRepo;
import com.later.erp.App.inventory.stocks.approvalService.repository.StockApprovalWorkflowRepo;
import com.later.erp.App.inventory.stocks.commentService.service.StockCommentService;
import com.later.erp.Authorization.Security.Auth.entities.Employee;
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
public class StockApprovalService {


    private final StockActiveApprovalRepo stockActiveApprovalRepo;
    private final StockCommentService stockCommentService;
    private final StockApprovalWorkflowRepo stockApprovalWorkflowRepo;

    public WithApproval approve(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<StockActiveApproval> stockActiveApprovals = stockActiveApprovalRepo.getAllByRefIdAndSystemCode(withApproval.getId(), systemCode);

        StockActiveApproval currentApprover = stockActiveApprovals.stream()
                .filter(a -> a.getApproverId().equals(loginEmployee.getId()) &&
                        a.getStatus().equals(withApproval.getStatus()))
                .findFirst().orElse(null);
        if (currentApprover == null) {
            throw new ApiException(401, "Unauthorized");
        }

        List<StockActiveApproval> nextApprovers = stockActiveApprovals.stream()
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
            stockCommentService.createComment(comment, loginEmployee, withApproval.getId(), withApproval.getRefCode(), "Approve");
        }
        return withApproval;
    }


    public WithApproval reject(WithApproval withApproval, String systemCode, Employee loginEmployee, String comment) throws ApiException {
        List<StockActiveApproval> stockActiveApprovals = stockActiveApprovalRepo.getAllByRefIdAndSystemCode(withApproval.getId(), systemCode);

        StockActiveApproval currentApprover = stockActiveApprovals.stream()
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
            stockCommentService.createComment(comment, loginEmployee, withApproval.getId(), withApproval.getRefCode(), UserActions.REJECT.action());
        }
        return withApproval;
    }


    private void notifyNextApprovers(Long createdBy, List<StockActiveApproval> nextApprovers, String refCode, String systemCode) {

        // todo: implement email notifications.
    }

    private void notifyApprovalLog(StockActiveApproval currentApprover, String refCode, String systemCode, String reject) {
        // todo: implement approval log.
    }

    private void notifyCreator(Long createdBy, StockActiveApproval currentApp, String refCode, String systemCode, String action) {
        // todo: implement email notifications.
    }


    public void saveApproval(List<StockActiveApproval> stockActiveApprovals, WithApproval withApproval) {
        if (withApproval.getUpdatedAt() != null) {
            stockActiveApprovalRepo.deleteAllByRefIdAndSystemCode(withApproval.getId(),
                    stockActiveApprovals.get(0).getSystemCode());
        }
        for (StockActiveApproval stockActiveApproval : stockActiveApprovals) {
            stockActiveApproval.setRefId(withApproval.getId());
        }
        stockActiveApprovalRepo.saveAll(stockActiveApprovals);
    }

    public List<StockActiveApproval> generateApprovers(WithApproval withApproval, String systemCode) {
        List<StockApprovalWorkflow> stockApprovalWorkflows = stockApprovalWorkflowRepo.findAll();

        List<StockActiveApproval> activeApprovals = new ArrayList<>();

        if (!stockApprovalWorkflows.isEmpty()) {
            for (StockApprovalWorkflow sw : stockApprovalWorkflows) {
                StockActiveApproval activeApproval = new StockActiveApproval();
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
