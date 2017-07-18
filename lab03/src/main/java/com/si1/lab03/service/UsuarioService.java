package com.si1.lab03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si1.lab03.model.Usuario;
import com.si1.lab03.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario fazerLogin(Usuario usuario) {
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		for (Usuario user : usuarios) {
			if (user.getEmail().equals(usuario.getEmail())) {
				if (user.getSenha().equals(usuario.getSenha()))
					return usuario;
			}
		}
		return null;
	}
	
	public Usuario cadastrar(Usuario usuario) {
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		for (Usuario user : usuarios) {
			if (user.getEmail().equals(usuario.getEmail()))
				return null;
		}
		return this.usuarioRepository.save(usuario);
	}

}
