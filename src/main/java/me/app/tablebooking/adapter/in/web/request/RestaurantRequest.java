package me.app.tablebooking.adapter.in.web.request;

import lombok.Getter;

@Getter
public class RestaurantRequest {
    private String category;
    private String priceRange;
    private String content;
    private String location;
    private String phoneNumber;
}
