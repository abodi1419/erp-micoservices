package com.later.itemservice.App.items.controller;

import com.later.itemservice.App.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/common")
@RequiredArgsConstructor
public class MSCommon {
    private final ItemService itemService;

//    @GetMapping("item/list")
//    public ResponseEntity getItems() {
//        return ok().body(
//                new ApiResponse<ItemModel>(true, 200, "Success", SERVICE_CALL)
//        );
//    }
}
