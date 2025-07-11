package service.commandos;

import java.util.ArrayList;
import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;
import service.exceptions.ComandoHistoricoException;

public class ComandoHistorico {

	private List<Comando> listaDeComandos = new ArrayList<>();
	private int ponteiroComando = -1;

	public ComandoHistorico(List<Comando> listaDeComandos) {
		this.listaDeComandos = listaDeComandos;
		iniciarHistorico();
	}

	public void iniciarHistorico() {
		if(ponteiroComando==-1) {
			listaDeComandos.add(null);
			ponteiroComando++;
		}
	}
	
	public void adicionarComando(Comando comando) {
		
		if (validarPonteiroAdicionar()) {
			if (ponteiroComando < listaDeComandos.size() - 1) {
				listaDeComandos.subList(ponteiroComando + 1, listaDeComandos.size()).clear();
			}
			listaDeComandos.add(comando);
			ponteiroComando = listaDeComandos.size() - 1;
			return;
		}
		throw new ComandoHistoricoException();
	}

	public void voltarComando() {
		if (!validarPonteiro()) {
			throw new ComandoHistoricoException();
		}
		voltarPonteiro();
		return;
	}

	public void irParaFrenteComando() {
		if (!validarPonteiro()) {
			throw new ComandoHistoricoException();
		}
		irParaFrentePonteiro();
		return;
	}

	public boolean validarPonteiro() {
		return ponteiroComando >= 0 && ponteiroComando < listaDeComandos.size();
	}

	public boolean validarPonteiroAdicionar() {
		return (ponteiroComando + 1) >= 0 && (ponteiroComando + 1) <= listaDeComandos.size();
	}

	public void voltarPonteiro() {
		if (validarPonteiroVoltar()) {
			this.ponteiroComando -= 1;
			return;
		}
		throw new ComandoHistoricoException();
	}

	public void irParaFrentePonteiro() {
		if ((ponteiroComando + 1) < listaDeComandos.size()) {
			this.ponteiroComando += 1;
			return;
		}
		throw new ComandoHistoricoException();
	}

	public boolean validarPonteiroVoltar() {
		return ponteiroComando > 0;
	}

	public void apagarHistorico() {
		listaDeComandos.clear();
		this.ponteiroComando = -1;
		iniciarHistorico();
	}

	public String gerarHistorico(IdiomaImplementacao idiomaImplementacao) {
		if (listaDeComandos.isEmpty()) {
			return idiomaImplementacao.pegarMensagemSemComandoDisponivel();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = listaDeComandos.size() - 1; i >= 0; i--) {
			String tipo = "";
			String indicadorAtual = (i == ponteiroComando) ? " <--- " : "";
			try {
				Comando comando = listaDeComandos.get(i);
				tipo = comando.pegarTipo().pegarIdioma(idiomaImplementacao.obterIdiomaOrientacao());
				
			} catch(Exception e) {
				tipo = "";
			}
			sb.append(String.format(" %d -  %s%s\n", i + 1, tipo, indicadorAtual));
		}

		sb.append("----------------------------");

		return sb.toString();
	}

	public Comando pegarComandoAtual() {
		try {
			return listaDeComandos.get(ponteiroComando);
		} catch (Exception e) {
			throw new ComandoHistoricoException();
		}
	}
}
