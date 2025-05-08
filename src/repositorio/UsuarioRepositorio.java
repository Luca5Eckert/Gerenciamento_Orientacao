package repositorio;

import java.sql.SQLException;
import java.util.List;
import Dominio.Usuario;
import infrastructure.dao.UsuarioDAO;

public class UsuarioRepositorio {

	private final UsuarioDAO usuarioDAO;

	public UsuarioRepositorio() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public List<Usuario> getUsuarios() {
		try {
			return usuarioDAO.buscarTodos();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar usuários: " + e.getMessage());
		}
		return null;
	}

	public void adicionarUsuario(Usuario usuario) {
		try {
			usuarioDAO.salvar(usuario);
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar usuário: " + e.getMessage());
		}
	}

	public void removerUsuario(Usuario usuario) {
		try {
			usuarioDAO.remover(usuario);
		} catch (SQLException e) {
			System.out.println("Erro ao remover usuário: " + e.getMessage());
		}
	}

	public void removerUsuario(int index) {
		try {
			Usuario usuario = pegarUsuario(index);
			usuarioDAO.remover(usuario);
		} catch (SQLException e) {
			System.out.println("Erro ao remover usuário pelo índice: " + e.getMessage());
		}
	}

	public void atualizarUsuario(int index, Usuario usuario) {
		try {
			usuario.setNome(pegarUsuario(index).getNome());
			usuarioDAO.atualizar(usuario);
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar usuário: " + e.getMessage());
		}
	}

	public Usuario pegarUsuario(int index) {
		try {
			List<Usuario> usuarios = usuarioDAO.buscarTodos();
			return usuarios.get(index);
		} catch (SQLException e) {
			System.out.println("Erro ao pegar usuário pelo índice: " + e.getMessage());
		}
		return null;
	}

	public Usuario pegarUsuarioEmail(String email) {
		try {
			return usuarioDAO.pegarPeloEmail(email);
		} catch (SQLException se) {
			System.out.println("Erro ao pegar usuário pelo email: " + se.getMessage());
		}
		return null;
	}

	public Usuario pegarUsuario(String nome) {
		try {
			return usuarioDAO.buscarPorNome(nome);
		} catch (SQLException e) {
			System.out.println("Erro ao pegar usuário pelo nome: " + e.getMessage());
		}
		return null;
	}

	public boolean verificaSeUsuarioExisteNome(String nome) {
		return pegarUsuario(nome) != null;
	}

	public boolean verificarSeUsuarioExiste(Usuario usuario) {
		try {
			return usuarioDAO.buscarTodos().contains(usuario);
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se o usuário existe: " + e.getMessage());
		}
		return false;
	}

	public int obterId() {
		try {
			return usuarioDAO.obterProximoIdUsuario();
		} catch (SQLException e) {
			System.out.println("Erro ao obter próximo ID de usuário: " + e.getMessage());
		}
		return -1;
	}

}
