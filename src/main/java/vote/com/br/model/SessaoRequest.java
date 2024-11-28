package vote.com.br.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class SessaoRequest {

    private Long pautaId;
    private LocalDateTime dataAbertura;
    private Integer duracao;

    public SessaoRequest(Long pautaId, LocalDateTime dataAbertura, Integer duracao) {
        this.pautaId = pautaId;
        this.dataAbertura = dataAbertura;
        this.duracao = duracao;
    }
}
