package service;

import aplication.implementacoes.IdiomaImplementacao;

public class SessaoUsuario {
	private static int numeroIdUsuario;
	private static IdiomaImplementacao idiomaImplementacao;
	private static boolean logado;

	public static void criarSessao(int numeroIdUsuario, IdiomaImplementacao idiomaImplementacao) {
		SessaoUsuario.setNumeroIdUsuario(numeroIdUsuario);
		SessaoUsuario.idiomaImplementacao = idiomaImplementacao;
		SessaoUsuario.setLogado(true);
	}

	public static int getNumeroIdUsuario() {
		return numeroIdUsuario;
	}

	public static void setNumeroIdUsuario(int numeroIdUsuario) {
		SessaoUsuario.numeroIdUsuario = numeroIdUsuario;
	}

	public static void fecharSessao() {
		setNumeroIdUsuario(-1);
		setLogado(false);
	}

	public static IdiomaImplementacao pegarIdioma() {
		return idiomaImplementacao;
	}

	public static void definirIdioma(IdiomaImplementacao idiomaImplementacao) {
		SessaoUsuario.idiomaImplementacao = idiomaImplementacao;
	}

	public static boolean isLogado() {
		return logado;
	}

	public static void setLogado(boolean logado) {
		SessaoUsuario.logado = logado;
	}
}
