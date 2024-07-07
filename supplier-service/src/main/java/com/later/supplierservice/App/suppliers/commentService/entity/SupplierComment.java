package com.later.supplierservice.App.suppliers.commentService.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.supplierservice.interfaces.commonEntity.EmployeeInterfaces.CreatedByInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "sp_comments")
public class SupplierComment implements CreatedByInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    private String action;
    @JsonIgnore
    private Long refId;
    @JsonIgnore
    private String requestRefCode;
    @Column(columnDefinition = "text")
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;
//    @ManyToOne
//    @JoinColumn(name = "requestRefCode", referencedColumnName = "refCode", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false,
//            updatable = false)
//    @JsonIgnore
//    private Supplier supplier;

}
