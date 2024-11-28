package vote.com.br.exception;

import org.springframework.http.HttpStatus;
import vote.com.br.enums.ErrorCodeEnum;

public class InvalidCPFException extends BaseException {

    public InvalidCPFException(String message, HttpStatus httpStatus, ErrorCodeEnum errorCodeEnum) {
        super(message, httpStatus, errorCodeEnum);
    }

}