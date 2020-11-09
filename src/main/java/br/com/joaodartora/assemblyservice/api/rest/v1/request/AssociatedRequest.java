package br.com.joaodartora.assemblyservice.api.rest.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociatedRequest {

    @ApiModelProperty(value = "Vote choice.", example = "4654", required = true)
    @JsonProperty(required = true)
    @NotNull(message = "Associated ID is required.")
    private Long id;

    @ApiModelProperty(value = "Vote choice.", example = "38869040071")
    @JsonProperty(required = true)
    @NotBlank(message = "Associated CPF is required.")
    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
