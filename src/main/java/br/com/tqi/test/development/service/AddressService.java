package br.com.tqi.test.development.service;

import br.com.tqi.test.development.client.CepClient;
import br.com.tqi.test.development.dto.AddressDTO;
import br.com.tqi.test.development.entity.AddressEntity;
import br.com.tqi.test.development.exception.BusinessException;
import br.com.tqi.test.development.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    private final CepClient cepClient;
    private final AddressRepository repository;

    @Autowired
    public AddressService(CepClient cepClient, AddressRepository repository) {

        this.cepClient = cepClient;
        this.repository = repository;

    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findByCep(String cep) {
        return cepClient.findByCep(cep);
    }

    @Transactional
    public void updateByClient(Long id, Long idClient, AddressDTO dto) throws BusinessException {

        AddressEntity oldAddress = repository.findById(id).orElseThrow(() -> new BusinessException("Address not found!"));

        validateCepClient(idClient, oldAddress.getClient().getId());

        AddressEntity newAddress = AddressEntity.toEntity(dto);

        newAddress.setId(oldAddress.getId());
        newAddress.setClient(oldAddress.getClient());

        repository.save(newAddress);

    }

    private void validateCepClient(Long newAdressClientId, Long oldAdressClientId) throws BusinessException {

        if (!newAdressClientId.equals(oldAdressClientId)) {
            throw new BusinessException("This address already belongs to another client!");
        }

    }

}
