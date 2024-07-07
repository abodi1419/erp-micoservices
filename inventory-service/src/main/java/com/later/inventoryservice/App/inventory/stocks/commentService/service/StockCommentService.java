package com.later.inventoryservice.App.inventory.stocks.commentService.service;


import com.later.inventoryservice.App.inventory.stocks.commentService.entity.StockComment;
import com.later.inventoryservice.App.inventory.stocks.commentService.repository.StockCommentRepo;
import com.later.inventoryservice.Security.Auth.entities.Employee;
import com.later.inventoryservice.Security.Auth.entities.Employee;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockCommentService {
    private final StockCommentRepo stockCommentRepo;

    public void createComment(@NotNull @NotEmpty String commentText,
                              @NotNull Employee commenter,
                              @NotNull Long refId,
                              @NotNull String requestRefCode,
                              String action) {
        StockComment stockComment = new StockComment();
        stockComment.setCreatedById(commenter.getId());
        stockComment.setCreatedByName(commenter.getEmployeeFullName());
        stockComment.setCreatedByNameAr(commenter.getEmployeeFullNameAr());
        stockComment.setCreatedByCompanyNumber(commenter.getEmployeeCompanyNumber());
        stockComment.setComment(commentText);
        stockComment.setRefId(refId);
        stockComment.setAction(action);
        stockComment.setRequestRefCode(requestRefCode);
        stockCommentRepo.save(stockComment);
    }
}
