package br.com.joaodartora.assemblyservice.dto;

import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

import java.time.LocalDateTime;

public class SessionDto {

    private Long id;
    private Long agendaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private VotesResultEnum result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
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

    public VotesResultEnum getResult() {
        return result;
    }

    public void setResult(VotesResultEnum result) {
        this.result = result;
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private SessionDto sessionDto;

        private Builder() {
            sessionDto = new SessionDto();
        }

        public Builder id(Long id) {
            sessionDto.setId(id);
            return this;
        }

        public Builder agendaId(Long agendaId) {
            sessionDto.setAgendaId(agendaId);
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            sessionDto.setStartTime(startTime);
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            sessionDto.setEndTime(endTime);
            return this;
        }

        public Builder result(VotesResultEnum result) {
            sessionDto.setResult(result);
            return this;
        }

        public SessionDto build() {
            return sessionDto;
        }
    }
}
