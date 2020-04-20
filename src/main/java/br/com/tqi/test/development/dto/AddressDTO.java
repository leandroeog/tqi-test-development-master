package br.com.tqi.test.development.dto;

import br.com.tqi.test.development.entity.AddressEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private final static String DEFAULT_COUNTRY = "Brasil";

    private Long id;

    @NotEmpty
    private String endereco;

    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty
    private String cidade;

    @NotEmpty
    private String estado;

    @Builder.Default
    private String pais = DEFAULT_COUNTRY;

    @NotEmpty
    private String cep;

    public static AddressDTO toDTO(AddressEntity addressEntity) {
        return AddressDTO.builder()
                .id(addressEntity.getId())
                .endereco(addressEntity.getEndereco())
                .numero(addressEntity.getNumero())
                .complemento(addressEntity.getComplemento())
                .bairro(addressEntity.getBairro())
                .cidade(addressEntity.getCidade())
                .estado(addressEntity.getEstado())
                .pais(addressEntity.getPais())
                .cep(addressEntity.getCep())
                .build();
    }

}
