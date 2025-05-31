package dtos;

import Dominio.NivelAcesso;

public record UsuarioDto(String nome, String email, String senha, NivelAcesso nivelAcesso) {

}
