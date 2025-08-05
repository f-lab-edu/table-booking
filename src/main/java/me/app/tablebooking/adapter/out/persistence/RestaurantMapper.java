package me.app.tablebooking.adapter.out.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RestaurantMapper {

    @Insert("""
                INSERT INTO restaurant (member_id, category, price_range, content, location, phone_number, created_at, modified_at)
                VALUES (#{memberId}, #{category}, #{priceRange}, #{content}, #{location}, #{phoneNumber}, #{createdAt}, #{modifiedAt})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(RestaurantEntity restaurant);
}
