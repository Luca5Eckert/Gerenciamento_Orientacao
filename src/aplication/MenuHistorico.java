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

	public MenuHistorico() {
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
	
	public Menu sobscreverMenu(Menu menu) {
		return linhaDoTempoMenu.set(ponteiroDoMenu, menu);
	}

	public boolean apontarPonteiroParaFrente() {
		this.ponteiroDoMenu++;
		return true;
	}
	
	public Menu pegarMenuAnterior() {
		return linhaDoTempoMenu.get(ponteiroDoMenu-1);
	}

	public void definirProximoMenu(Menu menu) {
		if (ponteiroDoMenu < linhaDoTempoMenu.size() - 1) {
			linhaDoTempoMenu = linhaDoTempoMenu.subList(0, ponteiroDoMenu + 1);
		}

		linhaDoTempoMenu.add(menu);
		apontarPonteiroParaFrente();
	}

	public Menu voltarMenu(Menu menu) {
		if (temAnterior()) {
			this.ponteiroDoMenu--;
			return sobscreverMenu(menu);
		}
		throw new RuntimeException("Não existe menu anterior");
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
	    if (linhaDoTempoMenu.isEmpty()) {
	        System.out.println("Nenhum histórico disponível.");
	        return;
	    }

	    System.out.println("Histórico de Navegação de Menus:");

	    for (int i = 0; i < linhaDoTempoMenu.size(); i++) {
	        Menu menu = linhaDoTempoMenu.get(i);
	        String marcador = (i == ponteiroDoMenu) ? " <-- Menu Atual" : "";
	        System.out.printf("[%d] %s%s%n", i, menu.getClass().getSimpleName(), marcador);
	    }
	}

	
}
