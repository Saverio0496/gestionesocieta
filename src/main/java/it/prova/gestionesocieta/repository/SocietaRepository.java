package it.prova.gestionesocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa> {

	@EntityGraph(attributePaths = { "dipendenti" })
	List<Societa> findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan(Integer redditoAnnuoLordo);

}
