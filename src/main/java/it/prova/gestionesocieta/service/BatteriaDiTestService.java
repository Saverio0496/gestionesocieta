package it.prova.gestionesocieta.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
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

	public void testInserisciNuovoDipendente() throws Exception {
		System.out.println("Inizio testInserisciNuovoDipendente");

		Long nowInMillisecondi = new Date().getTime();

		Societa SocietaPerTestInserimentoDipendente = new Societa("Unieuro" + nowInMillisecondi,
				"Via Roma, 20" + nowInMillisecondi, new SimpleDateFormat("dd-MM-yyyy").parse("08-01-2012"));
		if (SocietaPerTestInserimentoDipendente.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(SocietaPerTestInserimentoDipendente);
		if (SocietaPerTestInserimentoDipendente.getId() == null || SocietaPerTestInserimentoDipendente.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		Dipendente dipendenteDaInserire = new Dipendente("Cristian" + nowInMillisecondi, "Casino" + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("10-07-2000"), 54000);
		dipendenteDaInserire.setSocieta(SocietaPerTestInserimentoDipendente);
		dipendenteService.inserisciNuovo(dipendenteDaInserire);
		if (dipendenteDaInserire.getId() == null && dipendenteDaInserire.getId() < 1)
			throw new RuntimeException("testInserisciNuovoDipendente... failed: inserimento dipendente fallito");

		System.out.println("Fine testInserisciNuovoDipendente!");
	}

	public void testRimuoviSocietaConEccezione() throws Exception {
		System.out.println("Inizio testRimuoviSocietaConEccezione");

		Long nowInMillisecondi = new Date().getTime();

		Societa SocietaDaEliminare = new Societa("Trust" + nowInMillisecondi, "Via Firenze, 21" + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("09-23-2012"));
		if (SocietaDaEliminare.getId() != null)
			throw new RuntimeException(
					"testRimozioneSocietaVaiInRollback...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(SocietaDaEliminare);
		if (SocietaDaEliminare.getId() == null || SocietaDaEliminare.getId() < 1)
			throw new RuntimeException("testRimozioneSocietaVaiInRollback...failed");
		try {
			societaService.removeConEccezione(SocietaDaEliminare);
			throw new RuntimeException("testRemoveConEccezioneVaInRollback...failed: eccezione non lanciata");
		} catch (Exception e) {
			// se passo di qui è tutto ok
		}

		if (SocietaDaEliminare == null || SocietaDaEliminare.getId() == null)
			throw new RuntimeException(
					"testRimozioneSocietaVaiInRollback...failed: cancellazione avvenuta senza rollback");

		societaService.rimuovi(SocietaDaEliminare);

		if (SocietaDaEliminare.getDipendenti().size() != 0)
			throw new RuntimeException(
					"testRimozioneSocietaVaiInRollback...failed. Impossibile rimuovere: la societa ha dipendenti assegnati");

		System.out.println("Fine testRimuoviSocietaConEccezione!");
	}

}
