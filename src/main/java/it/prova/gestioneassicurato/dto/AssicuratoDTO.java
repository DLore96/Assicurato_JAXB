package it.prova.gestioneassicurato.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.gestioneassicurato.model.Assicurato;



public class AssicuratoDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private BigInteger nuoviSinistri;
	private Date dateNascita;


	public AssicuratoDTO() {
	}

	public AssicuratoDTO(String nome, String cognome, String codiceFiscale, BigInteger nuoviSinistri, Date dataNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale=codiceFiscale;
		this.nuoviSinistri=nuoviSinistri;
		this.dateNascita=dataNascita;
	}
	
	public AssicuratoDTO(Long id, String nome, String cognome, String codiceFiscale, BigInteger nuoviSinistri, Date dataNascita) {
		this.id=id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale=codiceFiscale;
		this.nuoviSinistri=nuoviSinistri;
		this.dateNascita=dataNascita;
	}

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public BigInteger getNuoviSinistri() {
		return nuoviSinistri;
	}

	public void setNuoviSinistri(BigInteger nuoviSinistri) {
		this.nuoviSinistri = nuoviSinistri;
	}

	public Date getDateNascita() {
		return dateNascita;
	}

	public void setDateNascita(Date dateNascita) {
		this.dateNascita = dateNascita;
	}

	public Assicurato buildAssicuratoModel() {
		return new Assicurato(this.id, this.nome, this.cognome, this.dateNascita, this.nuoviSinistri, this.codiceFiscale);
	}

	public static AssicuratoDTO buildAssicuratoDTOFromModel(Assicurato assicuratoModel) {
		return new AssicuratoDTO(assicuratoModel.getId(), assicuratoModel.getNome(), assicuratoModel.getCognome(), 
				assicuratoModel.getCodiceFiscale(), assicuratoModel.getNuoviSinistri(), assicuratoModel.getDataNascita());
	}

	public static AssicuratoDTO createAssicuratoDTOFromParams(String nomeInputParam, String cognomeInputParam,
				String DataNascitaStringParam, String nuoviSinistriParam, String codiceFiscaleParam) {

		AssicuratoDTO result = new AssicuratoDTO();
		result.setNome(nomeInputParam);
		result.setCognome(cognomeInputParam);
		//result.setDateNascita();
		//result.setNuoviSinistri(BigInteger.(nuoviSinistriParam));
		result.setCodiceFiscale(codiceFiscaleParam);
		return result;
	}

	public static List<AssicuratoDTO> createAssicuratoDTOListFromModelList(List<Assicurato> modelListInput) {
		return modelListInput.stream().map(assicuratoEntity -> {
			return AssicuratoDTO.buildAssicuratoDTOFromModel(assicuratoEntity);
		}).collect(Collectors.toList());
	}

}
