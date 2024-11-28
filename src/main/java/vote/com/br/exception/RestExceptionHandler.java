package vote.com.br.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vote.com.br.model.error.ExceptionErrorDescriptionResponse;
import vote.com.br.model.error.ExceptionResponse;


@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    @ExceptionHandler(InvalidCPFException.class)
    public ResponseEntity<ExceptionResponse> invalidCpfException(BaseException exception) {
        return buildDefaultErrorResponse(exception);
    }

    @ExceptionHandler(InvalidVoteException.class)
    public ResponseEntity<ExceptionResponse> invalidVoteException(BaseException exception) {
        return buildDefaultErrorResponse(exception);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> ResourceNotFoundException(BaseException exception) {
        return buildDefaultErrorResponse(exception);
    }


    private ResponseEntity<ExceptionResponse> buildDefaultErrorResponse(BaseException exception) {
        var response = buildExceptionResponse(exception);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    private static ExceptionResponse buildExceptionResponse(BaseException exception) {
        return ExceptionResponse.builder()
                .path(exception.getClass().getCanonicalName())
                .status(exception.getHttpStatus().value())
                .printStack(false)
                .error(buildErrorDescriptionResponse(exception))
                .build();
    }

    private static ExceptionErrorDescriptionResponse buildErrorDescriptionResponse(BaseException exception) {
        return ExceptionErrorDescriptionResponse.builder()
                .code(exception.getErrorCode())
                .message(exception.getMessage())
                .fields(null)
                .build();
    }

}