package it.prova.gestioneassicurato.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestioneassicurato.model.Assicurato;
import it.prova.gestioneassicurato.repository.AssicuratoRepository;

@Service
@Transactional
public class AssicuratoServiceImpl implements AssicuratoService{
	
	@Autowired
	private AssicuratoRepository repository;

	@Override
	public List<Assicurato> listAll() {
		return (List<Assicurato>) repository.findAll();
	}

	@Override
	public Assicurato caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Assicurato assicuratoInstance) {

		repository.save(assicuratoInstance);
	}

	@Override
	public void inserisciNuovo(Assicurato assicuratoInstance) {

		repository.save(assicuratoInstance);
	}

	@Override
	public void rimuovi(Assicurato assicuratoInstance) {

		repository.delete(assicuratoInstance);
	}

	@Override
	public Assicurato cercaPerNomeECognome(String nome, String cognome) {
		return repository.findByNomeAndCognome(nome, cognome);
	}

}
