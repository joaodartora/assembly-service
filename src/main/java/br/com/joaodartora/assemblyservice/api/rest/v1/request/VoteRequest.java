package br.com.joaodartora.assemblyservice.api.rest.v1.request;

import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class VoteRequest {

    @ApiModelProperty(value = "Vote choice.", example = "YES", required = true)
    @JsonProperty(required = true)
    @NotNull(message = "Vote is required.")
    private VoteChoiceEnum vote;

    @ApiModelProperty(value = "Associated information.", required = true)
    @JsonProperty(required = true)
    @NotNull(message = "Associated information is required.")
    @Valid
    private AssociatedRequest associated;

    public VoteChoiceEnum getVote() {
        return vote;
    }

    public void setVote(VoteChoiceEnum vote) {
        this.vote = vote;
    }

    public AssociatedRequest getAssociated() {
        return associated;
    }

    public void setAssociated(AssociatedRequest associated) {
        this.associated = associated;
    }
}
