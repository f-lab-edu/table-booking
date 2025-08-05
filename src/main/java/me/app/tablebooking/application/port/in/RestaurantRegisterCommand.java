package me.app.tablebooking.application.port.in;

import jakarta.validation.constraints.NotBlank;
import me.app.tablebooking.application.domain.model.Category;
import me.app.tablebooking.application.domain.model.Member;
import me.app.tablebooking.application.domain.model.PriceRange;

public record RestaurantRegisterCommand(
        @NotBlank Member member,
    @NotBlank Category category,
    @NotBlank PriceRange priceRange,
    @NotBlank String content,
    @NotBlank String location,
    @NotBlank String phoneNumber
)
{}
