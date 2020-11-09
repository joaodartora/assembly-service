package br.com.joaodartora.assemblyservice.client.response;

import br.com.joaodartora.assemblyservice.type.VotePossibilityEnum;

public class CpfValidationResponse {

    private VotePossibilityEnum status;

    public CpfValidationResponse() {
    }

    public CpfValidationResponse(VotePossibilityEnum status) {
        this.status = status;
    }

    public VotePossibilityEnum getStatus() {
        return status;
    }

    public void setStatus(VotePossibilityEnum status) {
        this.status = status;
    }
}
