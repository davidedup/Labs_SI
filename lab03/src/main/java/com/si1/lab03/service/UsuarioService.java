package com.si1.lab03.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.si1.lab03.dao.UsuarioDAO;
import com.si1.lab03.model.Usuario;

public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario fazerLogin(Usuario usuario) {
		Usuario usuarioSalvo = usuarioDAO.verificarUsuario(usuario.getEmail(), usuario.getSenha());
		return usuarioSalvo;
	}

	public Usuario cadastrar(Usuario usuario) {
		usuarioDAO.cadastrarUsuario(usuario);
		return usuario;
	}

}
