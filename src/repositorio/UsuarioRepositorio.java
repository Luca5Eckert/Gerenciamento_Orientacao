package repositorio;

import java.sql.SQLException;
import Dominio.Usuario;
import infrastructure.dao.UsuarioDAO;

public class UsuarioRepositorio {

	private final UsuarioDAO usuarioDAO;
	private static final int LIMITE_USUARIOS = 20;

	public UsuarioRepositorio() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public Usuario[] getUsuarios() {
		try {
			return usuarioDAO.buscarTodos(); 
		} catch (SQLException e) {
			System.out.println("Erro ao buscar usuários: " + e.getMessage());
		}
		return new Usuario[0];
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
			if (usuario != null) {
				usuarioDAO.remover(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao remover usuário pelo índice: " + e.getMessage());
		}
	}

	public void atualizarUsuario(int index, Usuario usuario) {
		try {
			Usuario existente = pegarUsuario(index);
			if (existente != null) {
				usuario.setNome(existente.getNome());
				usuarioDAO.atualizar(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar usuário: " + e.getMessage());
		}
	}

	public Usuario pegarUsuario(int index) {
		try {
			Usuario[] usuarios = usuarioDAO.buscarTodos();
			if (index >= 0 && index < usuarios.length) {
				return usuarios[index];
			}
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

	public Usuario pegarUsuarioNome(String nome) {
		try {
			return usuarioDAO.buscarPorNome(nome);
		} catch (SQLException e) {
			System.out.println("Erro ao pegar usuário pelo nome: " + e.getMessage());
		}
		return null;
	}

	public boolean verificaSeUsuarioExisteNome(String nome) {
		return pegarUsuarioNome(nome) != null;
	}
	
	public boolean verificaSeUsuarioExisteEmail(String email) {
		return pegarUsuarioEmail(email) != null;
	}

	public boolean verificarSeUsuarioExiste(Usuario usuario) {
		try {
			Usuario[] usuarios = usuarioDAO.buscarTodos();
			for (Usuario u : usuarios) {
				if (u != null && u.equals(usuario)) {
					return true;
				}
			}
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
