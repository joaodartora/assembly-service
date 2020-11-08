package br.com.joaodartora.assemblyservice.repository;

import br.com.joaodartora.assemblyservice.repository.entity.AgendaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends CrudRepository<AgendaEntity, Long> {

}
