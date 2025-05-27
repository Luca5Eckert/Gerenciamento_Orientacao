package service;

import java.util.concurrent.CompletableFuture;

import Dominio.Usuario;
import aplication.implementacoes.IdiomaImplementacao;
import infrastructure.security.UsuarioSecurity;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.CadastroSenhaException;
import service.exceptions.usuario.CadastroUsuarioJaExistenteException;

public class CadastroService {

	private UsuarioSecurity seguranca;

	public CadastroService() {
		this.seguranca = new UsuarioSecurity();
	}

	public boolean realizarCadastro(Usuario usuario, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) {
		validarUsuario(usuario, idiomaImplementacao, usuarioRepositorio);
		
		String senhaHash = seguranca.criptogramarSenha(usuario.getSenha());
		
		usuario.setSenha(senhaHash);
		
		usuarioRepositorio.adicionarUsuario(usuario);
		return true;

	}

	public boolean validarUsuario(Usuario usuario, IdiomaImplementacao idiomaImplementacao,
			UsuarioRepositorio usuarioRepositorio) throws CadastroUsuarioJaExistenteException, CadastroSenhaException {

		CompletableFuture<Void> validarNome = CompletableFuture
				.runAsync(() -> validarNomeUsuario(usuario.getNome(), idiomaImplementacao));

		CompletableFuture<Void> validarEmail = CompletableFuture
				.runAsync(() -> validarEmailUsuario(usuario.getEmail(), idiomaImplementacao, usuarioRepositorio));

		CompletableFuture<Void> validarSenha = CompletableFuture
				.runAsync(() -> validarSenhaUsuario(usuario.getSenha(), idiomaImplementacao));

		CompletableFuture.allOf(validarNome, validarEmail, validarSenha).join();

		return true;

	}

	private void validarNomeUsuario(String nomeUsuario, IdiomaImplementacao idiomaImplementacao) {
		Validacoes.toUsuario(nomeUsuario, idiomaImplementacao).run();
	}

	private void validarEmailUsuario(String emailUsuario, IdiomaImplementacao idiomaImplementacao,
			UsuarioRepositorio usuarioRepositorio) {
		Validacoes.toEmail(emailUsuario, usuarioRepositorio, idiomaImplementacao).run();
	}

	private void validarSenhaUsuario(String senhaUsuario, IdiomaImplementacao idiomaImplementacao) {
		Validacoes.toSenha(senhaUsuario, idiomaImplementacao).run();
	}

}
