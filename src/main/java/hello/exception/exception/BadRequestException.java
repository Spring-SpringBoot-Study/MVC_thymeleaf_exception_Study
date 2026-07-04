package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ERROR 터지면 400 에러(BAD_REQUEST)가 나오게 함
// @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad") // messages.properties에 있는 메시지를 사용하는 것도 가능
public class BadRequestException extends RuntimeException {

}