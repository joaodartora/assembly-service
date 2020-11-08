package br.com.joaodartora.assemblyservice.dto;

import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class SessionDto {

    private Long id;
    private Long agendaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private VoteChoiceEnum result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
    }

    public VoteChoiceEnum getResult() {
        return result;
    }

    public void setResult(VoteChoiceEnum result) {
        this.result = result;
    }

}
