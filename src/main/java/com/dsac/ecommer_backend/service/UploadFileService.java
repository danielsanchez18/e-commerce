package com.dsac.ecommer_backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UploadFileService {

    public Resource load(String filename, String folder) throws MalformedURLException;

    public String copy(MultipartFile filename, String folder) throws IOException;

    public boolean delete(String filename, String folder);
}
