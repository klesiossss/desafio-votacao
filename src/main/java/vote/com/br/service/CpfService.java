package vote.com.br.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vote.com.br.enums.ErrorCodeEnum;
import vote.com.br.exception.InvalidCPFException;
import vote.com.br.util.GeraCpfCnpj;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CpfService {

    private final GeraCpfCnpj cpfGenerator;

    public String generateCpf() {
        return cpfGenerator.cpf(false);
    }

    public String canVote(String cpf) {
        validate(cpf);
        Random random = new Random();
        return random.nextBoolean() ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE";
    }

    public Boolean validate(String cpf) {
        if (!cpfGenerator.isCPF(cpf)) {
            throw new InvalidCPFException("Invalid CPF", HttpStatus.NOT_FOUND, ErrorCodeEnum.CAD1002);
        }
        return true;
    }
}
