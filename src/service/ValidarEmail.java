package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aplication.implementacoes.IdiomaImplementacao;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.EmailInvalidoException;

public class ValidarEmail implements Validacao, Runnable {
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})?$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	private String emailParaValidar;
	private UsuarioRepositorio usuarioRepositorio;
	private IdiomaImplementacao idiomaImplementacao;

	public ValidarEmail(String emailParaValidar, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) {
		this.emailParaValidar = emailParaValidar;
		this.usuarioRepositorio = usuarioRepositorio;
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public boolean validar() {
		return validarEmailEscrita() && validarEmailUnico();

	}

	public boolean validarEmailEscrita() throws EmailInvalidoException {
		Matcher verificador = pattern.matcher(emailParaValidar);
		boolean emailValido = verificador.matches();
		
		if (!emailValido) {
			throw new EmailInvalidoException(idiomaImplementacao.pegarMensagemEmailComSintaxeIncorreta());
		}
		return emailValido;
	}

	public boolean validarEmailUnico() {
		boolean emailValido = !usuarioRepositorio.verificaSeUsuarioExisteEmail(emailParaValidar);

		if (!emailValido) {
			throw new EmailInvalidoException(idiomaImplementacao.pegarMensagemEmailInvalidoJaUsado());
		}
		return emailValido;

	}

	@Override
	public void run() {
		validar();
	}

}
