package br.com.joaodartora.assemblyservice.api.rest.v1.response;

import io.swagger.annotations.ApiModelProperty;

public class AgendaResponse {

    @ApiModelProperty(value = "New agenda generated ID.", example = "31")
    private Long id;

    public AgendaResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
