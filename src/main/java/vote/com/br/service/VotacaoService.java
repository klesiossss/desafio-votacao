package vote.com.br.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vote.com.br.enums.ErrorCodeEnum;
import vote.com.br.exception.InvalidVoteException;
import vote.com.br.exception.ResourceNotFoundException;
import vote.com.br.model.PautaRequest;
import vote.com.br.model.entity.Pauta;
import vote.com.br.model.ResultadoVotacao;
import vote.com.br.model.entity.SessaoVotacao;
import vote.com.br.model.entity.Voto;
import vote.com.br.repository.PautaRepository;
import vote.com.br.repository.SessaoVotacaoRepository;
import vote.com.br.repository.VotoRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final VotoRepository votoRepository;


    public SessaoVotacao abrirSessaoVotacao(Long pautaId, int duracao) {
        pautaRepository.findById(pautaId).orElseThrow(() -> new ResourceNotFoundException("Pauta não encontrada", HttpStatus.NOT_FOUND, ErrorCodeEnum.CAD1001 ));
        return sessaoVotacaoRepository.save(buildSessaoVotacao(pautaId, duracao));
    }


    public Voto votar(Long pautaId, Long associadoId, String voto) {
        //checa se associado já votou na pauta
        if (votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)) {
            throw new InvalidVoteException("Associado já votou nesta pauta", HttpStatus.BAD_REQUEST, ErrorCodeEnum.CAD1002);
        }
        return votoRepository.save(buildEntity(pautaId, associadoId, voto));
    }


    public ResultadoVotacao contabilizarResultado(Long pautaId) {
        Long votosSim = votoRepository.countByPautaIdAndVoto(pautaId, "SIM");
        Long votosNao = votoRepository.countByPautaIdAndVoto(pautaId, "NAO");
        return new ResultadoVotacao(votosSim, votosNao);
    }


    public Pauta cadastrarPauta(PautaRequest pautaRequest) {
        Pauta pauta = new Pauta(pautaRequest.getTitulo(), pautaRequest.getDescricao());
        return pautaRepository.save(pauta);
    }


    private static Voto buildEntity(Long pautaId, Long associadoId, String voto) {
        return Voto.builder()
                .pautaId(pautaId)
                .associadoId(associadoId)
                .voto(voto)
                .build();
    }

    private static SessaoVotacao buildSessaoVotacao(Long pautaId, int duracao) {
        return SessaoVotacao.builder()
                .pautaId(pautaId)
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(duracao))
                .aberta(Boolean.TRUE)
                .build();
    }


}

