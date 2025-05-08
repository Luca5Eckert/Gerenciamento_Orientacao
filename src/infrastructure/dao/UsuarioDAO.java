package infrastructure.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Dominio.Usuario;
import infrastructure.ConexaoFactory;

public class UsuarioDAO {

    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email")); 
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario pegarPeloEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;

        try (Connection conexao = ConexaoFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(result.getInt("id"));
                    usuario.setEmail(result.getString("email"));  
                    usuario.setNome(result.getString("nome"));
                    usuario.setSenha(result.getString("senha"));
                }
            }

            return usuario;
        }
    }

    public Usuario buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        Usuario usuario = null;

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));  
                    usuario.setSenha(rs.getString("senha"));
                }
            }
        }
        return usuario;
    }

    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (id, nome, email, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());  
            stmt.setString(4, usuario.getSenha());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail()); 
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void remover(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE email = ?"; 

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());  
            stmt.executeUpdate();
        }
    }

    public int obterProximoIdUsuario() throws SQLException {
        String query = "SELECT MAX(id) FROM usuarios";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        }
    }
}
