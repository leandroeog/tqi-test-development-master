package br.com.tqi.test.development.dto;

import br.com.tqi.test.development.entity.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sexo;

    @NotNull
    private AddressDTO addressDTO;

    public static ClientDTO toDTO(ClientEntity entity) {
        return ClientDTO.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome())
                .sexo(entity.getSexo())
                .addressDTO(AddressDTO.toDTO(entity.getAddress()))
                .build();
    }

}
