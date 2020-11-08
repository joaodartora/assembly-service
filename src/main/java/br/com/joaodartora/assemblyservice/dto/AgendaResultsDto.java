package br.com.joaodartora.assemblyservice.dto;

import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

public class AgendaResultsDto {

    private VotesResultEnum result;
    private Long totalVotes;
    private Long yesVotes;
    private Long noVotes;

    public AgendaResultsDto() {
    }

    public VotesResultEnum getResult() {
        return result;
    }

    public void setResult(VotesResultEnum result) {
        this.result = result;
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

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private AgendaResultsDto agendaResultsDto;

        private Builder() {
            agendaResultsDto = new AgendaResultsDto();
        }

        public Builder result(VotesResultEnum result) {
            agendaResultsDto.setResult(result);
            return this;
        }

        public Builder totalVotes(Long totalVotes) {
            agendaResultsDto.setTotalVotes(totalVotes);
            return this;
        }

        public Builder yesVotes(Long yesVotes) {
            agendaResultsDto.setYesVotes(yesVotes);
            return this;
        }

        public Builder noVotes(Long noVotes) {
            agendaResultsDto.setNoVotes(noVotes);
            return this;
        }

        public AgendaResultsDto build() {
            return agendaResultsDto;
        }
    }
}
