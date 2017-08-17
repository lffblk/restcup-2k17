package com.lffblk.restcup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.lffblk.restcup.model.dto.LocationsDto;
import com.lffblk.restcup.model.dto.UsersDto;
import com.lffblk.restcup.model.dto.VisitsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by lffblk on 17.08.2017.
 */
@Service
public class FileService {

    private final static Logger LOG = LoggerFactory.getLogger(FileService.class);

    @Value("${data.path}")
    private String zipArchiveName;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersistenceService persistenceService;

    public Set<File> unzipArchive() {
        LOG.debug("unzipArchive {}", zipArchiveName);
        Set<File> unzippedFiles = Sets.newHashSet();
        File scmArchive = new File(zipArchiveName);
        String outputDirectory = scmArchive.getParent();
        LOG.debug("outputDirectory = {}", outputDirectory);
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(scmArchive));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while(ze != null) {
                String fileName = ze.getName();
                LOG.debug("fileName = {}", fileName);
                if (fileName.endsWith("cmt_packages.xml")) {
                    LOG.debug("Skip cmt_packages.xml");
                    ze = zis.getNextEntry();
                    continue;
                }
                File newFile = new File(outputDirectory + File.separator + fileName);
                unzippedFiles.add(newFile);
                LOG.debug("file unzip : {}", newFile);

                //create all non exists folders
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            return unzippedFiles;
        }
        catch (Exception e) {
            LOG.error("Exception: ", e);
            throw new RuntimeException(e);
        }
    }

    public void persistData(Set<File> files) throws Exception {
        LOG.debug("persistData, files = {}", files);

        Set<File> userFiles = Sets.newHashSet();
        Set<File> locationFiles = Sets.newHashSet();
        Set<File> visitFiles = Sets.newHashSet();

        for (File file : files) {
            if (file.getName().contains("users")) {
                userFiles.add(file);
            } else if (file.getName().contains("locations")) {
                locationFiles.add(file);
            } else if (file.getName().contains("visits")) {
                visitFiles.add(file);
            }
        }

        for (File locationFile : locationFiles) {
            LocationsDto locations = objectMapper.readValue(locationFile, LocationsDto.class);
            LOG.debug("locations = {}", locations);
            locations.getLocations().forEach(location -> persistenceService.save(null, location));
        }

        for (File userFile : userFiles) {
            UsersDto users = objectMapper.readValue(userFile, UsersDto.class);
            LOG.debug("users = {}", users);
            users.getUsers().forEach(user -> persistenceService.save(null, user));
        }

        for (File visitFile : visitFiles) {
            VisitsDto visits = objectMapper.readValue(visitFile, VisitsDto.class);
            LOG.debug("visits = {}", visits);
            visits.getVisits().forEach(visit -> persistenceService.save(null, visit));
        }
    }
}
