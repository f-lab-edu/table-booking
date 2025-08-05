package me.app.tablebooking.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantRegisterUseCase {
    void create(RestaurantRegisterCommand command, List<MultipartFile> photos);
}
