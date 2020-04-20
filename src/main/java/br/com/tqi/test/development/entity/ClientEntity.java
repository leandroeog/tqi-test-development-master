package br.com.tqi.test.development.entity;

import br.com.tqi.test.development.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "sexo", length = 9, nullable = false)
    private String sexo;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    private AddressEntity address;

    public static ClientEntity toEntity(ClientDTO clientDTO) {
        return ClientEntity.builder()
                .id(clientDTO.getId())
                .cpf(clientDTO.getCpf())
                .nome(clientDTO.getNome())
                .sexo(clientDTO.getSexo())
                .build();
    }

}
