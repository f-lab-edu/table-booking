package me.app.tablebooking.adapter.out.persistence;

import me.app.tablebooking.application.domain.model.Image;
import me.app.tablebooking.application.domain.model.Restaurant;
import me.app.tablebooking.application.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEntityMapper {

    public RestaurantEntity toEntity(Restaurant restaurant) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setMemberId(restaurant.getMember().getId());
        entity.setCategory(restaurant.getCategory());
        entity.setPriceRange(restaurant.getPriceRange());
        entity.setContent(restaurant.getContent());
        entity.setLocation(restaurant.getLocation());
        entity.setPhoneNumber(restaurant.getPhoneNumber());
        entity.setCreatedAt(restaurant.getCreatedAt());
        entity.setModifiedAt(restaurant.getModifiedAt());
        return entity;
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

    public ImageEntity toEntity(Image image){
        return new ImageEntity(
                null, image.getUrl(), image.getRestaurant().getId()
        );
    }
}
