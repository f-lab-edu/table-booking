package me.app.tablebooking.application.domain.service;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.application.domain.model.Image;
import me.app.tablebooking.application.domain.model.Restaurant;
import me.app.tablebooking.application.port.in.RestaurantRegisterCommand;
import me.app.tablebooking.application.port.in.RestaurantRegisterUseCase;
import me.app.tablebooking.application.port.out.ImageSavePort;
import me.app.tablebooking.application.port.out.RestaurantRegisterPort;
import me.app.tablebooking.application.port.out.S3Port;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantRegisterService implements RestaurantRegisterUseCase {

    private final RestaurantRegisterPort restaurantRegisterPort;
    private final S3Port s3Port;
    private final ImageSavePort imageSavePort;

    public void create(RestaurantRegisterCommand command, List<MultipartFile> images){
        Restaurant restaurant = Restaurant.withoutId(
                command.member(),
                command.category(),
                command.priceRange(),
                command.content(),
                command.location(),
                command.phoneNumber(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Restaurant savedRestaurant = restaurantRegisterPort.save(restaurant);

        List<String> imageUrls = images.stream()
                .map(s3Port::upload)
                .collect(Collectors.toList());

        List<Image> image = imageUrls.stream()
                .map(url -> new Image(url, savedRestaurant))
                .collect(Collectors.toList());

        imageSavePort.saveAll(image);
    }
}
