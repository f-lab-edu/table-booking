package me.app.tablebooking.adapter.out.persistence;

import lombok.*;

@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    private Long id;
    private String url;
    private Long restaurantId;
}
