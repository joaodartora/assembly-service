package br.com.joaodartora.assemblyservice.api.rest.v1.response;

import io.swagger.annotations.ApiModelProperty;

public class VoteResponse {

    @ApiModelProperty(value = "Receipt of the vote.", example = "654654")
    private Long ballotReceipt;

    public VoteResponse(Long ballotReceipt) {
        this.ballotReceipt = ballotReceipt;
    }

    public Long getBallotReceipt() {
        return ballotReceipt;
    }

    public void setBallotReceipt(Long ballotReceipt) {
        this.ballotReceipt = ballotReceipt;
    }
}
