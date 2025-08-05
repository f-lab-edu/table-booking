package me.app.tablebooking.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface S3Port {
    String upload(MultipartFile image);
}
