package me.app.tablebooking.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.application.port.out.S3Port;
import me.app.tablebooking.common.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.Instant;
import java.util.Random;

@Transactional
@Service
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class S3PersistenceAdapter implements S3Port {
    private final S3Client s3Client;

    @Value("${r2.url}")
    private String url;

    @Value("${r2.bucket}")
    private String bucket;

    public String upload(MultipartFile image) {
        try {
            String originalFilename = image.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String timestamp = String.valueOf(Instant.now().toEpochMilli());
            int randomNum = new Random().nextInt(100000);
            String fileKey = timestamp + "_" + randomNum + extension;


            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileKey)
                    .contentType(image.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(image.getInputStream(), image.getSize()));

            String fileUrl = url + fileKey;
            return fileUrl;

        } catch (Exception e) {
            throw new FileUploadException();
        }
    }
}