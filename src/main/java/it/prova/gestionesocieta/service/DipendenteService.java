package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

public interface DipendenteService {

	public List<Dipendente> listAllDipendenti();

	public Dipendente caricaSingoloDipendente(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);

	public List<Dipendente> findByExample(Dipendente example);

	public List<Dipendente> cercaIlDipendentePiuAnzianoDelleSocietaFondatePrimaDel();

}
