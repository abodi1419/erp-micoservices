package com.later.procurement.App.procurement.commentService.mapper;

import com.later.procurement.App.procurement.commentService.entity.ProcurementComment;
import com.later.procurement.App.procurement.commentService.model.ProcurementCommentDto;


public class ProcurementCommentMapper {
    public static ProcurementCommentDto toDto(ProcurementComment comment) {
        if (comment == null) {
            return null;
        }
        return new ProcurementCommentDto(comment.getCreatedById(), comment.getCreatedByName(),
                comment.getCreatedByNameAr(), comment.getCreatedByCompanyNumber(),
                comment.getAction(), comment.getComment(), comment.getCreatedAt());

    }
}
