package vote.com.br.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String titulo;
    private String descricao;

    public Pauta(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
