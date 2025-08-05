package me.app.tablebooking.adapter.out.persistence;

import me.app.tablebooking.application.domain.model.Restaurant;
import me.app.tablebooking.application.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEntityMapper {

    public RestaurantEntity toEntity(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getId(),
                restaurant.getMember().getId(),
                restaurant.getCategory(),
                restaurant.getPriceRange(),
                restaurant.getContent(),
                restaurant.getLocation(),
                restaurant.getPhoneNumber(),
                restaurant.getCreatedAt(),
                restaurant.getModifiedAt()
        );
    }

    public Restaurant toDomain(RestaurantEntity entity, Member member) {
        return Restaurant.withId(
                entity.getId(),
                member,
                entity.getCategory(),
                entity.getPriceRange(),
                entity.getContent(),
                entity.getLocation(),
                entity.getPhoneNumber(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
