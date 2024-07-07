package com.later.commonservice.CommonModules.deliveryLocation.controller;

import com.later.commonservice.CommonModules.deliveryLocation.service.DeliveryLocationService;
import com.later.commonservice.constants.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/common/delivery-locations")
@RequiredArgsConstructor
public class DeliveryLocationController {
    private final DeliveryLocationService deliveryLocationService;

    @GetMapping("list")
    public ResponseEntity GET(@RequestParam(required = false) Long id) {

        if (id != null) {
            return ok().body(
                    new ApiResponse(true, 200, "Success", deliveryLocationService.findByIdOrElseNull(id))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", deliveryLocationService.findAll())
        );
    }

    @PostMapping("list")
    public ResponseEntity getListByIds(@RequestBody(required = false)List<Long> ids) {

        if(ids!=null&&!ids.isEmpty()){
            return ok().body(
                    new ApiResponse(true, 200, "Success", deliveryLocationService.findAllById(ids))
            );
        }
        return ok().body(
                new ApiResponse(true, 200, "Success", List.of())
        );
    }
}
