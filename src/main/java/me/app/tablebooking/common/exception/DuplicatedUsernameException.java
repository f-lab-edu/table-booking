package me.app.tablebooking.common.exception;

public class DuplicatedUsernameException extends RuntimeException {

    public DuplicatedUsernameException() {
        super("이미 사용 중인 아이디입니다.");
    }

    public DuplicatedUsernameException(String message) {
        super(message);
    }
}
