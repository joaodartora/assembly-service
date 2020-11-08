package br.com.joaodartora.assemblyservice.api.rest.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AgendaRequest {

    @ApiModelProperty(value = "Agenda description.", example = "Will we go back to working in person?", required = true)
    @JsonProperty(required = true)
    @NotBlank(message = "Agenda description is required.")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
