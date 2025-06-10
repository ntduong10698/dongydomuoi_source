package com.bkosft.uploadservice.service;

import com.bkosft.uploadservice.model.FileProperty;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.MovieHeaderBox;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    Logger logger = LogManager.getLogger(UploadServiceImpl.class);

    private final String uploadFolder = System.getProperty("catalina.base") + "/webapps/resources/micro-upload";
    //private final String uploadFolder = "F:\\upload";

    // private final String uploadFolder = "D:\\server\\apache-tomee-plus-8.0.2\\webapps\\resources\\micro-upload";

    @Override
    public void init() {
        File rootFolder = new File(uploadFolder);
        if (!rootFolder.exists())
            rootFolder.mkdirs();
    }

    @Override
    public String initDateFolder(String company) throws IOException {
        LocalDate now = LocalDate.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Path companyFolder = Paths.get(uploadFolder, company);
        if (!Files.exists(companyFolder))
            Files.createDirectory(companyFolder);
        Path folder = Paths.get(uploadFolder, company, date);
        if (!Files.exists(folder))
            Files.createDirectory(folder);
        return date;
    }

    @Override
    public Path initFileFolder(String company, String parent) throws IOException {
        Path parentPath = Paths.get(uploadFolder, company, parent);
        File[] listFiles = new File(parentPath.toAbsolutePath().toString()).listFiles();
        String folder = "";
        boolean check = true;
        while (check) {
            folder = RandomStringUtils.randomAlphanumeric(10);
            check = false;
            if (listFiles != null)
                for (File xx : listFiles) {
                    if (xx.getName().equals(folder))
                        check = true;
                }
        }
        Path folderPath = Paths.get(uploadFolder, company, parent, folder);
        if (!Files.exists(folderPath))
            Files.createDirectory(folderPath);
        return folderPath;
    }

    @Override
    public List<FileProperty> upload(String company, MultipartFile[] files) throws Exception {
        try {
            String today = initDateFolder(company);
            List<FileProperty> fileProperties = new ArrayList<>();
            for (MultipartFile file : files) {
                FileProperty fileProperty = new FileProperty();
                Path folder = initFileFolder(company, today);
                Files.copy(file.getInputStream(), folder.resolve(file.getOriginalFilename()));
                //set file property
                fileProperty.setName(file.getOriginalFilename());
                fileProperty.setUri(today + "/" + folder.toFile().getName() + "/" + file.getOriginalFilename());
                fileProperty.setSize(file.getSize());
                fileProperty.setType(file.getContentType());
                //get duration
                try {
                    if (fileProperty.getType().contains("mp4")) {
                        IsoFile isoFile = new IsoFile(uploadFolder + File.separator + company + File.separator + fileProperty.getUri());
                        MovieHeaderBox mhb = isoFile.getMovieBox().getMovieHeaderBox();
                        fileProperty.setDuration(mhb.getDuration() / mhb.getTimescale());
                    }
                } catch (Exception e) {
                    log.error("=>> duration error: ",e);
                    fileProperty.setDuration(0);
                }
                fileProperties.add(fileProperty);
                log.info("=======> upload file: "+fileProperty.getName());
                log.info("=====>  file size: "+fileProperty.getSize());
            }

            return fileProperties;
        } catch (Exception e) {
            logger.error("cant save file", e);
            throw e;
        }
    }

    @Override
    public boolean newLogo(String company, MultipartFile logo) throws Exception {
        try {
            Path logoFolder = Paths.get(uploadFolder, company, "logo");
            File folderLogo = new File(logoFolder.toString());
            if (!folderLogo.exists())
                Files.createDirectory(logoFolder);
            if (folderLogo.listFiles().length > 0)
                Files.copy(logo.getInputStream(), logoFolder.resolve("logo-" + company + ".png"), StandardCopyOption.REPLACE_EXISTING);
            else Files.copy(logo.getInputStream(), logoFolder.resolve("logo-" + company + ".png"));
            return true;
        } catch (Exception e) {
            logger.error("cant save file", e);
            throw e;
        }
    }

    @Override
    public boolean newRobot(String company, MultipartFile robo) throws Exception {
        try {
            Path configPath = Paths.get(uploadFolder, company);
            if (!Files.exists(configPath))
                Files.createDirectory(configPath);
            if (Files.exists(Paths.get(uploadFolder, company, "robot.txt")))
                Files.copy(robo.getInputStream(), configPath.resolve("robot.txt"), StandardCopyOption.REPLACE_EXISTING);
            else
                Files.copy(robo.getInputStream(), configPath.resolve("robot.txt"));
            return true;
        } catch (Exception e) {
            logger.error("cant save file", e);
            throw e;
        }
    }

    @Override
    public boolean newRobot(String company, String robotText) throws Exception {
        try {
            Path configPath = Paths.get(uploadFolder, company);
            if (!Files.exists(configPath))
                Files.createDirectory(configPath);
            if (Files.exists(Paths.get(uploadFolder, company, "robot.txt")))
                Files.copy(new ByteArrayInputStream(robotText.getBytes()), configPath.resolve("robot.txt"), StandardCopyOption.REPLACE_EXISTING);
            else
                Files.copy(new ByteArrayInputStream(robotText.getBytes()), configPath.resolve("robot.txt"));
            return true;
        } catch (Exception e) {
            logger.error("cant save file", e);
            throw e;
        }
    }

    @Override
    public String getRobot(String company) throws Exception {
        try {
            Path robotPath = Paths.get(uploadFolder, company, "robot.txt");
            return new String(Files.readAllBytes(robotPath), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("cant save file", e);
            throw e;
        }
    }

    @Override
    public boolean createFolder(String company, String folder) throws Exception {
        try {
            Path creator = Paths.get(uploadFolder, company, folder);
            if (Files.exists(creator))
                Files.createDirectory(creator);
            return true;
        } catch (Exception e) {
            logger.error("cant save file", e);
            throw e;
        }
    }

    @Override
    public Resource load(String path) {
        return null;
    }
}
