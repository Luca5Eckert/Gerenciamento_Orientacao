package service.exceptions;

public class FiltroJaAdicionadoException extends RuntimeException {

	
	public FiltroJaAdicionadoException() {
		super("Filtro já adicionado");
	}
	
	public FiltroJaAdicionadoException(String mensagem) {
		super(mensagem);
	}
}
