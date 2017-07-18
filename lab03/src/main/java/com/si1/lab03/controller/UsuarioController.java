package com.si1.lab03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.si1.lab03.model.Usuario;
import com.si1.lab03.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Usuario> fazerLogin(@RequestBody Usuario usuario) {
		usuario = usuarioService.fazerLogin(usuario);
		if (usuario == null) {
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		Usuario usuarioCadastrado = usuarioService.cadastrar(usuario);
		if (usuarioCadastrado == null) {
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	

}
