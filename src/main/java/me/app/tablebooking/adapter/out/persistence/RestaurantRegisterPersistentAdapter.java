package me.app.tablebooking.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.application.domain.model.Image;
import me.app.tablebooking.application.domain.model.Restaurant;
import me.app.tablebooking.application.port.out.ImageSavePort;
import me.app.tablebooking.application.port.out.RestaurantRegisterPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
        images.forEach(image -> {
            ImageEntity entity = new ImageEntity();
            entity.setUrl(image.getUrl());
            entity.setRestaurantId(image.getRestaurant().getId());
            imageMapper.insert(entity);
        });
    }

}
