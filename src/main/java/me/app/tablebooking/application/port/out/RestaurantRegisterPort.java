package me.app.tablebooking.application.port.out;

import me.app.tablebooking.application.domain.model.Restaurant;

public interface RestaurantRegisterPort {
    Restaurant save(Restaurant restaurant);
}
