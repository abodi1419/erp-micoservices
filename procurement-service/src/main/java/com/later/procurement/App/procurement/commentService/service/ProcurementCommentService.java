package com.later.procurement.App.procurement.commentService.service;


import com.later.procurement.App.procurement.commentService.entity.ProcurementComment;
import com.later.procurement.App.procurement.commentService.repository.ProcurementCommentRepo;
import com.later.procurement.Security.Auth.entities.Employee;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcurementCommentService {
    private final ProcurementCommentRepo procurementCommentRepo;

    public void createComment(@NotNull @NotEmpty String commentText,
                              @NotNull Employee commenter,
                              @NotNull Long refId,
                              @NotNull String requestRefCode,
                              String action) {
        ProcurementComment procurementComment = new ProcurementComment();
        procurementComment.setCreatedById(commenter.getEmployeeId());
        procurementComment.setCreatedByName(commenter.getEmployeeFullName());
        procurementComment.setCreatedByNameAr(commenter.getEmployeeFullNameAr());
        procurementComment.setCreatedByCompanyNumber(commenter.getEmployeeCompanyNumber());
        procurementComment.setComment(commentText);
        procurementComment.setRefId(refId);
        procurementComment.setAction(action);
        procurementComment.setRequestRefCode(requestRefCode);
        procurementCommentRepo.save(procurementComment);
    }
}
