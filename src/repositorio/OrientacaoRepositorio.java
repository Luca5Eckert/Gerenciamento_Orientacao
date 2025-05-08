package repositorio;

import java.sql.SQLException;
import java.util.List;

import dao.OrientacaoDAO;
import Dominio.IdiomaOrientacao;
import Dominio.Orientacao;
import Dominio.OrientacaoId;
import dtos.OrientacaoDto;

public class OrientacaoRepositorio {
    private final OrientacaoDAO orientacaoDAO;

    public OrientacaoRepositorio() {
        this.orientacaoDAO = new OrientacaoDAO();
    }

    public List<Orientacao> getOrientacaoRepositorio() {
        try {
            return orientacaoDAO.buscarTodas();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar orientações: " + e.getMessage());
        }
        return null;
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

    public boolean atualizarOrientacao(String indexOrientacao, IdiomaOrientacao idiomaOrientacao, OrientacaoDto orientacaoDto) {
        try {
            Orientacao nova = new Orientacao();
            OrientacaoId orientacaoId = new OrientacaoId(indexOrientacao, idiomaOrientacao);
            
            nova.setIdOrientacao(orientacaoId);
            nova.setTitulo(orientacaoDto.titulo());
            nova.setConteudo(orientacaoDto.conteudo()); 
            nova.setTipoOrientacao(orientacaoDto.tipoOrientacao());

            orientacaoDAO.atualizar(nova);
            
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
}
