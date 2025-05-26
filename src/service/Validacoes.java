package service;

import aplication.implementacoes.IdiomaImplementacao;
import repositorio.OrientacaoRepositorio;

public class Validacoes {

	public static Validacao toEmail(String email, OrientacaoRepositorio orientacaoRepositorio, IdiomaImplementacao idiomaImplementacao) {
		return new ValidarEmail(email, orientacaoRepositorio);
	}
	
	public static Validacao toUsuario(String usuario, IdiomaImplementacao idiomaImplementacao) {
		return new ValidarUsuario(usuario);
		
	}
	
}
