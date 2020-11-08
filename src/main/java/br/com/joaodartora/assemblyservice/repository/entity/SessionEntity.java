package br.com.joaodartora.assemblyservice.repository.entity;

import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agendaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private VotesResultEnum result;

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
        private SessionEntity sessionEntity;

        private Builder() {
            sessionEntity = new SessionEntity();
        }

        public Builder id(Long id) {
            sessionEntity.setId(id);
            return this;
        }

        public Builder agendaId(Long agendaId) {
            sessionEntity.setAgendaId(agendaId);
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            sessionEntity.setStartTime(startTime);
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            sessionEntity.setEndTime(endTime);
            return this;
        }

        public Builder result(VotesResultEnum result) {
            sessionEntity.setResult(result);
            return this;
        }

        public SessionEntity build() {
            return sessionEntity;
        }
    }
}
