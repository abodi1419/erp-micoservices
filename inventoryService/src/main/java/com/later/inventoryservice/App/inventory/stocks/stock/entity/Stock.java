package com.later.inventoryservice.App.inventory.stocks.stock.entity;

import com.later.inventoryservice.App.inventory.DocumentService.entity.InventoryDocument;
import com.later.inventoryservice.App.inventory.interfaces.HasAttachment;
import com.later.inventoryservice.App.inventory.stocks.approvalService.entity.StockActiveApproval;
import com.later.inventoryservice.App.inventory.stocks.commentService.entity.StockComment;
import com.later.inventoryservice.App.inventory.warehouses.entity.Warehouse;
import com.later.inventoryservice.App.inventory.warehouses.entity.WarehouseDetails;
import com.later.inventoryservice.interfaces.WithApproval;
import com.later.inventoryservice.interfaces.commonEntity.ItemInterface;
import com.later.inventoryservice.interfaces.commonEntity.UnitOfMeasureInterface;
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
@Table(name = "inv_stock")
public class Stock implements ItemInterface, UnitOfMeasureInterface, WithApproval, HasAttachment{
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
    private List<StockComment> comments;
    @SQLRestriction("request_ref_code like 'WAR%'")
    @OneToMany(mappedBy = "refId", fetch = FetchType.EAGER)
    private List<StockActiveApproval> approval;
    @SQLRestriction("system_code='WAR' and system_part='master' and deleted='false'")
    @OneToMany(mappedBy = "refId")
    private List<InventoryDocument> documents;

}
