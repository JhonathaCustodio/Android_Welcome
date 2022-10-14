package br.com.ibm.javaweb.service;

import br.com.ibm.javaweb.domain.dto.ClientDto;
import br.com.ibm.javaweb.entity.Client;
import br.com.ibm.example.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() {
        List<Client> clients = Arrays.asList(new Client(1l, "Maria"));
        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> expected = clientService.get();

        Mockito.verify(clientRepository).findAll();

        Assertions.assertEquals(expected.size(), 1);
        Assertions.assertEquals(expected.get(0).getId(), clients.get(0).getId());
        Assertions.assertEquals(expected.get(0).getName(), clients.get(0).getName());
    }

    @Test()
    public void testGetEmptyList() {
        List<Client> clients = new ArrayList<>();
        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> expected = clientService.get();

        Mockito.verify(clientRepository).findAll();

        Assertions.assertEquals(expected.size(), 0);
    }

    @Test
    public void testGetById() {
        Long id = 1l;
        Optional<Client> client = Optional.of(new Client(id, "Maria"));
        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(id))).thenReturn(client);

        ClientDto expected = clientService.get(id);

        Mockito.verify(clientRepository).findById(ArgumentMatchers.eq(id));

        Assertions.assertEquals(expected.getId(), client.get().getId());
        Assertions.assertEquals(expected.getName(), client.get().getName());
    }

    @Test
    public void testGetByIdException() {
        Long id = 1l;
        Optional<Client> client = Optional.empty();
        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(id))).thenReturn(client);

        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    clientService.get(id);
                }, "IllegalArgumentException error was expected");

        Mockito.verify(clientRepository).findById(ArgumentMatchers.eq(id));

        Assertions.assertEquals(thrown.getMessage(), "Client can not be null");
    }

    @Test
    public void testGetByName() {
        String name = "Maria";
        List<Client> clients = Arrays.asList(new Client(1l, name));
        Mockito.when(clientRepository.findByName(ArgumentMatchers.eq(name))).thenReturn(clients);

        List<ClientDto> expected = clientService.getByName(name);

        Mockito.verify(clientRepository).findByName(ArgumentMatchers.eq(name));

        Assertions.assertEquals(expected.size(), 1);
        Assertions.assertEquals(expected.get(0).getId(), clients.get(0).getId());
        Assertions.assertEquals(expected.get(0).getName(), clients.get(0).getName());
    }

    @Test
    public void testGetByNameEmptyList() {
        String name = "Maria";
        List<Client> clients = new ArrayList<>();
        Mockito.when(clientRepository.findByName(ArgumentMatchers.eq(name))).thenReturn(clients);

        List<ClientDto> expected = clientService.getByName(name);

        Mockito.verify(clientRepository).findByName(ArgumentMatchers.eq(name));

        Assertions.assertEquals(expected.size(), 0);
    }

    @Test
    public void testSave() {
        Client client = new Client(1l, "Maria");
        ClientDto clientDto = new ClientDto(null, "Maria");
        Mockito.when(clientRepository.save(ArgumentMatchers.any())).thenReturn(client);

        ClientDto expected = clientService.save(clientDto);

        Mockito.verify(clientRepository).save(ArgumentMatchers.any());

        Assertions.assertEquals(expected.getId(), client.getId());
        Assertions.assertEquals(expected.getName(), client.getName());
    }

    @Test
    public void testSaveException() {
        ClientDto clientDto = new ClientDto(null, "Maria");
        Mockito.when(clientRepository.save(ArgumentMatchers.any())).thenReturn(null);

        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    clientService.save(clientDto);
                }, "IllegalArgumentException error was expected");

        Mockito.verify(clientRepository).save(ArgumentMatchers.any());

        Assertions.assertEquals(thrown.getMessage(), "Client can not be null");
    }
}
