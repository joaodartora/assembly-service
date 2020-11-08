package br.com.joaodartora.assemblyservice.repository;

import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, Long> {

    List<SessionEntity> findAllByAgendaId(Long agendaId);

}
