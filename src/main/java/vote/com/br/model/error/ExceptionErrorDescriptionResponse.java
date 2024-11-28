package vote.com.br.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vote.com.br.enums.ErrorCodeEnum;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionErrorDescriptionResponse {

    private ErrorCodeEnum code;
    private String message;
    private Map<String, List<String>> fields;

}