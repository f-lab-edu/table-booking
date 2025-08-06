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

        //데이터베이스 격리 수준이 REPEATABLE READ여서 restaurant 객체를 데이터베이스에 저장 후 나오는 PK를 Image에서 활용하지 못한다.
        //New transaction method가 execute되기 이전에 커넥션 풀의 커넥션이 바닥나면 프로세스의 모든 쓰레드가 block되고, 에러 전파가 안되기 때문에
        //SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED; 로 격리 수준을 변경하였다.
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
