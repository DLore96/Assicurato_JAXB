package it.prova.gestioneassicurato.service;

import java.util.List;

import it.prova.gestioneassicurato.model.Assicurato;

public interface AssicuratoService {

	public List<Assicurato> listAll() ;

	public Assicurato caricaSingoloElemento(Long id) ;

	public void aggiorna(Assicurato assicuratoInstance) ;

	public void inserisciNuovo(Assicurato assicuratoInstance) ;

	public void rimuovi(Assicurato assicuratoInstance) ;

	public Assicurato cercaPerNomeECognome(String nome, String cognome) ;
}
