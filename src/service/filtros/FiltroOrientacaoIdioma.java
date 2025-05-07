package service.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.IdiomaOrientacao;
import dtos.OrientacaoDto;

public class FiltroOrientacaoIdioma implements FiltroOrientacao {
    private List<IdiomaOrientacao> idiomasOrientacoes = new ArrayList<>();

    public FiltroOrientacaoIdioma(List<IdiomaOrientacao> idiomasOrientacoes) {
        this.idiomasOrientacoes = idiomasOrientacoes;
    }

    public FiltroOrientacaoIdioma(IdiomaOrientacao idiomaOrientacao) {
		this.idiomasOrientacoes.add(idiomaOrientacao);
	}

	public FiltroOrientacaoIdioma() {
	}

	@Override
    public List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> orientacoes) {
        return orientacoes.stream()
                .filter(this::filtrarPorIdioma)
                .collect(Collectors.toList());
    }

    private boolean filtrarPorIdioma(OrientacaoDto orientacao) {
        return idiomasOrientacoes.contains(orientacao.idiomaOrientacao());
    }

    @Override
    public String toString() {
        return "Filtro de Idioma: " + idiomasOrientacoes;
    }

    public List<IdiomaOrientacao> getIdiomasOrientacoes() {
        return idiomasOrientacoes;
    }

    public void setIdiomasOrientacoes(List<IdiomaOrientacao> idiomasOrientacoes) {
        this.idiomasOrientacoes = idiomasOrientacoes;
    }

  
    public void adicionarIdioma(IdiomaOrientacao idioma) {
        if (!idiomasOrientacoes.contains(idioma)) {
            idiomasOrientacoes.add(idioma);
        }
    }

    public void limparFiltros() {
        idiomasOrientacoes.clear();
    }
}
