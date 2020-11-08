package br.com.joaodartora.assemblyservice.repository;

import br.com.joaodartora.assemblyservice.repository.entity.VoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Long> {
}
