package br.com.joaodartora.assemblyservice.producer.event;

import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

public class AgendaResultsEvent {

    private Long sessionId;
    private Long agendaId;
    private Long totalVotes;
    private Long yesVotes;
    private Long noVotes;
    private VotesResultEnum result;

    public AgendaResultsEvent() {
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Long getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(Long yesVotes) {
        this.yesVotes = yesVotes;
    }

    public Long getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(Long noVotes) {
        this.noVotes = noVotes;
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
        private AgendaResultsEvent agendaResultEvent;

        private Builder() {
            agendaResultEvent = new AgendaResultsEvent();
        }

        public Builder sessionId(Long sessionId) {
            agendaResultEvent.setSessionId(sessionId);
            return this;
        }

        public Builder agendaId(Long agendaId) {
            agendaResultEvent.setAgendaId(agendaId);
            return this;
        }

        public Builder totalVotes(Long totalVotes) {
            agendaResultEvent.setTotalVotes(totalVotes);
            return this;
        }

        public Builder yesVotes(Long yesVotes) {
            agendaResultEvent.setYesVotes(yesVotes);
            return this;
        }

        public Builder noVotes(Long noVotes) {
            agendaResultEvent.setNoVotes(noVotes);
            return this;
        }

        public Builder result(VotesResultEnum result) {
            agendaResultEvent.setResult(result);
            return this;
        }

        public AgendaResultsEvent build() {
            return agendaResultEvent;
        }
    }
}
