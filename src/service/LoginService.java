package service;

import Dominio.Usuario;
import dtos.UsuarioDto;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class LoginService {
	
	public boolean validarUsuarioExistente(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio) throws LoginUsuarioException {
		return usuarioRepositorio.pegarUsuarioEmail(usuarioDto.email()) != null;
		
	}

	public boolean validarSenha(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio) throws LoginSenhaException {
		Usuario usuario = usuarioRepositorio.pegarUsuarioEmail(usuarioDto.email());

		return senhaEhValida(usuarioDto.senha(), usuario.getSenha());	
	}

	private boolean senhaEhValida(String senhaInformada, String senhaCadastrada) {
		return senhaInformada.equals(senhaCadastrada);
	}
}
