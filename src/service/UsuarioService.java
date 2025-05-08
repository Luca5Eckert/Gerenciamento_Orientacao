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

	public UsuarioService(UsuarioRepositorio usuarioRepositorio, IdiomaImplementacao idiomaImplementacao) {
		this.usuarioRepositorio = usuarioRepositorio;
		this.idiomaImplementacao = idiomaImplementacao;
		this.cadastroService = new CadastroService();
	}

	public boolean realizarLogin(UsuarioDto usuarioDto) throws LoginException {
		validarUsuarioExistente(usuarioDto);
		validarSenha(usuarioDto);
		return true;
	}

	public CadastroService getCadastroService() {
		return cadastroService;
	}

	private void validarUsuarioExistente(UsuarioDto usuarioDto) throws LoginUsuarioException {
		boolean existe = usuarioRepositorio.verificaSeUsuarioExisteNome(usuarioDto.nome());

		if (!existe) {
			throw new LoginUsuarioException(idiomaImplementacao.pegarMensagemErroLoginUsuario());
		}
	}

	private void validarSenha(UsuarioDto usuarioDto) throws LoginSenhaException {
		Usuario usuario = usuarioRepositorio.pegarUsuario(usuarioDto.nome());

		if (!senhaEhValida(usuarioDto.senha(), usuario.getSenha())) {
			throw new LoginSenhaException(idiomaImplementacao.pegarMensagemErroLoginSenha());
		}
	}

	private boolean senhaEhValida(String senhaInformada, String senhaCadastrada) {
		return senhaInformada.equals(senhaCadastrada);
	}

	public boolean realizarCadastro(UsuarioDto usuarioDto) throws CadastroUsuarioJaExistenteException, CadastroSenhaException {
		cadastroService.validarUsuarioEmail(usuarioDto, usuarioRepositorio, idiomaImplementacao);

		Usuario usuario = converterDtoParaUsuario(usuarioDto);
		this.usuarioRepositorio.adicionarUsuario(usuario);

		return true;
	}

	private Usuario converterDtoParaUsuario(UsuarioDto dto) {
		return new Usuario(0, dto.email(), dto.nome(), dto.senha());
	}
}
