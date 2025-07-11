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
		String sql = "SELECT * FROM orientacao WHERE ativo = 1";

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
				orientacao.setUsuarioCriador(rs.getInt("id_fk_usuario_criou"));
				orientacoes.add(orientacao);
			}
		}
		return orientacoes;
	}

	public Orientacao buscarPorId(String id, String idiomaOrientacao) throws SQLException {
		String sql = "SELECT * FROM orientacao WHERE id = ? AND idioma_orientacao = ? AND ativo = 1";
		Orientacao orientacao = null;

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
					orientacao.setUsuarioCriador(rs.getInt("id_fk_usuario_criou"));
				}
			}
		}
		return orientacao;
	}

	public Orientacao buscarPorIdDesativado(String id, String idiomaOrientacao) throws SQLException {
		String sql = "SELECT * FROM orientacao WHERE id = ? AND idioma_orientacao = ? AND ativo = FALSE";
		Orientacao orientacao = null;

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
					orientacao.setUsuarioCriador(rs.getInt("id_fk_usuario_criou"));
				}
			}
		}
		return orientacao;
	}

	public List<Orientacao> buscarPorIdUsuarioCriador(int idUsuarioCriador) throws SQLException {
		List<Orientacao> orientacoes = new ArrayList<>();
		String sql = "SELECT * FROM orientacao WHERE id_fk_usuario_criou = ? AND ativo = 1";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idUsuarioCriador);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Orientacao orientacao = new Orientacao();
					OrientacaoId id = new OrientacaoId();
					id.setIdOrientacao(rs.getString("id"));
					id.setIdiomaOrientacao(IdiomaOrientacao.valueOf(rs.getString("idioma_orientacao")));
					orientacao.setIdOrientacao(id);
					orientacao.setTitulo(rs.getString("titulo"));
					orientacao.setConteudo(rs.getString("conteudo"));
					orientacao.setTipoOrientacao(TipoOrientacao.valueOf(rs.getString("tipo_orientacao")));
					orientacao.setUsuarioCriador(rs.getInt("id_fk_usuario_criou"));
					orientacoes.add(orientacao);
				}
			}
		}
		return orientacoes;
	}

	public void salvar(Orientacao orientacao) throws SQLException {
		String sql = "INSERT INTO orientacao (id, titulo, tipo_orientacao, conteudo, idioma_orientacao, id_fk_usuario_criou, ativo) VALUES (?, ?, ?, ?, ?, ?, TRUE)";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, orientacao.getIdOrientacao().getIdOrientacao());
			stmt.setString(2, orientacao.getTitulo());
			stmt.setString(3, orientacao.getTipoOrientacao().name());
			stmt.setString(4, orientacao.getConteudo());
			stmt.setString(5, orientacao.getIdOrientacao().getIdiomaOrientacao().name());
			stmt.setInt(6, orientacao.getUsuarioCriador());
			stmt.executeUpdate();
		}
	}

	public void atualizar(Orientacao orientacao) throws SQLException {
		String sql = "UPDATE orientacao SET titulo = ?, tipo_orientacao = ?, conteudo = ?, id_fk_usuario_criou = ?, ativo = TRUE WHERE id = ? AND idioma_orientacao = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, orientacao.getTitulo());
			stmt.setString(2, orientacao.getTipoOrientacao().name());
			stmt.setString(3, orientacao.getConteudo());
			stmt.setInt(4, orientacao.getUsuarioCriador());
			stmt.setString(5, orientacao.getIdOrientacao().getIdOrientacao());
			stmt.setString(6, orientacao.getIdOrientacao().getIdiomaOrientacao().name());
			stmt.executeUpdate();
		}
	}

	public void remover(Orientacao orientacao) throws SQLException {
		remover(orientacao.getIdOrientacao());
	}

	public void remover(OrientacaoId idOrientacao) throws SQLException {
		String sql = "UPDATE orientacao SET ativo = FALSE WHERE id = ? AND idioma_orientacao = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idOrientacao.getIdOrientacao());
			stmt.setString(2, idOrientacao.getIdiomaOrientacao().name());
			stmt.executeUpdate();
		}
	}

	public int obterProximoIdOrientacaoIdioma(IdiomaOrientacao idioma) throws SQLException {
		String sql = "SELECT MAX(CAST(id AS UNSIGNED)) FROM orientacao WHERE idioma_orientacao = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
		String sql = "SELECT id FROM orientacao WHERE titulo = ? AND conteudo = ? AND idioma_orientacao = ? AND tipo_orientacao = ? AND ativo = TRUE";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, orientacao.getTitulo());
			stmt.setString(2, orientacao.getConteudo());
			stmt.setString(3, orientacao.getIdOrientacao().getIdiomaOrientacao().name());
			stmt.setString(4, orientacao.getTipoOrientacao().name());

			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString(1);
				}
			}
		}
		return null;
	}

	public void definirTipoPorId(String idOrientacao, String tipoOrientacao) throws SQLException {
		String sql = "UPDATE orientacao SET tipo_orientacao = ? WHERE id = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, tipoOrientacao);
			stmt.setString(2, idOrientacao);
			stmt.executeUpdate();
		}
	}

	public void atualizarIdiomaPorId(String idOrientacao, String idiomaAntigo, String novoIdioma) throws SQLException {
		String sql = "UPDATE orientacao SET idioma_orientacao = ? WHERE id = ? AND idioma_orientacao = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, novoIdioma);
			stmt.setString(2, idOrientacao);
			stmt.setString(3, idiomaAntigo);
			stmt.executeUpdate();
		}
	}

	public void removerPorId(String idOrientacao) throws SQLException {
		String sql = "DELETE FROM orientacao WHERE id = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idOrientacao);
			stmt.executeUpdate();
		}
	}

	public List<Orientacao> pegarOrientacoesPorId(String idOrientacao) throws SQLException {
		List<Orientacao> orientacoes = new ArrayList<>();
		String sql = "SELECT * FROM orientacao WHERE id = ? AND ativo = TRUE";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idOrientacao);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Orientacao orientacao = new Orientacao();
					OrientacaoId id = new OrientacaoId();
					id.setIdOrientacao(rs.getString("id"));
					id.setIdiomaOrientacao(IdiomaOrientacao.valueOf(rs.getString("idioma_orientacao")));
					orientacao.setIdOrientacao(id);
					orientacao.setTitulo(rs.getString("titulo"));
					orientacao.setConteudo(rs.getString("conteudo"));
					orientacao.setTipoOrientacao(TipoOrientacao.valueOf(rs.getString("tipo_orientacao")));
					orientacao.setUsuarioCriador(rs.getInt("id_fk_usuario_criou"));
					orientacoes.add(orientacao);
				}
			}
		}
		return orientacoes;
	}

	public int pegarIdCriadorOrientacao(String idOrientacao, IdiomaOrientacao idiomaOrientacao) throws SQLException {
		String sql = "SELECT id_fk_usuario_criou FROM orientacao WHERE id = ? AND idioma_orientacao = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idOrientacao);
			stmt.setString(2, idiomaOrientacao.name());

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id_fk_usuario_criou");
				}
			}
		}
		return -1;
	}

	public void desfazerRemocaoOrientacao(String idOrientacao, IdiomaOrientacao idiomaOrientacao) throws SQLException {
		String sql = "UPDATE orientacao SET ativo = TRUE WHERE id = ? AND idioma_orientacao = ?";

		try (Connection conn = ConexaoFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, idOrientacao);
			stmt.setString(2, idiomaOrientacao.name());
			stmt.executeUpdate();
		}
	}

	public void atualizar(OrientacaoId orientacaoId, Orientacao orientacaoAlterada) throws SQLException {
		String sql = "UPDATE orientacao SET ativo = TRUE, titulo = ?, tipo_orientacao = ?, conteudo = ? "
				+ "WHERE id = ? AND idioma_orientacao = ?";

		try (Connection connection = ConexaoFactory.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, orientacaoAlterada.getTitulo());
			stmt.setString(2, orientacaoAlterada.getTipoOrientacao().name());
			stmt.setString(3, orientacaoAlterada.getConteudo());

			stmt.setString(4, orientacaoAlterada.getIdOrientacao().getIdOrientacao());
			stmt.setString(5, orientacaoAlterada.getIdOrientacao().getIdiomaOrientacao().name());

			stmt.executeUpdate();
		}

	}

	public boolean verificarTituloUnico(String titulo) throws SQLException {
		String consulta = "SELECT id_fk_usuario_criou FROM orientacao WHERE titulo = ? AND ativo = TRUE";

		try (Connection connection = ConexaoFactory.getConnection();
				PreparedStatement stmt = connection.prepareStatement(consulta)) {
			stmt.setString(1, titulo);

			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		}

	}
}
