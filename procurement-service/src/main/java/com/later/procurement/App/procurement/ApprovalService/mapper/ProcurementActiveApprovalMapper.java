package com.later.procurement.App.procurement.ApprovalService.mapper;

import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementActiveApproval;
import com.later.procurement.App.procurement.ApprovalService.model.ProcurementActiveApprovalDto;

public class ProcurementActiveApprovalMapper {


    public static ProcurementActiveApprovalDto toDto(ProcurementActiveApproval procurementActiveApproval) {
        if (procurementActiveApproval == null) {
            return null;
        }
        return new ProcurementActiveApprovalDto(procurementActiveApproval.getApproverId(),
                procurementActiveApproval.getApproverName(), procurementActiveApproval.getApproverNameAr(),
                procurementActiveApproval.getApproverCompanyNumber(), procurementActiveApproval.getStatus());
    }
}
