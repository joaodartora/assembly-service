package br.com.joaodartora.assemblyservice.repository.entity;

import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "vote")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sessionId;
    @Enumerated(EnumType.STRING)
    private VoteChoiceEnum vote;
    private Long associatedId;
    private String associatedCpf;

    public VoteEntity() {
    }

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

    public Long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Long associatedId) {
        this.associatedId = associatedId;
    }

    public String getAssociatedCpf() {
        return associatedCpf;
    }

    public void setAssociatedCpf(String associatedCpf) {
        this.associatedCpf = associatedCpf;
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private VoteEntity voteEntity;

        private Builder() {
            voteEntity = new VoteEntity();
        }

        public Builder id(Long id) {
            voteEntity.setId(id);
            return this;
        }

        public Builder sessionId(Long sessionId) {
            voteEntity.setSessionId(sessionId);
            return this;
        }

        public Builder vote(VoteChoiceEnum vote) {
            voteEntity.setVote(vote);
            return this;
        }

        public Builder associatedId(Long associatedId) {
            voteEntity.setAssociatedId(associatedId);
            return this;
        }

        public Builder associatedCpf(String associatedCpf) {
            voteEntity.setAssociatedCpf(associatedCpf);
            return this;
        }

        public VoteEntity build() {
            return voteEntity;
        }
    }
}
