package service;

import Dominio.Usuario;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class LoginService {

	public boolean validarLogin(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) throws LoginUsuarioException {

		Usuario usuario = usuarioRepositorio.pegarUsuarioEmail(usuarioDto.email());

		if (usuario == null) {
			throw new LoginUsuarioException(idiomaImplementacao.pegarMensagemErroLoginUsuario());
		}

		if (!senhaEhValida(usuarioDto.senha(), usuario.getSenha())) {
			throw new LoginSenhaException(idiomaImplementacao.pegarMensagemErroLoginSenha());
		}

		return true;
	}

	private boolean senhaEhValida(String senhaInformada, String senhaCadastrada) {
		return senhaInformada.equals(senhaCadastrada);
	}

}
