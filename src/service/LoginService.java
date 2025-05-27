package service;

import Dominio.Usuario;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import infrastructure.security.UsuarioSecurity;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class LoginService {
	
	private UsuarioSecurity seguranca;
	
	public LoginService() {
		this.seguranca = new UsuarioSecurity();
	}

	public boolean realizarLogin(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) {
		Usuario usuario = usuarioRepositorio.pegarUsuarioEmail(usuarioDto.email());
		return validarLogin(usuarioDto, usuario, idiomaImplementacao);
	}

	public boolean validarLogin(UsuarioDto usuarioDto, Usuario usuarioDB, IdiomaImplementacao idiomaImplementacao)
			throws LoginUsuarioException {

		if (usuarioDB == null) {
			throw new LoginUsuarioException(idiomaImplementacao.pegarMensagemErroLoginUsuario());
		}

		if (!senhaEhValida(usuarioDto.senha(), usuarioDB.getSenha())) {
			throw new LoginSenhaException(idiomaImplementacao.pegarMensagemErroLoginSenha());
		}

		return true;
	}


	private boolean senhaEhValida(String senhaInformada, String senhaCadastrada) {
		return seguranca.verificarSenha(senhaInformada, senhaCadastrada);
	}


}
