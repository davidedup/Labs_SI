package com.si1.lab03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si1.lab03.model.Usuario;
import com.si1.lab03.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public void register(Usuario user) {
		if (this.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException();
		} else {
			usuarioRepository.save(user);
			return;
		}
	}

	public Usuario login(Usuario user) {
		if (validation(user.getEmail(), user.getSenha()) == null) {
			throw new RuntimeException();
		} else {
			return validation(user.getEmail(), user.getSenha());
		}

	}

	public Usuario validation(String email, String password) {
		Usuario user = this.findByEmail(email);
		if (user == null)
			return user;
		if (user.getSenha().equals(password)) {
				return user;
		}
		return null;
	}
	
	public Usuario findByEmail(String email) {
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		for (Usuario user : usuarios) {
			if (user.getEmail().equals(email))
				return user;
		}
		return null;
	}

}
