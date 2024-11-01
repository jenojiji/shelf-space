package com.jeno.shelf_space_system.service.file;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilestorageService {

    @Value("${application.file.upload.photos-output-path}")
    private final String fileUploadPath;

    public String saveFile(
            @NotNull MultipartFile sourceFile,
            @NotNull Integer userId) {
        final String fileUploadSubPath = "users" + File.separator + userId;

        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(
            @NotNull MultipartFile sourceFile,
            @NotNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            log.warn("File creation failed");
            return null;
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        final String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis()
                + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("file saved correctly");
            return targetFilePath;
        } catch (IOException e) {
            log.error("file not saved");
        }
        return null;
    }

    private String getFileExtension(String originalFilename) {
        int lastDotIndex = 0;
        if (originalFilename != null || originalFilename.isEmpty()) {
            lastDotIndex = originalFilename.lastIndexOf(".");
            if (lastDotIndex == -1) {
                return "";
            }
        }
        return originalFilename.substring(lastDotIndex + 1).toLowerCase();
    }
}
