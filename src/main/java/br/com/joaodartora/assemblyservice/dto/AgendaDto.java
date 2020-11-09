package br.com.joaodartora.assemblyservice.dto;

public class AgendaDto {

    private Long id;
    private String description;

    public AgendaDto() {
    }

    public AgendaDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
