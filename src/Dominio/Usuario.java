package Dominio;

import java.util.Objects;

public class Usuario {
	
	private int idUsuario;
	private String nome;
	private String email;
	private String senha;
	private NivelAcesso nivelAcesso;

	public Usuario() {
	}

	public Usuario(int idUsuario, String nome, String email, String senha, NivelAcesso nivelAcesso) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.setNivelAcesso(nivelAcesso);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Usuario outro = (Usuario) obj;
		return this.nome.equalsIgnoreCase(outro.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome.toLowerCase());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

}
