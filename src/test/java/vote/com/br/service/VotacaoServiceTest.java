package vote.com.br.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vote.com.br.exception.InvalidVoteException;
import vote.com.br.exception.ResourceNotFoundException;
import vote.com.br.model.entity.Pauta;
import vote.com.br.model.entity.SessaoVotacao;
import vote.com.br.model.entity.Voto;
import vote.com.br.repository.PautaRepository;
import vote.com.br.repository.SessaoVotacaoRepository;
import vote.com.br.repository.VotoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

class VotacaoServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotacaoService votacaoService;

    private static SessaoVotacao buildSessaoVotacao(Long pautaId, int duracao) {
        return SessaoVotacao.builder()
                .pautaId(pautaId)
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(duracao))
                .aberta(Boolean.TRUE)
                .build();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void abrirSessaoVotacao_pautaExists_opensSession() {
        Long pautaId = 1L;
        int duracao = 5;
        Pauta pauta = new Pauta();
        pauta.setDescricao("descricao");
        pauta.setId(1L);
        pauta.setTitulo("titulo");
        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        when(sessaoVotacaoRepository.save(any(SessaoVotacao.class))).thenReturn(buildSessaoVotacao(pautaId, duracao));

        SessaoVotacao sessaoVotacao = votacaoService.abrirSessaoVotacao(pautaId, duracao);

        assertNotNull(sessaoVotacao);
        assertEquals(pautaId, sessaoVotacao.getPautaId());
        verify(sessaoVotacaoRepository, times(1)).save(any(SessaoVotacao.class));
    }

    @Test
    void abrirSessaoVotacao_pautaNotFound_throwsException() {
        Long pautaId = 1L;
        int duracao = 5;
        when(pautaRepository.findById(pautaId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            votacaoService.abrirSessaoVotacao(pautaId, duracao);
        });

        assertEquals("Pauta não encontrada", exception.getMessage());
    }

    @Test
    void votar_validVote_savesVote() {
        Long pautaId = 1L;
        Long associadoId = 1L;
        String voto = "SIM";
        when(votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)).thenReturn(false);
        when(votoRepository.save(any(Voto.class))).thenReturn(buildVoto());
        Voto savedVoto = votacaoService.votar(pautaId, associadoId, voto);

        assertNotNull(savedVoto);
        assertEquals(pautaId, savedVoto.getPautaId());
        assertEquals(associadoId, savedVoto.getAssociadoId());
        assertEquals(voto, savedVoto.getVoto());
        verify(votoRepository, times(1)).save(any(Voto.class));
    }

    private static Voto buildVoto() {
        return Voto.builder()
                .voto("SIM")
                .associadoId(1L)
                .pautaId(1L)
                .id(1L)
                .build();
    }

    @Test
    void votar_associadoAlreadyVoted_throwsException() {
        Long pautaId = 1L;
        Long associadoId = 1L;
        String voto = "Sim";
        when(votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)).thenReturn(true);

        InvalidVoteException exception = assertThrows(InvalidVoteException.class, () -> {
            votacaoService.votar(pautaId, associadoId, voto);
        });

        assertEquals("Associado já votou nesta pauta", exception.getMessage());
    }

    @Test
    void votar_invalidVote_throwsException() {
        Long pautaId = 1L;
        Long associadoId = 1L;
        String voto = "NAO";
when(votoRepository.existsByPautaIdAndAssociadoId(pautaId,associadoId)).thenReturn(Boolean.TRUE);
        InvalidVoteException exception = assertThrows(InvalidVoteException.class, () -> {
            votacaoService.votar(pautaId, associadoId, voto);
        });

        assertEquals("Associado já votou nesta pauta", exception.getMessage());
    }
}