package me.app.tablebooking.adapter.in.web.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String type;
}
