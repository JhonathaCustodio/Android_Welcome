package br.com.ibm.javaweb.controller;

import br.com.ibm.javaweb.domain.dto.ClientDto;
import br.com.ibm.javaweb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> get() {
        return ResponseEntity.ok(this.service.get());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDto> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @RequestMapping(path = "/name", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> get(@Param(value = "name") String name) {
        return ResponseEntity.ok(this.service.getByName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto) {
        return new ResponseEntity<>(this.service.save(dto), CREATED);
    }
}
