package me.app.tablebooking.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.app.tablebooking.application.domain.model.Image;
import me.app.tablebooking.application.domain.model.Restaurant;
import me.app.tablebooking.application.port.out.ImageSavePort;
import me.app.tablebooking.application.port.out.RestaurantRegisterPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RestaurantRegisterPersistentAdapter implements ImageSavePort, RestaurantRegisterPort {

    private final ImageMapper imageMapper;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
        restaurantMapper.insert(entity);

        return restaurantEntityMapper.toDomain(entity, restaurant.getMember());
    }

    @Override
    public void saveAll(List<Image> images) {
        List<ImageEntity> entities = images.stream()
                .map(image ->  restaurantEntityMapper.toEntity(image))
                .collect(Collectors.toList());

        imageMapper.insertAll(entities);
    }
}
