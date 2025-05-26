package aplication;

import java.util.ArrayList;
import java.util.List;

import aplication.interfaces.Menu;

public class MenuHistorico {
	private List<Menu> linhaDoTempoMenu = new ArrayList<>();
	private int ponteiroDoMenu = -1;

	public MenuHistorico(Menu menu) {
		definirProximoMenu(menu);
	}

	public List<Menu> getLinhaDoTempoMenu() {
		return linhaDoTempoMenu;
	}

	public void setLinhaDoTempoMenu(List<Menu> novaLinhaDoTempo) {
		this.linhaDoTempoMenu = new ArrayList<>(novaLinhaDoTempo);
	}

	public Menu pegarUltimoMenu() {
		if (linhaDoTempoMenu.isEmpty()) {
			throw new IllegalStateException("Não há menus na linha do tempo.");
		}
		return linhaDoTempoMenu.get(linhaDoTempoMenu.size() - 1);
	}

	public Menu pegarMenuAtual() {
		if (ponteiroDoMenu >= 0 && ponteiroDoMenu < linhaDoTempoMenu.size()) {
			return linhaDoTempoMenu.get(ponteiroDoMenu);
		}
		throw new IllegalStateException("Nenhum menu atual disponível.");
	}

	public Menu sobscreverMenu(Menu menu) {
		if (ponteiroDoMenu >= 0 && ponteiroDoMenu < linhaDoTempoMenu.size()) {
			return linhaDoTempoMenu.set(ponteiroDoMenu, menu);
		}
		throw new IllegalStateException("Não é possível sobrescrever: ponteiro inválido.");
	}

	public void apontarPonteiroParaFrente() {
		this.ponteiroDoMenu++;
	}

	public void apontarPonteiroParaTras() {
		this.ponteiroDoMenu--;
	}

	public Menu pegarMenuAnterior() {
		if (ponteiroDoMenu - 1 >= 0) {
			return linhaDoTempoMenu.get(ponteiroDoMenu - 1);
		}
		throw new IllegalStateException("Não há menu anterior.");
	}

	public void definirProximoMenu(Menu menu) {
		if (verificarDuplicacao(menu)) {
			return;
		}
		if (ponteiroDoMenu < linhaDoTempoMenu.size() - 1) {
			linhaDoTempoMenu = new ArrayList<>(linhaDoTempoMenu.subList(0, ponteiroDoMenu + 1));
		}

		linhaDoTempoMenu.add(menu);
		apontarPonteiroParaFrente();
	}

	public Menu voltarMenu(Menu menu) {
		if (temAnterior()) {
			ponteiroDoMenu--;
			return sobscreverMenu(menu);
		}
		throw new IllegalStateException("Não existe menu anterior");
	}

	public Menu irProximoMenu() {
		if (temProximo()) {
			ponteiroDoMenu++;
			return pegarMenuAtual();
		}
		throw new IllegalStateException("Não existe próximo menu");
	}

	public Menu voltarMenu() {
		if (temAnterior()) {
			ponteiroDoMenu--;
			return pegarMenuAtual();
		}
		throw new IllegalStateException("Não existe menu anterior");
	}

	public boolean temProximo() {
		return ponteiroDoMenu < linhaDoTempoMenu.size() - 1;
	}

	public boolean temAnterior() {
		return ponteiroDoMenu > 0;
	}

	public void trocarMenuAtual(Menu menu) {
		if (ponteiroDoMenu >= 0 && ponteiroDoMenu < linhaDoTempoMenu.size()) {
			linhaDoTempoMenu.set(ponteiroDoMenu, menu);
		} else {
			throw new IllegalStateException("Ponteiro inválido para troca.");
		}
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

	public void voltarPonteiro(int quantidadeVoltar) {
		int novoIndice = ponteiroDoMenu - quantidadeVoltar;
		if (novoIndice >= 0) {
			ponteiroDoMenu = novoIndice;
		} else {
			throw new IllegalArgumentException("Não existe menu anterior");
		}
	}

	public void irParaFrentePonteiro(int quantidadeParaFrente) {
		int novoIndice = ponteiroDoMenu + quantidadeParaFrente;
		if (novoIndice < linhaDoTempoMenu.size()) {
			ponteiroDoMenu = novoIndice;
		} else {
			throw new IllegalArgumentException("Não existe próximo menu");
		}
	}

	public boolean verificarDuplicacao(Menu menu) {
		return pegarMenuAtual().equals(menu);
	}
}
