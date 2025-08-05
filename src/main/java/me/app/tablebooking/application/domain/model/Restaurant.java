package me.app.tablebooking.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Restaurant {
    private final Long id;
    private final Member member;
    private final Category category;
    private final PriceRange priceRange;
    private final String content;
    private final String location;
    private final String phoneNumber;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static Restaurant withoutId(
            Member member,
            Category category,
            PriceRange priceRange,
            String content,
            String location,
            String phoneNumber,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt){
        return new Restaurant(null, member, category, priceRange, content, location, phoneNumber, createdAt, modifiedAt);
    }

    public static Restaurant withId(
            Long id,
            Member member,
            Category category,
            PriceRange priceRange,
            String content,
            String location,
            String phoneNumber,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt){
        return new Restaurant(id, member, category, priceRange, content, location, phoneNumber, createdAt, modifiedAt);
    }
}
