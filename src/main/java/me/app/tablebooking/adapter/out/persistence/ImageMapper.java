package me.app.tablebooking.adapter.out.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    
    @Insert("INSERT INTO image (restaurant_id, url) VALUES (#{restaurantId}, #{url})")
    void insert(ImageEntity image);
}
