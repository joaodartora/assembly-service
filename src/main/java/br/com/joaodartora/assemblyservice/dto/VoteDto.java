package br.com.joaodartora.assemblyservice.dto;

import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;

public class VoteDto {

    private Long id;
    private Long sessionId;
    private VoteChoiceEnum vote;
    private Integer associatedId;
    private String associatedCpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public VoteChoiceEnum getVote() {
        return vote;
    }

    public void setVote(VoteChoiceEnum vote) {
        this.vote = vote;
    }

    public Integer getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Integer associatedId) {
        this.associatedId = associatedId;
    }

    public String getAssociatedCpf() {
        return associatedCpf;
    }

    public void setAssociatedCpf(String associatedCpf) {
        this.associatedCpf = associatedCpf;
    }
}
