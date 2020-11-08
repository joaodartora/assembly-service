package br.com.joaodartora.assemblyservice.dto;

import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;

public class VoteDto {

    private VoteChoiceEnum vote;
    private AssociatedDto associated;

    public VoteChoiceEnum getVote() {
        return vote;
    }

    public void setVote(VoteChoiceEnum vote) {
        this.vote = vote;
    }

    public AssociatedDto getAssociated() {
        return associated;
    }

    public void setAssociated(AssociatedDto associated) {
        this.associated = associated;
    }
}
