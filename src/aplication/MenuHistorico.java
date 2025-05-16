package aplication;

import java.util.ArrayList;
import java.util.List;

import aplication.interfaces.Menu;

public class MenuHistorico {
	private List<Menu> linhaDoTempoMenu = new ArrayList<>();
	private int ponteiroDoMenu = -1;

	public MenuHistorico(Menu menu) {
		this.definirProximoMenu(menu);
		
	}

	public List<Menu> getLinhaDoTempoMenu() {
		return linhaDoTempoMenu;
	}

	public void setLinhaDoTempoMenu(List<Menu> novaLinhaDoTempo) {
	    this.linhaDoTempoMenu = novaLinhaDoTempo;
	}


	public Menu pegarUltimoMenu() {
		return linhaDoTempoMenu.getLast();
	}

	public Menu pegarMenuAtual() {
		return this.linhaDoTempoMenu.get(this.ponteiroDoMenu);
	}

	public boolean apontarPonteiroParaFrente() {
		this.ponteiroDoMenu++;
		return true;
	}

	public void definirProximoMenu(Menu menu) {
		if (ponteiroDoMenu < linhaDoTempoMenu.size() - 1) {
			linhaDoTempoMenu = linhaDoTempoMenu.subList(0, ponteiroDoMenu + 1);
		}

		linhaDoTempoMenu.add(menu);
		apontarPonteiroParaFrente();
	}


	public Menu irProximoMenu() {
		if (temProximo()) {
			this.ponteiroDoMenu++;
			return pegarMenuAtual();
		}

		throw new RuntimeException("Não existe proximo menu");
	}

	public Menu voltarMenu() {
		if (temAnterior()) {
			this.ponteiroDoMenu--;
			return pegarMenuAtual();
		}

		throw new RuntimeException("Não existe menu anterior");
	}

	public boolean temProximo() {
		return ponteiroDoMenu < linhaDoTempoMenu.size();
	}

	public boolean temAnterior() {
		return ponteiroDoMenu > -1;
	}

	public void mostrarHistorico() {
		this.linhaDoTempoMenu.forEach(menu -> System.out.println(menu.getClass().getSimpleName()) );
	}
	
}
