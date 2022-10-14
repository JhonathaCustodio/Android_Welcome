package br.com.ibm.javaweb.domain.converter;

import br.com.ibm.javaweb.domain.dto.ClientDto;
import br.com.ibm.javaweb.entity.Client;

import java.util.Optional;

public class ClientConverter {

    private ClientConverter() {}

    public static Client toEntity(Optional<ClientDto> clientDto) {
        if (clientDto.isPresent()) {
            return new Client(clientDto.get().getId(), clientDto.get().getName());
        } else {
            throw new IllegalArgumentException("Client can not be null");
        }
    }

    public static ClientDto toDto(Optional<Client> client) {
        if (client.isPresent()) {
            return new ClientDto(client.get().getId(), client.get().getName());
        } else {
            throw new IllegalArgumentException("Client can not be null");
        }
    }
}

