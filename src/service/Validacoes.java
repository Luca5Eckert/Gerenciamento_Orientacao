package service;

import aplication.implementacoes.IdiomaImplementacao;
import repositorio.UsuarioRepositorio;

public class Validacoes {

	public static Runnable toEmail(String email, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) {
		return new ValidarEmail(email, usuarioRepositorio, idiomaImplementacao);
	}

	public static Runnable toUsuario(String nomeUsuario, IdiomaImplementacao idiomaImplementacao) {
		return new ValidarUsuario(nomeUsuario, idiomaImplementacao);
	}

	public static Runnable toSenha(String senhaUsuario, IdiomaImplementacao idiomaImplementacao) {
		return new ValidarSenha(senhaUsuario, idiomaImplementacao);
	}

}
