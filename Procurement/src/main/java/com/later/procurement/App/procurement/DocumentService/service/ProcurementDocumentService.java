package com.later.procurement.App.procurement.DocumentService.service;



import com.later.procurement.App.procurement.DocumentService.entity.ProcurementDocument;
import com.later.procurement.App.procurement.DocumentService.repository.ProcurementDocumentRepo;
import com.later.procurement.CommonModules.models.DocumentModel;
import com.later.procurement.Exception.ApiException;
import com.later.procurement.constants.SystemCode;
import com.later.procurement.App.procurement.interfaces.HasAttachment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ProcurementDocumentService {
    private final ProcurementDocumentRepo documentRepo;
    @Value("${storage.path}/proc/")
    private String documentPath;
    @Value("${storage.temp}/proc/")
    private String documentTempPath;

    public ProcurementDocumentService(ProcurementDocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public String uploadFile(MultipartFile file, String systemCode) throws IOException {
        SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
        if (systemCodeObject == null) {
            throw new RuntimeException("System code not found");
        }
        String filePath = documentPath + File.separator + systemCodeObject.systemCode();

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getName());
        file.transferTo(Path.of(dir.getAbsolutePath() + File.separator + fileName));
        return fileName;
    }


    public DocumentModel downloadFileBase64(String fileName, String systemCode) throws ApiException {
        try {
            SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
            if (systemCodeObject == null) {
                throw new RuntimeException("System code not found");
            }
            String filePath = documentPath + File.separator + systemCodeObject.systemCode() + File.separator + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                throw new RuntimeException("File not found");
            }
            DocumentModel documentModel = new DocumentModel();
            documentModel.setType(FilenameUtils.getExtension(fileName).replace(".", ""));
            documentModel.setName(fileName);
            String base64 = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
            documentModel.setBase64(base64);
            return documentModel;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(404, "File could not be downloaded");
        }
    }

    public DocumentModel downloadTempFileBase64(String fileName, String systemCode) throws IOException {
        SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
        if (systemCodeObject == null) {
            throw new RuntimeException("System code not found");
        }
        String filePath = documentTempPath + File.separator + systemCodeObject.systemCode() + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }
        DocumentModel documentModel = new DocumentModel();
        documentModel.setType(FilenameUtils.getExtension(fileName).replace(".", ""));
        documentModel.setName(fileName);
        String base64 = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
        documentModel.setBase64(base64);
        return documentModel;
    }

    public List<ProcurementDocument> uploadFromTemp(List<String> fileNames, String systemCode) throws ApiException {
        List<ProcurementDocument> documents = new ArrayList<>();
        for (String fileName : fileNames) {
            SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
            if (systemCodeObject == null) {
                throw new ApiException(404, "System code not found");
            }
            String filePath = documentPath + File.separator + systemCodeObject.systemCode();

            String tempPath = documentTempPath + File.separator + systemCodeObject.systemCode();

            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                Files.move(Path.of(tempPath, File.separator + fileName), Path.of(filePath, File.separator, fileName));
                ProcurementDocument document = new ProcurementDocument();
                document.setAttachmentName(fileName);
                document.setFileName(fileName);
                document.setFileType(FilenameUtils.getExtension(fileName).replace(".", ""));
                document.setSystemCode(systemCode);
                documents.add(document);
            } catch (IOException ioException) {
                throw new ApiException(404, "File could not be uploaded");
            }
        }
        return documents;

    }

    public List<ProcurementDocument> uploadFromTemp(String fileName, String systemCode) throws ApiException {
        List<ProcurementDocument> documents = new ArrayList<>();
        SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
        if (systemCodeObject == null) {
            throw new ApiException(404, "System code not found");
        }
        String filePath = documentPath + File.separator + systemCodeObject.systemCode();

        String tempPath = documentTempPath + File.separator + systemCodeObject.systemCode();

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            Files.move(Path.of(tempPath, File.separator + fileName), Path.of(filePath, File.separator, fileName));
            ProcurementDocument document = new ProcurementDocument();
            document.setAttachmentName(fileName);
            document.setFileName(fileName);
            document.setFileType(FilenameUtils.getExtension(fileName).replace(".", ""));
            document.setSystemCode(systemCode);
            documents.add(document);
        } catch (IOException ioException) {
            throw new ApiException(404, "File could not be uploaded");
        }

        return documents;

    }

    public List<ProcurementDocument> uploadFromTemp(String fileName, String systemCode, String attachmentType) throws ApiException {
        List<ProcurementDocument> documents = new ArrayList<>();
        SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
        if (systemCodeObject == null) {
            throw new ApiException(404, "System code not found");
        }
        String filePath = documentPath + File.separator + systemCodeObject.systemCode();

        String tempPath = documentTempPath + File.separator + systemCodeObject.systemCode();

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            Files.move(Path.of(tempPath, File.separator + fileName), Path.of(filePath, File.separator, fileName));
            ProcurementDocument document = new ProcurementDocument();
            document.setAttachmentName(fileName);
            document.setAttachmentType(attachmentType);
            document.setFileName(fileName);
            document.setFileType(FilenameUtils.getExtension(fileName).replace(".", ""));
            document.setSystemCode(systemCode);
            documents.add(document);
        } catch (IOException ioException) {
            throw new ApiException(404, "File could not be uploaded");
        }

        return documents;

    }

    public List<ProcurementDocument> uploadFromTemp(List<String> fileNames, String systemCode, String attachmentType) throws ApiException {
        List<ProcurementDocument> documents = new ArrayList<>();
        for (String fileName : fileNames) {
            SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
            if (systemCodeObject == null) {
                throw new ApiException(404, "System code not found");
            }
            String filePath = documentPath + File.separator + systemCodeObject.systemCode();

            String tempPath = documentTempPath + File.separator + systemCodeObject.systemCode();

            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                Files.move(Path.of(tempPath, File.separator + fileName), Path.of(filePath, File.separator, fileName));
                ProcurementDocument document = new ProcurementDocument();
                document.setAttachmentName(fileName);
                document.setAttachmentType(attachmentType);
                document.setFileName(fileName);
                document.setFileType(FilenameUtils.getExtension(fileName).replace(".", ""));
                document.setSystemCode(systemCode);
                documents.add(document);
            } catch (IOException ioException) {
                throw new ApiException(404, "File could not be uploaded");
            }
        }
        return documents;

    }

    public String uploadTempFile(MultipartFile file, String systemCode) throws IOException, ApiException {
        SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
        if (systemCodeObject == null) {
            throw new RuntimeException("System code not found");
        }
        String filePath = documentTempPath + File.separator + systemCodeObject.systemCode();

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

    public void delete(List<ProcurementDocument> documents, String systemCode) {
        for (ProcurementDocument document : documents) {
            SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
            String filePath = documentPath + File.separator + systemCodeObject.systemCode() + File.separator + document.getFileName();
            File file = new File(filePath);
            if (file.exists()) {
                String deletedPath = documentPath + File.separator + "deleted" + File.separator + systemCodeObject.moduleGroup() +
                        File.separator + systemCodeObject.systemCode();
                File deletedDir = new File(deletedPath);
                if (!deletedDir.exists()) {
                    deletedDir.mkdirs();
                }
                try {
                    Files.move(Path.of(file.getAbsolutePath()), Path.of(deletedDir.getAbsolutePath(), File.separator,
                            document.getFileName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void retrieveDeleted(List<ProcurementDocument> documents, String systemCode) throws ApiException {
        for (ProcurementDocument document : documents) {
            SystemCode systemCodeObject = SystemCode.getBySystemCode(systemCode);
            String restorePath = documentPath + File.separator + systemCodeObject.systemCode();
            String filePath = documentPath + File.separator + "deleted" + File.separator + systemCodeObject.systemCode() + File.separator + document.getFileName();
            File file = new File(filePath);
            if (file.exists()) {
                try {
                    Files.move(Path.of(file.getAbsolutePath()), Path.of(restorePath, File.separator, document.getFileName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new ApiException(404, "Deleted file not found");
            }
        }
    }

    public void saveDocument(List<ProcurementDocument> documents, HasAttachment hasAttachment, String systemPart, Boolean overwrite, String systemCode) {
        if (overwrite) {
            List<ProcurementDocument> documentsToBeDeleted = documentRepo.findAllBySystemCodeAndSystemPartAndRefId(systemCode, systemPart, hasAttachment.getId());
            delete(documentsToBeDeleted, systemCode);
            documentRepo.deleteAll(documentsToBeDeleted);
        }
        for (ProcurementDocument document : documents) {
            document.setSystemPart(systemPart);
            document.setSerial(documentRepo.getCurrentSerial(document.getSystemCode(), hasAttachment.getId(), document.getSystemPart()));
            document.setRefId(hasAttachment.getId());
            documentRepo.save(document);
        }

    }

    public void saveDocument(List<ProcurementDocument> documents, HasAttachment hasAttachment, Boolean overwrite, String systemCode) {
        if (overwrite) {
            List<ProcurementDocument> documentsToBeDeleted = documentRepo.findAllBySystemCodeAndSystemPartAndRefId(systemCode, "master", hasAttachment.getId());
            delete(documentsToBeDeleted, systemCode);
            documentRepo.deleteAll(documentsToBeDeleted);
        }
        for (ProcurementDocument document : documents) {
            document.setSystemPart("master");
            document.setSerial(documentRepo.getCurrentSerial(document.getSystemCode(),
                    hasAttachment.getId(), document.getSystemPart()));
            document.setRefId(hasAttachment.getId());
            documentRepo.save(document);
        }

    }


//    public void savePrintout(List<ProcurementDocument> documents, HasPrintout hasPrintout) {
//        for (Document document : documents) {
//            document.setSystemPart("master");
//            document.setSerial(documentRepo.getCurrentSerial(document.getSystemCode(), hasPrintout.getId(), document.getSystemPart()));
//            document.setRefId(hasPrintout.getId());
//            documentRepo.save(document);
//        }
//
//    }
}
