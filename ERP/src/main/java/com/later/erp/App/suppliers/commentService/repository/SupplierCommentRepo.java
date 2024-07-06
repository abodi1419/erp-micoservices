package com.later.erp.App.suppliers.commentService.repository;

import com.later.erp.App.suppliers.commentService.entity.SupplierComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierCommentRepo extends JpaRepository<SupplierComment, Long> {

}
