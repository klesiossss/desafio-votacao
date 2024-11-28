package vote.com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vote.com.br.model.entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    public Long countByPautaIdAndVoto(Long pautaId, String voto);
    public Boolean existsByPautaIdAndAssociadoId(Long pautaId, Long associadoId);
}
