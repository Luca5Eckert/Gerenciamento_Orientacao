package Dominio;

import java.util.Arrays;
import java.util.List;

public enum TipoOrientacao implements Filtro<TipoOrientacao> {
	MANUAL_OPERACAO("Manual de Operação", "Operation Manual", "Betriebsanleitung", "Manual de Operación", 1),
	PROCEDIMENTO_SEGURANCA("Procedimento de Segurança", "Safety Procedure", "Sicherheitsverfahren",
			"Procedimiento de Seguridad", 2),
	MANUTENCAO_REPAROS("Manutenção e Reparos", "Maintenance and Repairs", "Wartung und Reparaturen",
			"Mantenimiento y Reparaciones", 3),
	TESTES_DIAGNOSTICO("Testes e Diagnóstico", "Tests and Diagnostics", "Tests und Diagnosen", "Pruebas y Diagnóstico",
			4),
	MANUAL_CONDUTA_OPERACOES("Manual de Conduta e Operações Setoriais", "Manual of Conduct and Sector Operations",
			"Handbuch für Verhalten und Sektoroperationen", "Manual de Conducta y Operaciones Sectoriales", 5);

	private final String portuguesNome;
	private final String inglesNome;
	private final String alemaoNome;
	private final String espanholNome;
	private final int numero;

	TipoOrientacao(String portuguesNome, String inglesNome, String alemaoNome, String espanholNome, int numero) {
		this.portuguesNome = portuguesNome;
		this.inglesNome = inglesNome;
		this.alemaoNome = alemaoNome;
		this.espanholNome = espanholNome;
		this.numero = numero;
	}

	public String getNome(IdiomaOrientacao idiomaOrientacao) {
		return switch (idiomaOrientacao) {
		case PORTUGUES -> getNomePortugues();
		case INGLES -> getNomeIngles();
		case ALEMAO -> getNomeAlemao();
		case ESPANHOL -> getNomeEspanhol();
		};
	}

	public String getNomePortugues() {
		return portuguesNome;
	}

	public String getNomeIngles() {
		return inglesNome;
	}

	public String getNomeAlemao() {
		return alemaoNome;
	}

	public String getNomeEspanhol() {
		return espanholNome;
	}

	@Override
	public String toString() {
		return portuguesNome;
	}

	public int getNumero() {
		return numero;
	}
	
	public static List<TipoOrientacao> listarTipos() {
        return Arrays.asList(TipoOrientacao.values());
    }

	public static String mostrarTodasTipos(IdiomaOrientacao idiomaOrientacao) {
		StringBuilder todosTipos = new StringBuilder();
		int numeroTipo = 1;

		for (TipoOrientacao tipo : TipoOrientacao.values()) {
			todosTipos.append(numeroTipo).append(" - ").append(tipo.getNome(idiomaOrientacao)).append("\n");
			numeroTipo++;
		}
		return todosTipos.toString();
	}

	public static TipoOrientacao pegarOrientacao(int indexOrientacao) {
		for (TipoOrientacao tipo : TipoOrientacao.values()) {
			if (tipo.getNumero() == indexOrientacao) {
				return tipo;
			}
		}
		return TipoOrientacao.MANUAL_OPERACAO;
	}

	@Override
	public TipoOrientacao converterStringParaEnum(String palavra) {
		return valueOf(palavra);
	}

	@Override
	public List<TipoOrientacao> pegarValores() {
		return listarTipos();
	}

	@Override
	public TipoOrientacao pegarValor(int index) {
		return pegarOrientacao(index);
	}

	@Override
	public String pegarValorSegundoIdioma(IdiomaOrientacao idioma, int index) {
		TipoOrientacao tipoFiltro = pegarOrientacao(index);
		return tipoFiltro.getNome(idioma);
	}
	
	@Override
	public String pegarValorSegundoIdioma(IdiomaOrientacao idioma) {
		return getNome(idioma);
	}

	@Override
	public List<String> pegarValoresSegundoIdioma(IdiomaOrientacao idiomaOrientacao) {
		return Arrays.stream(values()).map(i -> i.getNome(idiomaOrientacao)).toList();
	}
	
}
