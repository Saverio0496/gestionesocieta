package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;

	@Autowired
	private DipendenteService dipendenteService;

	public void testInserisciNuovoMunicipio() throws Exception {

		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = new Societa("Ferrari" + nowInMillisecondi, "Via Monaco, 30" + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("09-07-1910"));
		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		// salvo
		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("testInserisciNuovaSocieta........OK");
	}

	public void testFindByExampleSocieta() throws Exception {
		System.out.println("Inizio test findByExampleSocieta");
		Societa societaDaCercare = new Societa("Ferrari", null, null);
		List<Societa> societaTrovate = societaService.findByExample(societaDaCercare);
		for (Societa societaItem : societaTrovate) {
			System.out.println(societaItem.toString());
		}
		if (societaTrovate.size() < 1)
			throw new RuntimeException("Non ci sono società con questi parametri!");
		System.out.println("Fine test findByExampleSocieta!");
	}

}
