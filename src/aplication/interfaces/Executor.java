package aplication.interfaces;

import service.commandos.Comando;

public interface Executor {
	
	public void executar();
	
	public Comando pegarComando();
	
	public void criarExecutadorComando();
}
