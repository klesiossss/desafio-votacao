package vote.com.br.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import vote.com.br.enums.ErrorCodeEnum;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorCodeEnum errorCode;

    public BaseException(String message, HttpStatus httpStatus, ErrorCodeEnum errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
