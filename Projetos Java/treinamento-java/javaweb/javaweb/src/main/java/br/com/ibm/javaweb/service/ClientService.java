package br.com.ibm.javaweb.service;

import br.com.ibm.javaweb.domain.converter.ClientConverter;
import br.com.ibm.javaweb.domain.dto.ClientDto;
import br.com.ibm.javaweb.entity.Client;
import br.com.ibm.javaweb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<ClientDto> get() {
        return repository.findAll().stream().map(e -> ClientConverter.toDto(Optional.ofNullable(e))).collect(Collectors.toList());
    }

    public ClientDto get(Long id) {
        return ClientConverter.toDto(repository.findById(id));
    }

    public List<ClientDto> getByName(String name) {
        return repository.findByName(name).stream().map(e -> ClientConverter.toDto(Optional.ofNullable(e))).collect(Collectors.toList());
    }

    public ClientDto save(ClientDto dto) {
        Client client = ClientConverter.toEntity(Optional.of(dto));
        return ClientConverter.toDto(Optional.ofNullable(repository.save(client)));
    }
}
