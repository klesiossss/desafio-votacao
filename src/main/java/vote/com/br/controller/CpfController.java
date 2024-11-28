package vote.com.br.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vote.com.br.service.CpfService;

@RestController
@RequestMapping("/cpf")
@RequiredArgsConstructor
public class CpfController {


    private final CpfService cpfService;

    @GetMapping("/generate")
    public ResponseEntity<String> generateCpf() {
        String cpfGerado = cpfService.generateCpf();
        return ResponseEntity.ok(cpfGerado);
    }

    // Endpoint para verificar se o CPF é válido e se pode votar
    @GetMapping("/{cpf}")
    public ResponseEntity<String> verificarCpf(@PathVariable String cpf) {
        String votoStatus = cpfService.canVote(cpf);
        return ResponseEntity.ok(votoStatus);
    }
}
