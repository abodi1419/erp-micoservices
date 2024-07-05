package com.later.inventoryservice.App.inventory.DocumentService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.later.inventoryservice.interfaces.HasSoftDelete;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "inv_documents")
public class InventoryDocument implements HasSoftDelete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attachmentType;
    private String attachmentName;
    @Column(unique = true)
    private String fileName;
    private String fileType;
    @JsonIgnore
    private String systemCode;
    @JsonIgnore
    private String systemPart;
    @JsonIgnore
    private Long refId;
    @JsonIgnore
    private Integer serial;
    @Column(columnDefinition = "bit default 0")
    @JsonIgnore
    private Boolean deleted = false;
    @JsonIgnore
    private LocalDateTime deletedAt;
}
