package com.later.erp.App.inventory.stocks.stock.entity;

import com.later.erp.App.inventory.stocks.approvalService.entity.StockActiveApproval;
import com.later.erp.App.inventory.warehouses.entity.Warehouse;
import com.later.erp.App.inventory.warehouses.entity.WarehouseDetails;
import com.later.erp.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.erp.App.procurement.DocumentService.interfaces.HasAttachment;
import com.later.erp.App.suppliers.commentService.entity.SupplierComment;
import com.later.erp.interfaces.WithApproval;
import com.later.erp.interfaces.commonEntity.ItemInterface;
import com.later.erp.interfaces.commonEntity.UnitOfMeasureInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "war_stock")
public class Stock implements ItemInterface, UnitOfMeasureInterface, WithApproval, HasAttachment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long itemId;
    private String itemRefCode;
    private String itemName;
    private String itemNameAr;
    private String itemDescription;
    private String itemDescriptionAr;
    private Boolean serviceItem;
    private Long unitOfMeasureId;
    private String unitOfMeasureName;
    private String unitOfMeasureNameAr;
    private String unitOfMeasureCode;
    private String unitOfMeasureCodeAr;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private WarehouseDetails warehouseDetails;
    private Integer quantity;
    @Column(unique = true)
    private String refCode;
    @Column(unique = true)
    private Integer serial;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime lastApprovalDate;
    private Long createdById;
    private String createdByName;
    private String createdByNameAr;
    private String createdByCompanyNumber;
    @Column(columnDefinition = "tinyint")
    private Short status;
    @Column(columnDefinition = "bit default 0")
    private Boolean active = false;
    /////////////////////////////////////////////////////////
//    @JoinFormula("request_ref_code=ref_code")
    @SQLRestriction("request_ref_code like 'WAR%'")
    @OneToMany(mappedBy = "refId")
    private List<SupplierComment> comments;
    @SQLRestriction("request_ref_code like 'WAR%'")
    @OneToMany(mappedBy = "refId", fetch = FetchType.EAGER)
    private List<StockActiveApproval> approval;
    @SQLRestriction("system_code='WAR' and system_part='master' and deleted='false'")
    @OneToMany(mappedBy = "refId")
    private List<ProcurementDocument> documents;

}
