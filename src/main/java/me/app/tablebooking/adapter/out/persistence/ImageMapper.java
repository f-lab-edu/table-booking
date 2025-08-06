package me.app.tablebooking.adapter.out.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ImageMapper {

    //Mybatis의 Dynamic SQL -> batch insert. DB와의 커넥션 형성을 한 번만 하면 되어서 네트워크 오버헤드 감소 -> 실행 속도 감소
    @Insert("""
            <script>
            INSERT INTO image (restaurant_id, url) VALUES
            <foreach collection="images" item="image" separator=",">
                (#{image.restaurantId}, #{image.url})
            </foreach>
            </script>
            """)
    // @Transactional(propagation = Propagation.REQUIRES_NEW) 만약 이렇게 있다면 RestaurantRegisterService.java에서
    //restaurant_id=A에 배타락을 건 상태에서 ImageMapper.insertAll()이 외래키 체크를 위해 restaurant_id=A 에 공유락을 걸려고 대기 -> 타임아웃 에러
    void insertAll(@Param("images") List<ImageEntity> images);
}
