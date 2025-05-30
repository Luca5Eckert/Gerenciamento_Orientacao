package service.exceptions;

public class NivelDeAcessoInsuficienteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NivelDeAcessoInsuficienteException() {
		super("Nível de acesso insuficiente");
	}
	
	public NivelDeAcessoInsuficienteException(String mensagem) {
		super(mensagem);
	}

}
