package me.app.tablebooking.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.app.tablebooking.application.domain.model.Category;
import me.app.tablebooking.application.domain.model.PriceRange;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RestaurantEntity {
    private Long id;
    private Long memberId;
    private Category category;
    private PriceRange priceRange;
    private String content;
    private String location;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
