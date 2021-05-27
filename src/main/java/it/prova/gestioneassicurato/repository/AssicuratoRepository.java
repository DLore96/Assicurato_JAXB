package it.prova.gestioneassicurato.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneassicurato.model.Assicurato;

public interface AssicuratoRepository extends CrudRepository<Assicurato, Long>{
	
	public Assicurato findByNomeAndCognome(String nome, String cognome);

}
