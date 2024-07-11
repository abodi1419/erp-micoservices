package com.later.procurement.App.procurement.commentService.model;

import com.later.procurement.App.procurement.commentService.entity.ProcurementComment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ProcurementComment}
 */
public record ProcurementCommentDto(Long createdById, String createdByName, String createdByNameAr,
                                    String createdByCompanyNumber, String action, String comment,
                                    LocalDateTime createdAt) implements Serializable {
}