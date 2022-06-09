package it.prova.gestionesocieta.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente> {

	@Query("from Dipendente d join d.societa s where s.dataFondazione < '1990-01-01' and d.dataAssunzione in (select min(d.dataAssunzione) from Dipendente d)")
	@EntityGraph(attributePaths = { "societa" })
	Dipendente findTheOldestDipendenteWithSocietaBornBefore();

}
