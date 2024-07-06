package com.later.supplierservice.DocumentService.service;



import com.later.supplierservice.Exception.ApiException;
import com.later.supplierservice.constants.SystemCode;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class DocumentService {
    @Value("${storage.path}")
    private String documentPath;
    @Value("${storage.temp}")
    private String documentTempPath;


    public String uploadTempFile(MultipartFile file, String systemCode) throws IOException, ApiException {
        SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
        if (systemCodeObject == null) {
            throw new RuntimeException("System code not found");
        }
        String filePath = documentTempPath + File.separator + systemCodeObject.moduleGroup() +
                File.separator + systemCodeObject.systemCode();

        File dir = new File(filePath);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new ApiException(500, "File could not be deleted");
            }
            ;
        }
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        file.transferTo(Path.of(dir.getAbsolutePath() + File.separator + fileName));
        return fileName;
    }
}
