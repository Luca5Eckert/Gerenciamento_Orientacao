package infrastructure.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Dominio.IdiomaOrientacao;
import Dominio.Orientacao;
import Dominio.OrientacaoId;
import Dominio.TipoOrientacao;
import infrastructure.ConexaoFactory;

public class OrientacaoDAO {

    public List<Orientacao> buscarTodas() throws SQLException {
        List<Orientacao> orientacoes = new ArrayList<>();
        String sql = "SELECT * FROM orientacao";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

        	while (rs.next()) {
        	    Orientacao orientacao = new Orientacao();

        	    OrientacaoId id = new OrientacaoId();
        	    id.setIdOrientacao(rs.getString("id"));
        	    id.setIdiomaOrientacao(IdiomaOrientacao.valueOf(rs.getString("idioma_orientacao")));
        	    orientacao.setIdOrientacao(id); 

        	    orientacao.setTitulo(rs.getString("titulo"));
        	    orientacao.setConteudo(rs.getString("conteudo"));
        	    orientacao.setTipoOrientacao(TipoOrientacao.valueOf(rs.getString("tipo_orientacao")));

        	    orientacoes.add(orientacao);
        	}

        }
        return orientacoes;
    }

    public Orientacao buscarPorId(String id, String idiomaOrientacao) throws SQLException {
        String sql = "SELECT * FROM orientacao WHERE id = ? AND idioma_orientacao = ?";
        Orientacao orientacao = null;

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.setString(2, idiomaOrientacao);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orientacao = new Orientacao();
                    OrientacaoId orientacaoId = new OrientacaoId();
                    
                    orientacaoId.setIdOrientacao(rs.getString("id"));
                    orientacaoId.setIdiomaOrientacao(IdiomaOrientacao.valueOf(rs.getString("idioma_orientacao")));
                    
                    orientacao.setIdOrientacao(orientacaoId);  

                    orientacao.setTitulo(rs.getString("titulo"));
                    orientacao.setConteudo(rs.getString("conteudo"));
                    orientacao.setTipoOrientacao(TipoOrientacao.valueOf(rs.getString("tipo_orientacao")));
                }
            }
        }
        return orientacao;
    }


    public void salvar(Orientacao orientacao) throws SQLException {
        String sql = "INSERT INTO orientacao (id, titulo, tipo_orientacao, conteudo, idioma_orientacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orientacao.getIdOrientacao().getIdOrientacao());
            stmt.setString(2, orientacao.getTitulo());
            stmt.setString(3, orientacao.getTipoOrientacao().name());
            stmt.setString(4, orientacao.getConteudo());
            stmt.setString(5, orientacao.getIdOrientacao().getIdiomaOrientacao().name());

            stmt.executeUpdate();
        }
    }

    public void atualizar(Orientacao orientacao) throws SQLException {
        String sql = "UPDATE orientacao SET titulo = ?, tipo_orientacao = ?, conteudo = ? WHERE id = ? AND idioma_orientacao = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orientacao.getTitulo());
            stmt.setString(2, orientacao.getTipoOrientacao().name());
            stmt.setString(3, orientacao.getConteudo());
            stmt.setString(4, orientacao.getIdOrientacao().getIdOrientacao());
            stmt.setString(5, orientacao.getIdOrientacao().getIdiomaOrientacao().name());

            stmt.executeUpdate();
        }
    }

    public void remover(Orientacao orientacao) throws SQLException {
        String sql = "DELETE FROM orientacao WHERE id = ? AND idioma_orientacao = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orientacao.getIdOrientacao().getIdOrientacao());
            stmt.setString(2, orientacao.getIdOrientacao().getIdiomaOrientacao().name());

            stmt.executeUpdate();
        }
    }

    public int obterProximoIdOrientacaoIdioma(IdiomaOrientacao idioma) throws SQLException {
        String sql = "SELECT MAX(id) FROM orientacao WHERE idioma_orientacao = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idioma.name());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maxId = rs.getInt(1);
                    return maxId + 1;
                }
                return 1;
            }
        }
    }

    public String obterIdOrientacao(Orientacao orientacao) throws SQLException {
        String sql = "SELECT id FROM orientacao WHERE titulo = ? AND conteudo = ? AND idioma_orientacao = ? AND  tipo_orientacao = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

        	stmt.setString(1, orientacao.getTitulo());
        	stmt.setString(2, orientacao.getConteudo());
        	stmt.setString(3, orientacao.getIdOrientacao().getIdiomaOrientacao().name());
        	stmt.setString(4, orientacao.getTipoOrientacao().name());
        	
        	try( ResultSet resultSet = stmt.executeQuery()){
        		
        		if(resultSet.next()) {
        			String idOrientacao = resultSet.getString(1);
        			return idOrientacao;
        		}
        	}
        }
        
        return null;
    }
}
