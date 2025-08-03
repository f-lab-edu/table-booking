package me.app.tablebooking.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Member {
    private Long id;
    private String username;
    private String password;
    private Type type;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
