package vote.com.br.exception;

import org.springframework.http.HttpStatus;
import vote.com.br.enums.ErrorCodeEnum;

public class InvalidVoteException extends BaseException {

    public InvalidVoteException(String message, HttpStatus httpStatus, ErrorCodeEnum errorCodeEnum) {
        super(message, httpStatus, errorCodeEnum);
    }

}
