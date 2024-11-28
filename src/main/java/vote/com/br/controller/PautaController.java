package vote.com.br.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vote.com.br.model.PautaRequest;
import vote.com.br.model.ResultadoVotacao;
import vote.com.br.model.SessaoRequest;
import vote.com.br.model.VotoRequest;
import vote.com.br.model.entity.Pauta;
import vote.com.br.model.entity.SessaoVotacao;
import vote.com.br.model.entity.Voto;
import vote.com.br.service.VotacaoService;

@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final VotacaoService votacaoService;


    @PostMapping("/{pautaId}/sessao")
    @Operation(summary = "criando uma sessão",
            description = "criando uma sessão de votação.")
    public ResponseEntity<SessaoVotacao> abrirSessao(@PathVariable Long pautaId, @RequestBody SessaoRequest request) {
        int duracao = (request.getDuracao() != null) ? request.getDuracao() : 1;
        var sessao = votacaoService.abrirSessaoVotacao(pautaId, duracao);
        return new ResponseEntity<>(sessao, HttpStatus.CREATED);
    }


    @PostMapping("/{id}/votar")
    @Operation(summary = "votação",
            description = "endpoint de realizar votação.")

    public ResponseEntity<Voto> votar(@PathVariable Long id, @RequestBody VotoRequest votoRequest) {
        var voto = votacaoService.votar(id, votoRequest.getAssociadoId(), votoRequest.getVoto().name());
        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/resultado")
    @Operation(summary = "obtendo resultado",
            description = "endpoint de obter resultado da votação.")
    public ResultadoVotacao obterResultado(@PathVariable Long id) {
        return votacaoService.contabilizarResultado(id);
    }


    @PostMapping("/cadastrar")
    @Operation(summary = "cadastrando pauta",
            description = "endpoint para cadastrar nova pauta.")
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody PautaRequest pautaRequest) {
        Pauta pautaSalva = votacaoService.cadastrarPauta(pautaRequest);
        return new ResponseEntity<>(pautaSalva, HttpStatus.CREATED);
    }



}
