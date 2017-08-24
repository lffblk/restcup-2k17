package com.lffblk.restcup;

import com.lffblk.restcup.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by lffblk on 14.08.2017.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.lffblk.restcup.repository")
public class Application implements ApplicationRunner {

    @Autowired
    private FileService fileService;

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            LOG.info("Filling db...");
            fileService.persistData(fileService.unzipArchive());
            LOG.info("Filling db DONE!");
        }
        catch (Exception e) {
            LOG.error("Exception: ", e);
            throw e;
        }
    }
}
