package dtos;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;

public record OrientacaoDto(String id, String titulo, TipoOrientacao tipoOrientacao, String conteudo,
		IdiomaOrientacao idiomaOrientacao) {

}
