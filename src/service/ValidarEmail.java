package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aplication.implementacoes.IdiomaImplementacao;
import repositorio.OrientacaoRepositorio;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.EmailInvalidoException;

public class ValidarEmail implements Validacao{
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	
	private String emailParaValidar;
	private UsuarioRepositorio usuarioRepositorio;
	private IdiomaImplementacao idiomaImplementacao;
	
	public ValidarEmail(String emailParaValidar, UsuarioRepositorio usuarioRepositorio, IdiomaImplementacao idiomaImplementacao) {
		this.emailParaValidar = emailParaValidar;
		this.usuarioRepositorio = usuarioRepositorio;
		this.idiomaImplementacao = idiomaImplementacao;
	}
	
	@Override
	public boolean validar() {
		 
	}
	
	public boolean validarEmailEscrita() throws EmailInvalidoException {
		Matcher verificador = pattern.matcher(emailParaValidar);
		boolean emailValido = verificador.matches();
		
		if (emailValido) {
			throw new EmailInvalidoException(idiomaImplementacao.pegarMensagemEmailComSintaxeIncorreta()); 
		}
		return emailValido;
	}
	
	public boolean validarEmailUnico() {
		boolean emailValido = usuarioRepositorio.verificaSeUsuarioExisteEmail(emailParaValidar);
	}


}
