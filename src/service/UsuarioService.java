package service;

import Dominio.Usuario;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.CadastroSenhaException;
import service.exceptions.usuario.CadastroUsuarioJaExistenteException;
import service.exceptions.usuario.LoginException;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class UsuarioService {
	private final UsuarioRepositorio usuarioRepositorio;
	private final IdiomaImplementacao idiomaImplementacao;
	private final CadastroService cadastroService;
	private final LoginService loginService;

	public UsuarioService(UsuarioRepositorio usuarioRepositorio, IdiomaImplementacao idiomaImplementacao) {
		this.usuarioRepositorio = usuarioRepositorio;
		this.idiomaImplementacao = idiomaImplementacao;
		this.cadastroService = new CadastroService();
		this.loginService = new LoginService();
	}

	public boolean realizarLogin(UsuarioDto usuarioDto) throws LoginException {
		if (!loginService.validarUsuarioExistente(usuarioDto, usuarioRepositorio)) {
			throw new LoginUsuarioException(idiomaImplementacao.pegarMensagemErroLoginUsuario());

		}
		if (!loginService.validarSenha(usuarioDto, usuarioRepositorio)) {
			throw new LoginSenhaException(idiomaImplementacao.pegarMensagemErroLoginSenha());
		}

		return true;
	}

	public CadastroService getCadastroService() {
		return cadastroService;
	}

	public boolean realizarCadastro(UsuarioDto usuarioDto)
			throws CadastroUsuarioJaExistenteException, CadastroSenhaException {
		cadastroService.validarUsuarioEmail(usuarioDto, usuarioRepositorio, idiomaImplementacao);

		Usuario usuario = converterDtoParaUsuario(usuarioDto);
		this.usuarioRepositorio.adicionarUsuario(usuario);

		return true;
	}

	private Usuario converterDtoParaUsuario(UsuarioDto dto) {
		return new Usuario(0, dto.email(), dto.nome(), dto.senha());
	}
	
	private Usuario converterDtoParaUsuario(UsuarioDto dto, int idUsuario) {
		return new Usuario(idUsuario, dto.email(), dto.nome(), dto.senha());
	}

}
