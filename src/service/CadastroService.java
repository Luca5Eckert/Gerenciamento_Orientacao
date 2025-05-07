package service;

import java.util.regex.Pattern;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.CadastroSenhaException;
import service.exceptions.usuario.CadastroUsuarioJaExistenteException;

public class CadastroService {

	CadastroService() {

	}

	public boolean validarUsuarioEmail(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao)
			throws CadastroUsuarioJaExistenteException, CadastroSenhaException {
		
		return validarSeEhNovo(usuarioDto, usuarioRepositorio, idiomaImplementacao)
				&& validarSenha(usuarioDto, idiomaImplementacao);
	}

	public boolean validarSeEhNovo(UsuarioDto usuarioDto, UsuarioRepositorio usuarioRepositorio,
			IdiomaImplementacao idiomaImplementacao) throws CadastroUsuarioJaExistenteException {
		if (usuarioRepositorio.verificaSeUsuarioExisteNome(usuarioDto.nome())) {
			throw new CadastroUsuarioJaExistenteException(idiomaImplementacao.pegarMensagemErroCadastroUsuarioExistente());
		}
		return true;
		
	}

	public boolean validarSenha(UsuarioDto usuarioDto, IdiomaImplementacao idiomaImplementacao)
			throws CadastroSenhaException {
		return validarTamanhoSenha(usuarioDto.senha(), idiomaImplementacao)
				&& validarLetraMaiusculaSenha(usuarioDto.senha(), idiomaImplementacao)
				&& validarCaracterEspecial(usuarioDto.senha(), idiomaImplementacao);
	}

	public boolean validarTamanhoSenha(String senhaUsuario, IdiomaImplementacao idiomaImplementacao)
			throws CadastroSenhaException {
		if (senhaUsuario.length() >= 8) {
			return true;
		}

		throw new CadastroSenhaException(idiomaImplementacao.pegarMensagemErroCadastroSenhaPequena());
	}

	public boolean validarLetraMaiusculaSenha(String senhaUsuario, IdiomaImplementacao idiomaImplementacao)
			throws CadastroSenhaException {
		final Pattern MAIUSCULA = Pattern.compile("[A-Z]");

		if (MAIUSCULA.matcher(senhaUsuario).find()) {
			return true;
		}

		throw new CadastroSenhaException(idiomaImplementacao.pegarMensagemErroCadastroSenhaSemMaiscula());

	}

	public boolean validarCaracterEspecial(String senhaUsuario, IdiomaImplementacao idiomaImplementacao)
			throws CadastroSenhaException {
		final Pattern CARACTER_ESPECIAL = Pattern.compile("[^a-zA-Z0-9]");

		if (CARACTER_ESPECIAL.matcher(senhaUsuario).find()) {
			return true;
		}

		throw new CadastroSenhaException(idiomaImplementacao.pegarMensagemErroCadastroSenhaSemCaracterEspecial());

	}
}
