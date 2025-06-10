package com.bksoftwarevn.adminthuocdongy.uploadservice.service;

import com.bksoftwarevn.adminthuocdongy.uploadservice.model.FileProperty;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface UploadService {

    void init();

    String initDateFolder(String company) throws IOException;

    Path initFileFolder(String company, String parent) throws IOException;

    List<FileProperty> upload(String company, MultipartFile[] file) throws Exception;

    boolean newLogo(String company, MultipartFile logo) throws Exception;

    boolean newRobot(String company, MultipartFile robo) throws Exception;

    boolean newRobot(String company, String robotText) throws Exception;

    String getRobot(String company) throws Exception;

    boolean createFolder(String company, String folder) throws Exception;

    Resource load(String path);
}
