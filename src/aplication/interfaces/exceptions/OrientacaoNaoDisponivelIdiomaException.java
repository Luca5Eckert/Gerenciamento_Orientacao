package aplication.interfaces.exceptions;

public class OrientacaoNaoDisponivelIdiomaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrientacaoNaoDisponivelIdiomaException(String mensagem) {
		super(mensagem);
	}
	
	public OrientacaoNaoDisponivelIdiomaException() {
		super("Orientação ainda não disponível neste idioma");
	}

}
