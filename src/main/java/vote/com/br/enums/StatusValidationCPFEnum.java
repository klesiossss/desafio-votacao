package vote.com.br.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum StatusValidationCPFEnum {
    VALID("VALID"),
    INVALID("INVALID");

    private final String status;

    @JsonCreator
    public static StatusValidationCPFEnum getName(String name) {
        return Arrays.stream(values())
                .filter(brand -> brand.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(INVALID);
    }
}
