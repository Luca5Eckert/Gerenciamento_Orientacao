package service.pesquisas;

public class PesquisaFactory {

	public static PesquisaOrientacao pegarPesquisa(PesquisaTipo pesquisaTipo) {
		return switch(pesquisaTipo) {
		case PesquisaTipo.TITULO -> new PesquisaOrientacaoTitulo();
		default -> new PesquisaOrientacaoTitulo();
		};
	}
}
