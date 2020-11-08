package br.com.joaodartora.assemblyservice.api.rest.v1.response;

import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import io.swagger.annotations.ApiModelProperty;

public class AgendaResultsResponse {

    @ApiModelProperty(value = "Result of the agenda.", example = "YES")
    private VotesResultEnum result;

    @ApiModelProperty(value = "Total of votes in this agenda.", example = "100")
    private Integer totalVotes;

    @ApiModelProperty(value = "Total of 'YES' votes in this agenda.", example = "70")
    private Integer yesVotes;

    @ApiModelProperty(value = "Total of 'NO' votes in this agenda.", example = "30")
    private Integer noVotes;

    public VotesResultEnum getResult() {
        return result;
    }

    public void setResult(VotesResultEnum result) {
        this.result = result;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Integer getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(Integer yesVotes) {
        this.yesVotes = yesVotes;
    }

    public Integer getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(Integer noVotes) {
        this.noVotes = noVotes;
    }
}
