package repositorio;

import java.sql.SQLException;

import Dominio.IdiomaOrientacao;
import Dominio.Orientacao;
import Dominio.OrientacaoId;
import Dominio.TipoOrientacao;
import infrastructure.dao.OrientacaoDAO;

public class OrientacaoRepositorio {
	private final OrientacaoDAO orientacaoDAO;

	public OrientacaoRepositorio() {
		this.orientacaoDAO = new OrientacaoDAO();
	}

	public Orientacao[] getOrientacaoRepositorio() {
		try {
			return orientacaoDAO.buscarTodas();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar orientações: " + e.getMessage());
		}
		return new Orientacao[0]; // Retorna array vazio em vez de null
	}

	public void adicionarOrientacao(Orientacao orientacaoModelo) {
		try {
			orientacaoDAO.salvar(orientacaoModelo);
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar orientação: " + e.getMessage());
		}
	}

	public void removerOrientacao(Orientacao orientacaoModelo) {
		try {
			orientacaoDAO.remover(orientacaoModelo);
		} catch (SQLException e) {
			System.out.println("Erro ao remover orientação: " + e.getMessage());
		}
	}

	public void removerOrientacao(OrientacaoId idOrientacao) {
		try {
			orientacaoDAO.remover(idOrientacao);
		} catch (SQLException e) {
			System.out.println("Erro ao remover orientação: " + e.getMessage());
		}
	}

	public boolean atualizarOrientacao(Orientacao orientacao) {
		try {
			orientacaoDAO.atualizar(orientacao);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar orientação: " + e.getMessage());
			return false;
		}
	}

	public Orientacao pegarOrientacaoPorIdeIdioma(String indexOrientacao, String idiomaOrientacao) {
		try {
			return orientacaoDAO.buscarPorId(indexOrientacao, idiomaOrientacao);
		} catch (SQLException e) {
			System.out.println("Erro ao pegar orientação pelo ID: " + e.getMessage());
			return null;
		}
	}

	public String pegarIndexOrientacao(Orientacao orientacao) {
		try {
			return orientacaoDAO.obterIdOrientacao(orientacao);
		} catch (SQLException e) {
			System.out.println("Erro ao pegar índice da orientação: " + e.getMessage());
		}
		return null;
	}

	public int pegarIndexOrientacaoIdioma(IdiomaOrientacao idiomaOrientacao) {
		try {
			return orientacaoDAO.obterProximoIdOrientacaoIdioma(idiomaOrientacao);
		} catch (SQLException e) {
			System.out.println("Erro ao pegar índice da orientação por idioma: " + e.getMessage());
		}
		return -1;
	}

	public void atualizarIdioma(String idOrientacao, IdiomaOrientacao idiomaAntigo, IdiomaOrientacao novoIdiomaOrientacao) {
		try {
			orientacaoDAO.atualizarIdiomaPorId(idOrientacao, idiomaAntigo.name(), novoIdiomaOrientacao.name());
		} catch(SQLException se) {
			System.out.println("Erro ao atualizar idioma da orientação: " + se.getMessage());
		}
	}
	
	public boolean verificarIdiomaOrientacao(String idOrientacao, IdiomaOrientacao idiomaOrientacao) {
		try {
			return orientacaoDAO.buscarPorId(idOrientacao, idiomaOrientacao.name()) == null;
		} catch (SQLException se) {
			System.out.println("Erro ao pegar orientacao por id e idioma: " + se.getMessage());
		}
		return false;
	}

	public void atualizarTiposOrientacoes(String idOrientacao, TipoOrientacao tipoOrientacao) {
		try {
			orientacaoDAO.definirTipoPorId(idOrientacao, tipoOrientacao.name());
		} catch (SQLException se) {
			System.out.println("Erro ao definir tipos para orientações: " + se.getMessage());
		}
	}
}
