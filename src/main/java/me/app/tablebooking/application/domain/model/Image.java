package me.app.tablebooking.application.domain.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Image {
    private Long id;
    private final String url;
    private final Restaurant restaurant;

    public Image(String url, Restaurant restaurant){
        this.id = null;
        this.url = url;
        this.restaurant = restaurant;
    }
}
