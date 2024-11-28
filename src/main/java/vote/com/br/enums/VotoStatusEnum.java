package vote.com.br.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum VotoStatusEnum {
    SIM(),
    NAO();

    @JsonCreator
    public static VotoStatusEnum getName(String name) {
        return Arrays.stream(values())
                .filter(brand -> brand.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(NAO);
    }
}
