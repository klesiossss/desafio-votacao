package vote.com.br.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCodeEnum {

    CAD1001,
    CAD1002,
    CPF1003("CPF invalido", HttpStatus.NOT_FOUND),
    CPF1004;

    private String message;
    private HttpStatus httpStatus;

}
