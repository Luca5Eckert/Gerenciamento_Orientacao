package aplication;

import java.util.List;

import aplication.interfaces.Menu;

public class MenuHistorico {
	private List<Menu> linhaDoTempoMenu;
	private int ponteiroDoMenu;
	
	public List<Menu> getLinhaDoTempoMenu();

	public void setLnhaDoTempoMenu(List<Menu> novaLinhaDoTempo){
		this.linhaDoTempoMenu = novaLinhaDoTempo;
	}

	public Menu pegarUltimoMenu(){
		return linhaDoTempoMenu.getLast();
	}

	public Menu pegarMenuAtual(){
		return this.linhaDoTempoMenu.get(this.ponteiroDoMenu);
	}
	
	public Menu irProximoMenu(){
		if(temProximo()){
			this.ponteiroMenu++;
			return pegarMenuAtual();
		}

		throw new Exception("Não existe proximo menu");
	}

	public Menu voltarMenu(){
		if(temAnterior()){
			this.ponteiroDoMenu--:
			return pegarMenuAtual();
		}

		throw new Exception("Não existe menu anterior");
	}

	public boolean temProximo(){
		return ponteiroAtual < linhaDoTempoMenu.lenght();
	}

	public boolean temAnterior(){
		return ponteiroAtual > linhaDoTempoMenu.lenght();
	}
	
}
