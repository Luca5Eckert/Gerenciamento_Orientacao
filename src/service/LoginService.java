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
	private CriadorSessao criadorSessao;

	public LoginService() {
		this.seguranca = new UsuarioSecurity();
		this.criadorSessao = new CriadorSessao();
	}

	public SessaoUsuario realizarLogin(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) {
		Usuario usuario = usuarioRepositorio.pegarUsuarioEmail(usuarioDto.email().trim().toLowerCase());
		validarLogin(usuarioDto, usuario, idiomaImplementacao);

		return criarSessaoUsuario(usuarioRepositorio.pegarIdPeloEmail(usuario.getEmail()), usuarioRepositorio);
	}

	private SessaoUsuario criarSessaoUsuario(int idUsuario, UsuarioRepositorio usuarioRepositorio) {
		return criadorSessao.criarSessao(idUsuario, usuarioRepositorio);
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
