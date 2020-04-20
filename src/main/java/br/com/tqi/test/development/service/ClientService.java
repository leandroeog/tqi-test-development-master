package br.com.tqi.test.development.service;

import br.com.tqi.test.development.dto.AddressDTO;
import br.com.tqi.test.development.dto.ClientDTO;
import br.com.tqi.test.development.entity.AddressEntity;
import br.com.tqi.test.development.entity.ClientEntity;
import br.com.tqi.test.development.exception.BusinessException;
import br.com.tqi.test.development.repository.AddressRepository;
import br.com.tqi.test.development.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository repository;

    private AddressRepository addressRepository;

    @Autowired
    public ClientService(ClientRepository repository, AddressRepository addressRepository) {

        this.repository = repository;

        this.addressRepository = addressRepository;

    }

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {

        List<ClientDTO> dtos = new ArrayList<>();

        repository.findAll().forEach(clientEntity -> dtos.add(ClientDTO.toDTO(clientEntity)));

        return dtos;

    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) throws BusinessException {
        return ClientDTO.toDTO(repository.findById(id).orElseThrow(() -> new BusinessException("Client not found!")));
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(ClientDTO dto) {
        saveAddress(dto.getAddressDTO(), repository.save(ClientEntity.toEntity(dto)));
    }

    private void saveAddress(AddressDTO addressDTO, ClientEntity client) {

        AddressEntity addressEntity = AddressEntity.toEntity(addressDTO);

        addressEntity.setClient(client);

        addressRepository.save(addressEntity);

    }

}
