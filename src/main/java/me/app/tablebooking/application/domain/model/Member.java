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
    private MemberRole memberRole;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Member withoutId(
                                    String username,
                                    String password,
                                    MemberRole memberRole,
                                    String name,
                                    String phoneNumber,
                                    LocalDateTime createdAt,
                                    LocalDateTime modifiedAt){
        return new Member(null, username, password, memberRole, name, phoneNumber, createdAt, modifiedAt);
    }

}
