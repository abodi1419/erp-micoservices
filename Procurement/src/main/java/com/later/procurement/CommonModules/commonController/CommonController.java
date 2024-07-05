package com.later.procurement.CommonModules.commonController;


import com.later.procurement.DocumentService.service.DocumentService;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.constants.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/common")
@RequiredArgsConstructor
public class CommonController {

    private final DocumentService documentService;

    @PostMapping("upload")
    public ResponseEntity upload(HttpServletRequest request) throws ApiException {
        String systemCode = request.getParameter("systemCode");
        if (systemCode == null) {
            throw new ApiException(400, "Missing system code");
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        if (multipartRequest.getFileMap().isEmpty()) {
            throw new ApiException(400, "Missing files");
        }
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : multipartRequest.getMultiFileMap().values().stream().flatMap(Collection::stream).toList()) {
            if (!file.isEmpty()) {
                try {
                    fileNames.add(documentService.uploadTempFile(file, systemCode));
                } catch (IOException e) {
                    throw new ApiException(400, "One or more files could not be uploaded");
                }
            }
        }
        return ResponseEntity.ok().body(
                new ApiResponse(true, 200, "Success", fileNames)
        );
    }

}
