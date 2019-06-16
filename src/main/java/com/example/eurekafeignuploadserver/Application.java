package com.example.eurekafeignuploadserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.shape.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Application {

  @RestController
  public class UploadController {

    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
      try {
        Files.copy(file.getInputStream(), Paths.get("down.jpg"), StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return file.getName();
    }

  }
  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }
}