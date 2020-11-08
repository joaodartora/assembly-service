package br.com.joaodartora.assemblyservice.dto;

public class VoteResultsDto {

    private Long totalVotes;
    private Long yesVotes;
    private Long noVotes;

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
        private VoteResultsDto voteResultsDto;

        private Builder() {
            voteResultsDto = new VoteResultsDto();
        }

        public Builder totalVotes(Long totalVotes) {
            voteResultsDto.setTotalVotes(totalVotes);
            return this;
        }

        public Builder yesVotes(Long yesVotes) {
            voteResultsDto.setYesVotes(yesVotes);
            return this;
        }

        public Builder noVotes(Long noVotes) {
            voteResultsDto.setNoVotes(noVotes);
            return this;
        }

        public VoteResultsDto build() {
            return voteResultsDto;
        }
    }
}
