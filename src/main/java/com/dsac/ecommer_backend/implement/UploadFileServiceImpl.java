package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Value("${uploads_folder.path}")
    private String UPLOADS_FOLDER_PATH;

    @Value("${user_folder.path}")
    private String USER_FOLDER_PATH;

    @Value("${product_folder.path}")
    private String PRODUCT_FOLDER_PATH;

    @Override
    public Resource load(String filename, String folder) throws MalformedURLException {
        Path path = getPath(filename, folder);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error in path: " + path.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file, String folder) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename, folder);
        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename, String folder) {
        Path rootPath = getPath(filename, folder);
        File file = rootPath.toFile();

        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }

        return false;
    }

    private Path getPath(String filename, String folder) {
        String basePath;
        switch (folder) {
            case "user":
                basePath = USER_FOLDER_PATH;
                break;
            case "product":
                basePath = PRODUCT_FOLDER_PATH;
                break;
            default:
                basePath = UPLOADS_FOLDER_PATH;
        }
        return Paths.get(basePath).resolve(filename).toAbsolutePath();
    }
}
