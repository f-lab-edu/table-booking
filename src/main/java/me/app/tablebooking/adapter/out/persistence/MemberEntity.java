package me.app.tablebooking.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.app.tablebooking.application.domain.model.Type;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    private Long id;
    private String username;
    private String password;
    private Type type;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
