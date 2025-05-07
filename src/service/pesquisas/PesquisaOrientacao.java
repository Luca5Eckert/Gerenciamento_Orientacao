package service.pesquisas;

import java.util.List;

import dtos.OrientacaoDto;
import service.exceptions.orientacao.OrientacaoException;

public interface PesquisaOrientacao {
	List<OrientacaoDto> aplicarPesquisa(List<OrientacaoDto> orientacoes, String palavraPesquisada) throws OrientacaoException;
}
