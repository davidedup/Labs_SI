package com.si1.lab03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.si1.lab03.model.Serie;
import com.si1.lab03.service.SerieService;

@RestController
@RequestMapping("/serie")
public class SerieController {
	
	@Autowired
	private SerieService serieService;

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Serie> addSerie(Serie serie) {
		Serie serieAdicionada = serieService.addSerie(serie);
		return new ResponseEntity<>(serieAdicionada, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Serie> removeSerie(@PathVariable Integer serieId) {
		Serie serieRemovida = serieService.removeSerie(serieId);
		return new ResponseEntity<>(serieRemovida, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Serie> atualizaSerie(@RequestBody Serie serie, @PathVariable Integer serieID) {
		serie.setId(serieID);
		Serie serieAtualizada = serieService.atualizaSerie(serie);
		return new ResponseEntity<>(serieAtualizada, HttpStatus.OK);
	}
}
