package vote.com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vote.com.br.model.entity.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
