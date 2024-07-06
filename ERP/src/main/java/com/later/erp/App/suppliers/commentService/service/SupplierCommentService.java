package com.later.erp.App.suppliers.commentService.service;

import com.later.erp.Authorization.Security.Auth.entities.Employee;
import com.later.erp.App.suppliers.commentService.entity.SupplierComment;
import com.later.erp.App.suppliers.commentService.repository.SupplierCommentRepo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierCommentService {
    private final SupplierCommentRepo supplierCommentRepo;

    public void createComment(@NotNull @NotEmpty String commentText,
                              @NotNull Employee commenter,
                              @NotNull Long refId,
                              @NotNull String requestRefCode,
                              String action) {
        SupplierComment supplierComment = new SupplierComment();
        supplierComment.setCreatedById(commenter.getEmployeeId());
        supplierComment.setCreatedByName(commenter.getEmployeeFullName());
        supplierComment.setCreatedByNameAr(commenter.getEmployeeFullNameAr());
        supplierComment.setCreatedByCompanyNumber(commenter.getEmployeeCompanyNumber());
        supplierComment.setComment(commentText);
        supplierComment.setRefId(refId);
        supplierComment.setAction(action);
        supplierComment.setRequestRefCode(requestRefCode);
        supplierCommentRepo.save(supplierComment);
    }
}
