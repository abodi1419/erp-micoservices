package com.later.procurement.App.procurement.PurchaseRequest.service;


import com.later.procurement.App.company.employees.model.EmployeeShortModel;
import com.later.procurement.App.company.employees.service.EmployeeService;
import com.later.procurement.App.items.entity.Item;
import com.later.procurement.App.items.service.ItemService;
import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementActiveApproval;
import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;
import com.later.procurement.App.procurement.ApprovalService.service.ProcurementApprovalService;
import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.procurement.App.procurement.DocumentService.service.ProcurementDocumentService;
import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequest;
import com.later.procurement.App.procurement.PurchaseRequest.entity.PurchaseRequestDetails;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestCreationModel;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestDetailsCreationModel;
import com.later.procurement.App.procurement.PurchaseRequest.model.PurchaseRequestShortModel;
import com.later.procurement.App.procurement.PurchaseRequest.repository.PurchaseRequestDetailsRepo;
import com.later.procurement.App.procurement.PurchaseRequest.repository.PurchaseRequestRepo;
import com.later.procurement.CommonModules.company.costCenter.entity.CostCenter;
import com.later.procurement.CommonModules.company.costCenter.service.CostCenterService;
import com.later.procurement.CommonModules.company.department.entity.Department;
import com.later.procurement.CommonModules.company.department.service.DepartmentService;
import com.later.procurement.CommonModules.company.division.entity.Division;
import com.later.procurement.CommonModules.company.division.service.DivisionService;
import com.later.procurement.CommonModules.deliveryLocation.entity.DeliveryLocation;
import com.later.procurement.CommonModules.deliveryLocation.service.DeliveryLocationService;
import com.later.procurement.CommonModules.discipline.entity.Discipline;
import com.later.procurement.CommonModules.discipline.service.DisciplineService;
import com.later.procurement.CommonModules.models.DocumentModel;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.Security.Auth.entities.Employee;
import com.later.procurement.constants.ApprovalSpecialConditions;
import com.later.procurement.constants.ResponseText;
import com.later.procurement.constants.Status;
import com.later.procurement.constants.SystemCode;
import com.later.procurement.util.RefCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseRequestService {
    private final PurchaseRequestRepo purchaseRequestRepo;
    private final PurchaseRequestDetailsRepo purchaseRequestDetailsRepo;
    private final DisciplineService disciplineService;
    private final ItemService itemService;
    private final CostCenterService costCenterService;
    private final DepartmentService departmentService;
    private final DivisionService divisionService;
    private final DeliveryLocationService deliveryLocationService;
    private final ProcurementApprovalService procurementApprovalService;
    private final ProcurementDocumentService documentService;
    private final EmployeeService employeeService;


    public List<PurchaseRequest> findAll() {
        return purchaseRequestRepo.findAll();
    }

    public PurchaseRequest findById(Long id, Employee employee) throws ApiException {
        PurchaseRequest purchaseRequest = purchaseRequestRepo.findById(id).orElse(null);
        if (purchaseRequest == null || (purchaseRequest.getCreatedById().equals(employee.getId())
                && !purchaseRequest.getBuyerId().equals(employee.getId())
                && purchaseRequest.getApproval().stream()
                .noneMatch(a -> a.getApproverId().equals(employee.getId()))
        )) {
            throw new ApiException(404, "Purchase request not found");
        }
        purchaseRequest.setSpecialConditions(procurementApprovalService.checkSpecialConditions(purchaseRequest.getStatus(),
                SystemCode.PURCHASE_REQUEST.systemCode()));
        return purchaseRequest;
    }


    public String create(PurchaseRequestCreationModel purchaseRequestCreationModel, Employee loginUser) throws ApiException {
        PurchaseRequest purchaseRequest = validateCreationModel(purchaseRequestCreationModel, null, loginUser);
        List<ProcurementActiveApproval
                > procurementActiveApprovals = procurementApprovalService
                .generatePurchaseRequestApprovers(purchaseRequest, SystemCode.PURCHASE_REQUEST.systemCode(),
                        purchaseRequest.getDivisionId(), purchaseRequest.getDisciplineId(),
                        purchaseRequest.getService());
        if (procurementActiveApprovals == null) {
            purchaseRequest.setStatus(Status.APPROVED.code());
        } else {
            purchaseRequest.setStatus((short) 1);
        }
        List<ProcurementDocument> document = null;
        if (purchaseRequestCreationModel.getAttachment() != null && !purchaseRequestCreationModel.getAttachment().isEmpty()) {
            document = documentService.uploadFromTemp(purchaseRequestCreationModel.getAttachment(),
                    SystemCode.PURCHASE_REQUEST.systemCode());

        }
        purchaseRequest.setSerial(purchaseRequestRepo.getCurrentSerial());
        purchaseRequest.setRefCode(RefCode.generate(
                purchaseRequest.getService() ? "SR" : "PR",
                purchaseRequest.getSerial()));
        if (procurementActiveApprovals == null) {
            if (purchaseRequestCreationModel.getBuyer() == null) {
                throw new ApiException(400, "Buyer is mandatory");
            }
            com.later.procurement.App.company.employees.entity.Employee employee = employeeService.findById(purchaseRequestCreationModel.getBuyer());
            purchaseRequest.setBuyerId(employee.getId());
            purchaseRequest.setBuyerName(employee.getFullName());
            purchaseRequest.setBuyerNameAr(employee.getFullNameAr());
            purchaseRequest.setBuyerCompanyNumber(employee.getCompanyNumber());

        }
        purchaseRequest = purchaseRequestRepo.save(purchaseRequest);
        purchaseRequestDetailsRepo.saveAll(purchaseRequest.getPurchaseRequestDetails());
        if (procurementActiveApprovals != null) {
            procurementApprovalService.saveApproval(procurementActiveApprovals, purchaseRequest);
        }
        if (document != null) {
            documentService.saveDocument(document, purchaseRequest, true, SystemCode.PURCHASE_REQUEST.systemCode());
        }
        return ResponseText.CREATED.text();
    }

    public String update(Long id, PurchaseRequestCreationModel purchaseRequestCreationModel, Employee loginUser) throws ApiException {
        PurchaseRequest oldPurchaseRequest = purchaseRequestRepo.findById(id).orElse(null);
        if (oldPurchaseRequest == null) {
            throw new ApiException(404, "Purchase request not found");
        }
        PurchaseRequest purchaseRequest = validateCreationModel(purchaseRequestCreationModel, oldPurchaseRequest, loginUser);
        if (oldPurchaseRequest.getService() != purchaseRequest.getService()) {
            purchaseRequest.setSerial(purchaseRequestRepo.getCurrentSerial());
            purchaseRequest.setRefCode(RefCode.generate(
                    purchaseRequest.getService() ? "SR" : "PR",
                    purchaseRequest.getSerial()));
        }
        List<ProcurementActiveApproval
                > procurementActiveApprovals = procurementApprovalService
                .generatePurchaseRequestApprovers(purchaseRequest, SystemCode.PURCHASE_REQUEST.systemCode(),
                        purchaseRequest.getDivisionId(), purchaseRequest.getDisciplineId(),
                        purchaseRequest.getService());

        if (procurementActiveApprovals == null) {
            purchaseRequest.setStatus(Status.APPROVED.code());
        } else {
            purchaseRequest.setStatus((short) 1);
        }
        List<ProcurementDocument> document = null;
        if (purchaseRequestCreationModel.getAttachment() != null && !purchaseRequestCreationModel.getAttachment().isEmpty()) {
            document = documentService.uploadFromTemp(purchaseRequestCreationModel.getAttachment(),
                    SystemCode.PURCHASE_REQUEST.systemCode());

        }
        if (procurementActiveApprovals == null) {
            if (purchaseRequestCreationModel.getBuyer() == null) {
                throw new ApiException(400, "Buyer is mandatory");
            }
            com.later.procurement.App.company.employees.entity.Employee employee = employeeService.findById(purchaseRequestCreationModel.getBuyer());
            purchaseRequest.setBuyerId(employee.getId());
            purchaseRequest.setBuyerName(employee.getFullName());
            purchaseRequest.setBuyerNameAr(employee.getFullNameAr());
            purchaseRequest.setBuyerCompanyNumber(employee.getCompanyNumber());
        }
        purchaseRequest = purchaseRequestRepo.save(purchaseRequest);
        purchaseRequestDetailsRepo.saveAll(purchaseRequest.getPurchaseRequestDetails());
        if (procurementActiveApprovals != null) {
            procurementApprovalService.saveApproval(procurementActiveApprovals, purchaseRequest);
        }
        if (document != null) {
            documentService.saveDocument(document, purchaseRequest, true, SystemCode.PURCHASE_REQUEST.systemCode());
        }
        return ResponseText.CREATED.text();
    }

    private PurchaseRequest validateCreationModel(PurchaseRequestCreationModel purchaseRequestCreationModel,
                                                  PurchaseRequest oldPurchaseRequest, Employee loginUser)
            throws ApiException {
        CostCenter costCenter = costCenterService.findById(purchaseRequestCreationModel.getCostCenter());
        Department department = departmentService.findByIdUnderCostCenter(purchaseRequestCreationModel.getDepartment(),
                costCenter.getId());
        Division division = divisionService.findByIdUnderDepartment(purchaseRequestCreationModel.getDivision(),
                department.getId());
        Discipline discipline = disciplineService.findById(purchaseRequestCreationModel.getDiscipline());
        // todo: check access for discipline and division

        Map<Long, Item> items =
                getItemsMap(purchaseRequestCreationModel.getPurchaseRequestDetailsCreationModelList(), discipline);
        Map<Long, DeliveryLocation> deliveryLocationMap =
                getDeliveryLocations(purchaseRequestCreationModel.getPurchaseRequestDetailsCreationModelList());
        BigDecimal total = BigDecimal.ZERO;

        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setCostCenterId(costCenter.getId());
        purchaseRequest.setCostCenterName(costCenter.getName());
        purchaseRequest.setCostCenterNameAr(costCenter.getNameAr());
        purchaseRequest.setCostCenterRefCode(costCenter.getCode());
        purchaseRequest.setDepartmentId(department.getId());
        purchaseRequest.setDepartmentName(department.getName());
        purchaseRequest.setDepartmentNameAr(department.getNameAr());
        purchaseRequest.setDepartmentRefCode(department.getRefCode());
        purchaseRequest.setDivisionId(division.getId());
        purchaseRequest.setDivisionName(division.getName());
        purchaseRequest.setDivisionNameAr(division.getNameAr());
        purchaseRequest.setDisciplineId(discipline.getId());
        purchaseRequest.setDisciplineName(discipline.getName());
        purchaseRequest.setDisciplineNameAr(discipline.getNameAr());
        purchaseRequest.setDisciplineCode(discipline.getCode());
        purchaseRequest.setCreatedById(loginUser.getEmployeeId());
        purchaseRequest.setCreatedByName(loginUser.getEmployeeFullName());
        purchaseRequest.setCreatedByNameAr(loginUser.getEmployeeFullNameAr());
        purchaseRequest.setCreatedByCompanyNumber(loginUser.getEmployeeCompanyNumber());
        purchaseRequest.setDeliveryDate(purchaseRequestCreationModel.getDeliveryDate());
        purchaseRequest.setDeliveryTerms(purchaseRequestCreationModel.getDeliveryTerms());
        purchaseRequest.setGeneralRemarks(purchaseRequestCreationModel.getGeneralRemarks());
        purchaseRequest.setPermanentItems(purchaseRequestCreationModel.getPermanentItems());
        purchaseRequest.setService(purchaseRequestCreationModel.getService());
        purchaseRequest.setPurchaseRequestDetails(new ArrayList<>());
        for (PurchaseRequestDetailsCreationModel purchaseRequestDetailsCreationModel :
                purchaseRequestCreationModel.getPurchaseRequestDetailsCreationModelList()) {
            Item item = items.get(purchaseRequestDetailsCreationModel.getItem());
            DeliveryLocation deliveryLocation = deliveryLocationMap.get(purchaseRequestDetailsCreationModel.getDeliveryLocation());
            PurchaseRequestDetails purchaseRequestDetails = new PurchaseRequestDetails();
            purchaseRequestDetails.setItemId(item.getId());
            purchaseRequestDetails.setItemName(item.getName());
            purchaseRequestDetails.setItemNameAr(item.getNameAr());
            purchaseRequestDetails.setItemDescription(item.getDescription());
            purchaseRequestDetails.setItemDescriptionAr(item.getDescriptionAr());
            purchaseRequestDetails.setItemRefCode(item.getRefCode());
            purchaseRequestDetails.setServiceItem(item.getService());
            purchaseRequestDetails.setUnitOfMeasureId(item.getMeasureUnit().getId());
            purchaseRequestDetails.setUnitOfMeasureCode(item.getMeasureUnit().getCode());
            purchaseRequestDetails.setUnitOfMeasureCodeAr(item.getMeasureUnit().getCodeAr());
            purchaseRequestDetails.setUnitOfMeasureName(item.getMeasureUnit().getName());
            purchaseRequestDetails.setUnitOfMeasureNameAr(item.getMeasureUnit().getNameAr());
            if (item.getService() ^ purchaseRequestCreationModel.getService()) {
                throw new ApiException(400, "Item type mismatch");
            }
            purchaseRequestDetails.setDeliveryLocationId(deliveryLocation.getId());
            purchaseRequestDetails.setDeliveryLocationAddress(deliveryLocation.getAddress());
            purchaseRequestDetails.setDeliveryLocationAddressAr(deliveryLocation.getAddressAr());
            purchaseRequestDetails.setDeliveryLocationName(deliveryLocation.getName());
            purchaseRequestDetails.setDeliveryLocationNameAr(deliveryLocation.getNameAr());
            purchaseRequestDetails.setDeliveryLocationGoogleMapAddress(deliveryLocation.getGoogleMapAddress());

            // Sub-total and total calculation
            purchaseRequestDetails.setQuantity(purchaseRequestDetailsCreationModel.getQuantity());
            purchaseRequestDetails.setEstUnitPrice(purchaseRequestDetailsCreationModel.getEstUnitPrice());
            BigDecimal subTotal = BigDecimal.valueOf(purchaseRequestDetails.getEstUnitPrice())
                    .multiply(BigDecimal.valueOf(purchaseRequestDetails.getQuantity()))
                    .setScale(2, RoundingMode.HALF_UP);
            total = total.add(subTotal);
            /////////////////////////

            purchaseRequestDetails.setDescriptionOfGoods(purchaseRequestDetailsCreationModel.getDescriptionOfGoods());
            purchaseRequestDetails.setDescriptionOfGoodsAr(purchaseRequestDetailsCreationModel.getDescriptionOfGoodsAr());
            purchaseRequestDetails.setPurchaseRequest(purchaseRequest);
            purchaseRequestDetails.setRemarks(purchaseRequestDetailsCreationModel.getRemarks());
            purchaseRequest.getPurchaseRequestDetails().add(purchaseRequestDetails);
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        purchaseRequest.setTotal(total.doubleValue());

        if (oldPurchaseRequest != null) {
            purchaseRequest.setId(oldPurchaseRequest.getId());
            purchaseRequest.setSerial(oldPurchaseRequest.getSerial());
            purchaseRequest.setRefCode(oldPurchaseRequest.getRefCode());
        }
        return purchaseRequest;
    }

    private Map<Long, DeliveryLocation> getDeliveryLocations(List<PurchaseRequestDetailsCreationModel> purchaseRequestDetailsCreationModelList) throws ApiException {
        List<Long> locationsIds = purchaseRequestDetailsCreationModelList.stream()
                .map(PurchaseRequestDetailsCreationModel::getDeliveryLocation).distinct().toList();

        Map<Long, DeliveryLocation> locations = deliveryLocationService.findAllById(locationsIds).stream()
                .collect(Collectors.toMap(DeliveryLocation::getId, d -> d));
        if (locations.size() != locationsIds.size()) {
            throw new ApiException(404, "Some delivery locations not found");
        }
        return locations;
    }

    private Map<Long, Item> getItemsMap(List<PurchaseRequestDetailsCreationModel> purchaseRequestDetailsCreationModelList, Discipline discipline) throws ApiException {
        List<Long> itemsIds = purchaseRequestDetailsCreationModelList.stream()
                .map(PurchaseRequestDetailsCreationModel::getItem).distinct().toList();

        Map<Long, Item> items;
        if (discipline.getSupportAllItems()) {
            items = itemService.findAllById(itemsIds).stream().collect(Collectors.toMap(Item::getId, i -> i));
        } else {
            items = itemService.findAllByIdUnderDiscipline(itemsIds, discipline.getId())
                    .stream().collect(Collectors.toMap(Item::getId, i -> i));
        }
        if (items.size() != itemsIds.size()) {
            throw new ApiException(404, "Some items not found");
        }
        return items;
    }

    public List<Item> getItems(Long disciplineId, Employee loginUser) {
        return itemService.findAllUnderDiscipline(disciplineId);
    }

    public List<Discipline> getDisciplines(Employee loginUser) {
        return disciplineService.findAll();
    }

    public List<CostCenter> getCostCenters(Employee loginUser) throws ApiException {

        return costCenterService.findAll();
    }

    public List<Department> getDepartmentsUnderCostCenter(Long costCenterId, Employee loginUser) {
        return departmentService.findAllByCostCenter(costCenterId);
    }

    public List<Division> getDivisionsUnderDepartment(Long departmentId, Employee loginUser) {
        return divisionService.findAllUnderDepartment(departmentId);
    }

    public List<DeliveryLocation> getDeliveryLocations(Employee loginUser) {
        return deliveryLocationService.findAll();
    }

    public String approve(Long id, HashMap<String, String> map, Employee loginUser) throws ApiException {
        PurchaseRequest purchaseRequest = findById(id, loginUser);
        List<ProcurementApprovalSpecialCondition> approvalSpecialConditions = purchaseRequest.getSpecialConditions();
        for (ProcurementApprovalSpecialCondition asc : approvalSpecialConditions) {
            if (asc.getActive() && asc.getTypeId().equals(ApprovalSpecialConditions.SELECT_BUYER.id())) {
                if (map.containsKey("buyer")) {
                    com.later.procurement.App.company.employees.entity.Employee employee = employeeService.findById(Long.parseLong(map.get("buyer")));
                    if (employee == null) {
                        throw new ApiException(404, "Buyer not found");
                    }
                    purchaseRequest.setBuyerId(employee.getId());
                    purchaseRequest.setBuyerName(employee.getFullName());
                    purchaseRequest.setBuyerNameAr(employee.getFullNameAr());
                    purchaseRequest.setBuyerCompanyNumber(employee.getCompanyNumber());
                } else {
                    throw new ApiException(404, "Please select a buyer");
                }
            }
        }
        purchaseRequest = (PurchaseRequest) procurementApprovalService
                .approve(purchaseRequest, SystemCode.PURCHASE_REQUEST.systemCode(),
                        loginUser, map.getOrDefault("comment", null));
        purchaseRequestRepo.save(purchaseRequest);
        return ResponseText.APPROVED.text();
    }

    public String reject(Long id, HashMap<String, String> map, Employee loginUser) throws ApiException {
        PurchaseRequest purchaseRequest = findById(id, loginUser);
        purchaseRequest = (PurchaseRequest) procurementApprovalService
                .reject(purchaseRequest, SystemCode.PURCHASE_REQUEST.systemCode(),
                        loginUser, map.getOrDefault("comment", null));
        purchaseRequestRepo.save(purchaseRequest);
        return ResponseText.REJECTED.text();

    }


    public Boolean checkApproval(Long divisionId, Long disciplineId, Boolean isService) {
        return procurementApprovalService.checkPurchaseRequestApproval(divisionId, disciplineId, isService);
    }

    public List<EmployeeShortModel> listEmployees() {
        return employeeService.listShort();
    }

    public DocumentModel downloadAttachment(Long id, String attachmentName, Employee loginUser) throws ApiException {
        PurchaseRequest purchaseRequest = findById(id, loginUser);
        if (purchaseRequest.getDocuments().stream().anyMatch(a -> a.getFileName().equals(attachmentName))) {
            return documentService.downloadFileBase64(attachmentName, SystemCode.PURCHASE_REQUEST.systemCode());
        }
        throw new ApiException(404, "File not found");
    }

    public List<PurchaseRequestShortModel> listForBuyer(Long costCenterId, Employee loginUser) {
        return purchaseRequestRepo.listForBuyer(loginUser.getId(), costCenterId);
    }

    public PurchaseRequest findByIdForPurchaseOrder(Long id, Employee employee) throws ApiException {
        PurchaseRequest purchaseRequest = purchaseRequestRepo.findById(id).orElse(null);
        if (purchaseRequest == null || (purchaseRequest.getCreatedById().equals(employee.getId())
                && !purchaseRequest.getBuyerId().equals(employee.getId())
                && purchaseRequest.getApproval().stream()
                .noneMatch(a -> a.getApproverId().equals(employee.getId()))
        ) || !purchaseRequest.getStatus().equals(Status.APPROVED.code())) {
            throw new ApiException(404, "Purchase request not found");
        }
        purchaseRequest.setApproval(null);
        return purchaseRequest;
    }

    public void saveDetailsForPurchaseOrder(List<PurchaseRequestDetails> purchaseRequestDetails) {
        purchaseRequestDetailsRepo.saveAll(purchaseRequestDetails);
    }

}
