package br.com.ibm.example.controller;

import br.com.ibm.javaweb.domain.dto.ClientDto;
import br.com.ibm.javaweb.entity.Client;
import br.com.ibm.example.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() throws Exception {
        List<Client> clients = Arrays.asList(new Client(1l, "Maria"));
        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("1")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Maria")));

        Mockito.verify(clientRepository).findAll();
    }

    @Test
    public void testGetById() throws Exception {
        Long id = 1l;
        Optional<Client> client = Optional.of(new Client(id, "Maria"));
        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(id))).thenReturn(client);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(id.toString())));

        Mockito.verify(clientRepository).findById(ArgumentMatchers.eq(id));
    }

    @Test
    public void testGetByName() throws Exception {
        String name = "Maria";
        List<Client> clients = Arrays.asList(new Client(1l, name));
        Mockito.when(clientRepository.findByName(ArgumentMatchers.eq(name))).thenReturn(clients);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients/name")
                        .param("name", name))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(name)));

        Mockito.verify(clientRepository).findByName(ArgumentMatchers.eq(name));
    }

    @Test
    public void testSave() throws Exception {
        Client client = new Client(1l, "Maria");
        ClientDto clientDto = new ClientDto(null, "Maria");
        Mockito.when(clientRepository.save(ArgumentMatchers.any())).thenReturn(client);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .content(new ObjectMapper().writeValueAsString(clientDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("1")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Maria")));

        Mockito.verify(clientRepository).save(ArgumentMatchers.any());
    }
}
