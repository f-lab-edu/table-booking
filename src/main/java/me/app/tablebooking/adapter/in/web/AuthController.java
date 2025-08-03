package me.app.tablebooking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.adapter.in.web.request.LoginRequest;
import me.app.tablebooking.application.port.in.LoginCommand;
import me.app.tablebooking.application.port.in.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.login(new LoginCommand(request.getUsername(), request.getPassword())));
    }
}
