package service;

import java.util.concurrent.CompletableFuture;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.CadastroSenhaException;
import service.exceptions.usuario.CadastroUsuarioJaExistenteException;

public class CadastroService {

	public boolean validarUsuario(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio,
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
