package com.si1.lab03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si1.lab03.model.Serie;
import com.si1.lab03.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	SerieRepository serieRepository;
	
	public Serie addSerie(Serie serie) {
		return this.serieRepository.save(serie);
	}
	
	public Serie removeSerie(Serie serie) {
		serieRepository.delete(serie);
		return serie;
	}
	
	public Serie removeSerie(Integer serieID) {
		Serie serie = getSerie(serieID);
		serieRepository.delete(serie);
		return serie;
	}
	
	public Serie getSerie(Integer serieID) {
		return serieRepository.findOne(serieID);
	}
	
	public List<Serie> getSeries() {
		return serieRepository.findAll();
	}
	
	public Serie atualizaSerie(Serie serie) {
		return this.serieRepository.save(serie);
	}
	
	public List<Serie> getSeriesUsuario(Long idUsuario) {
		List<Serie> seriesUsuario = new ArrayList<Serie>();
		List<Serie> series = this.serieRepository.findAll();
		for(Serie serie : series) {
			if (serie.getIdUsuario().equals(idUsuario)) {
				seriesUsuario.add(serie);
			}
		}
		return seriesUsuario;
	}
}
