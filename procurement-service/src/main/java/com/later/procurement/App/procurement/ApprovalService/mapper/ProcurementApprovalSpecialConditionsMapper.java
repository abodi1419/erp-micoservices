package com.later.procurement.App.procurement.ApprovalService.mapper;

import com.later.procurement.App.procurement.ApprovalService.entity.ProcurementApprovalSpecialCondition;
import com.later.procurement.App.procurement.ApprovalService.model.ProcurementApprovalSpecialConditionDto;

public class ProcurementApprovalSpecialConditionsMapper {
    public static ProcurementApprovalSpecialConditionDto toDto(ProcurementApprovalSpecialCondition procurementApprovalSpecialCondition) {
        if (procurementApprovalSpecialCondition == null) {
            return null;
        }
        return new ProcurementApprovalSpecialConditionDto(procurementApprovalSpecialCondition.getType(),
                procurementApprovalSpecialCondition.getTypeAr(), procurementApprovalSpecialCondition.getTypeId(),
                procurementApprovalSpecialCondition.getTypeDesc(), procurementApprovalSpecialCondition.getTypeDescAr(),
                procurementApprovalSpecialCondition.getStatus(), procurementApprovalSpecialCondition.getActive());
    }
}
