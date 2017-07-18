package com.si1.lab03.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Serie {

	@Id
	@GeneratedValue()
	private Integer id;
	private String nome;
	private String imdbId;
	private String avaliacao;
	private Integer ultimoEpisodio;
	private Long idUsuario;
	private boolean perfil;
	
	
	public boolean noPerfil() {
		return perfil;
	}
	public void setPerfil(boolean perfil) {
		this.perfil = perfil;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer serieID) {
		this.id = serieID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Integer getUltimoEpisodio() {
		return ultimoEpisodio;
	}
	public void setUltimoEpisodio(Integer ultimoEpisodio) {
		this.ultimoEpisodio = ultimoEpisodio;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
