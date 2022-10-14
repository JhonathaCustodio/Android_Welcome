package br.com.ibm.javaweb.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ClientDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String name;

    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
