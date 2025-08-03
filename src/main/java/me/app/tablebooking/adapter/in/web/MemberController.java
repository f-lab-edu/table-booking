package me.app.tablebooking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.adapter.in.web.request.SignUpRequest;
import me.app.tablebooking.application.domain.model.Type;
import me.app.tablebooking.application.port.in.SignUpCommand;
import me.app.tablebooking.application.port.in.SignUpUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
        SignUpCommand command = new SignUpCommand(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getPhoneNumber(),
                Type.valueOf(request.getType().toUpperCase())
        );
        signUpUseCase.signUp(command);
        return ResponseEntity.ok("회원 가입이 완료되었습니다. ");
    }

}
