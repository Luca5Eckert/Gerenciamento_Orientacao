package infrastructure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import infrastructure.ConexaoFactory;
import service.SessaoUsuario;

public class RegistroLoginDAO {

	public void registrarSessaoLogin(SessaoUsuario sessaoUsuario) throws SQLException {
		String sql = "INSERT INTO registroSessaoLogin ( id_usuario ) values ( ? ) ";

		try (Connection conexao = ConexaoFactory.getConnection();
				PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, sessaoUsuario.pegarIdUsuario());
			
			statement.executeUpdate();
		}
	}
}
