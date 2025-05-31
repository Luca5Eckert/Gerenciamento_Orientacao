package infrastructure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import infrastructure.ConexaoFactory;
import service.commandos.RegistroComando;

public class RegistroComandoDAO {

	public void salvarRegistroComando(RegistroComando registroComando) throws SQLException {
		String consulta = "INSERT INTO registroComando (id_orientacao, idioma_orientacao, id_usuario, tipo_comando) values (?,?,?,?)";

		try (Connection conexao = ConexaoFactory.getConnection();
				PreparedStatement statement = conexao.prepareStatement(consulta)) {

			statement.setString(1, registroComando.getIdOrientacao());
			statement.setString(2, registroComando.getIdiomaOrientacao().name());
			statement.setInt(3, registroComando.getIdUsuario());
			statement.setString(4, registroComando.getTipoComando().name());

			statement.executeUpdate();

		}

	}

}
