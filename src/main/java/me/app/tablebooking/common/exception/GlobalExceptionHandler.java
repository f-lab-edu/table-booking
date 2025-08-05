package me.app.tablebooking.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedUsernameException.class)
    public ResponseEntity<ErrorMsg> handleDuplicatedUsername(DuplicatedUsernameException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMsg(ex.getMessage(), ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorMsg> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorMsg(ex.getMessage(), ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorMsg> fileUploadException(FileUploadException ex) {
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
                .body(new ErrorMsg(ex.getMessage(), ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsg> handleGenericException(Exception ex) {
        log.error("서버 오류 발생", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMsg("알 수 없는 서버 오류가 발생했습니다.", ex.getClass().getSimpleName()));
    }
}
