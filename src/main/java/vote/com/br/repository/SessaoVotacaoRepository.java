package vote.com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vote.com.br.model.entity.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
}
