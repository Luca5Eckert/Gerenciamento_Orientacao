package service;

import java.util.regex.Pattern;

import aplication.implementacoes.IdiomaImplementacao;
import service.exceptions.usuario.CadastroSenhaException;

public class ValidarSenha implements Validacao, Runnable {

	final Pattern CARACTER_ESPECIAL = Pattern.compile("[^a-zA-Z0-9]");
	final Pattern MAIUSCULA = Pattern.compile("[A-Z]");

	private String senhaUsuario;
	private IdiomaImplementacao idiomaImplementacao;

	public ValidarSenha(String senhaUsuario, IdiomaImplementacao idiomaImplementacao) {
		this.senhaUsuario = senhaUsuario;
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public void run() {
		validar();
	}

	@Override
	public boolean validar() {
		return validarTamanhoSenha();
	}

	public boolean validarTamanhoSenha() throws CadastroSenhaException {
		if (senhaUsuario.length() >= 8) {
			return true;
		}

		throw new CadastroSenhaException(idiomaImplementacao.pegarMensagemErroCadastroSenhaPequena());
	}

	public boolean validarLetraMaiusculaSenha(String senhaUsuario, IdiomaImplementacao idiomaImplementacao)
			throws CadastroSenhaException {

		if (MAIUSCULA.matcher(senhaUsuario).find()) {
			return true;
		}

		throw new CadastroSenhaException(idiomaImplementacao.pegarMensagemErroCadastroSenhaSemMaiscula());

	}

	public boolean validarCaracterEspecial(String senhaUsuario, IdiomaImplementacao idiomaImplementacao)
			throws CadastroSenhaException {

		if (CARACTER_ESPECIAL.matcher(senhaUsuario).find()) {
			return true;
		}

		throw new CadastroSenhaException(idiomaImplementacao.pegarMensagemErroCadastroSenhaSemCaracterEspecial());

	}

}
