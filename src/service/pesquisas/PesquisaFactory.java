package service.pesquisas;

public class PesquisaFactory {

	public static PesquisaOrientacao toTitulo() {
		return new PesquisaOrientacaoTitulo();
	}
	
	public static PesquisaOrientacao toConteudo() {
		return new PesquisaOrientacaoConteudo();
	}
	

}
