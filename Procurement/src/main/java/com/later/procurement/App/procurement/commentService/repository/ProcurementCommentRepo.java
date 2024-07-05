package com.later.procurement.App.procurement.commentService.repository;


import com.later.procurement.App.procurement.commentService.entity.ProcurementComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcurementCommentRepo extends JpaRepository<ProcurementComment, Long> {

}
