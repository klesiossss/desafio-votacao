package vote.com.br.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String path;
    private Integer status;
    private boolean printStack;
    private ExceptionErrorDescriptionResponse error;

    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();

}