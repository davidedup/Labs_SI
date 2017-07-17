package com.si1.lab03.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.si1.lab03.model.Usuario;
import com.si1.lab03.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Usuario fazerLogin(@RequestBody Usuario usuario) {
		usuario = usuarioService.fazerLogin(usuario);
		return usuario;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public Usuario cadastrar(@RequestBody Usuario usuario) {
		Usuario usuarioCadastrado = usuarioService.cadastrar(usuario);
		return usuarioCadastrado;
	}

}
