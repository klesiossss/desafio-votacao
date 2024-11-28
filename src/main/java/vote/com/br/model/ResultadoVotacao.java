package vote.com.br.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoVotacao {

    private Long votosSim;
    private Long votosNao;
    private String resultado;

    public ResultadoVotacao(Long votosSim, Long votosNao) {
        this.votosSim = votosSim;
        this.votosNao = votosNao;
        this.resultado = (votosSim > votosNao) ? "Aprovada" : "Rejeitada";
    }

}
