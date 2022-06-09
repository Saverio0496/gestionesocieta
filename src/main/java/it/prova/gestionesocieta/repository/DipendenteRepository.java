package it.prova.gestionesocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente> {

	public void addToSocieta(Dipendente dipendenteInput, Societa societaInput);

	@Query("from Dipendente d join d.societa s where s.dataFondazione < 1990-01-01 order by d.dataAssunzione desc")
	@EntityGraph(attributePaths = { "societa" })
	List<Dipendente> findTheOldestDipendenteWithSocietaBornBefore();

}
