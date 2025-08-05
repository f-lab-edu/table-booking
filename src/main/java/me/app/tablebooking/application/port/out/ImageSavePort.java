package me.app.tablebooking.application.port.out;

import me.app.tablebooking.application.domain.model.Image;

import java.util.List;

public interface ImageSavePort {
    void saveAll(List<Image> images);
}
