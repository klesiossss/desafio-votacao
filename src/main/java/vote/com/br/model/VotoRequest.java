package vote.com.br.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vote.com.br.enums.VotoStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoRequest {

    private Long associadoId;
    private VotoStatusEnum voto; // "Sim" ou "Nao"
}