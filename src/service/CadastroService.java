package service;

import java.util.concurrent.CompletableFuture;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
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
		validarUsuario(usuarioDto, idiomaImplementacao);
		
		usuarioRepositorio.adicionarUsuario(usuarioDto);
	}

	public boolean validarUsuario(UsuarioDto usuarioDto,
			IdiomaImplementacao idiomaImplementacao)
			throws CadastroUsuarioJaExistenteException, CadastroSenhaException {

		CompletableFuture<Void> validarNome = CompletableFuture
				.runAsync(() -> validarNomeUsuario(usuarioDto.nome(), idiomaImplementacao));

		CompletableFuture<Void> validarEmail = CompletableFuture
				.runAsync(() -> validarEmailUsuario(usuarioDto.email(), idiomaImplementacao, usuarioRepositorio));

		CompletableFuture<Void> validarSenha = CompletableFuture
				.runAsync(() -> validarSenhaUsuario(usuarioDto.senha(), idiomaImplementacao));

		
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
