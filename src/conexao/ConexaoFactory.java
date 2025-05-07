package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_orientacao_db?allowPublicKeyRetrieval=true&useSSL=false";
	private static final String USUARIO = "lucas";
	private static final String SENHA = "lucas";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
	}

	public static void main(String[] args) {

		try (Connection conn = ConexaoFactory.getConnection()) {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar: " + e.getMessage());
		} catch (ClassNotFoundException e) {
		}

	}
}
