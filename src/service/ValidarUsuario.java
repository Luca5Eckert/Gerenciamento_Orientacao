package service;

import aplication.implementacoes.IdiomaImplementacao;
import service.exceptions.usuario.CadastroException;
import service.exceptions.usuario.CadastroUsuarioJaExistenteException;

public class ValidarUsuario implements Validacao, Runnable {

    private String usuarioParaValidar;
    private IdiomaImplementacao idiomaImplementacao;

    public ValidarUsuario(String usuarioParaValidar, IdiomaImplementacao idiomaImplementacao) {
        this.usuarioParaValidar = usuarioParaValidar;
        this.idiomaImplementacao = idiomaImplementacao;
    }

    @Override
    public boolean validar() {
        return !comecaComEspaco() && !excedeLimiteCaracteres();
    }

    private boolean comecaComEspaco() throws CadastroException   {
        boolean usuarioValido = usuarioParaValidar != null && usuarioParaValidar.startsWith(" ");
        
        if (!usuarioValido) {
        	throw new CadastroException(idiomaImplementacao.pegarMensagemUsuarioInvalidoEmBranco());
        }
        return true;
    }

    private boolean excedeLimiteCaracteres() {
    	boolean usuarioValido = usuarioParaValidar != null && usuarioParaValidar.length() >= 15;
    	
    	if(!usuarioValido) {
        	throw new CadastroUsuarioJaExistenteException(idiomaImplementacao.pegarMensagemUsuarioInvalidoLimiteDeCaracters());
    	}
    	return true;
    }

	@Override
	public void run() {
		validar();	
	}

}
