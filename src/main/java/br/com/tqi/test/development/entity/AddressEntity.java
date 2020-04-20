package br.com.tqi.test.development.entity;

import br.com.tqi.test.development.dto.AddressDTO;
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
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "numero", length = 6)
    private String numero;

    @Column(name = "complemento", length = 30)
    private String complemento;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "pais", length = 50)
    private String pais;

    @Column(name = "cep", length = 6, nullable = false)
    private String cep;

    @OneToOne
    @JoinColumn(name = "id_client", columnDefinition = "id_client", referencedColumnName = "id", nullable = false)
    private ClientEntity client;

    public static AddressEntity toEntity(AddressDTO dto) {
        return AddressEntity.builder()
                .id(dto.getId())
                .endereco(dto.getEndereco())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .pais(dto.getPais())
                .cep(dto.getCep())
                .build();
    }

}
