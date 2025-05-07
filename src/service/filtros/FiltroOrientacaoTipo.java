package service.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;

public class FiltroOrientacaoTipo implements FiltroOrientacao {
    private List<TipoOrientacao> tiposOrientacao = new ArrayList<>();

    public FiltroOrientacaoTipo(List<TipoOrientacao> tiposOrientacao) {
        this.tiposOrientacao = tiposOrientacao;
    }

    public FiltroOrientacaoTipo(TipoOrientacao tipoOrientacao) {
		this.tiposOrientacao.add(tipoOrientacao);
	}

	public FiltroOrientacaoTipo() {
	}

	@Override
    public List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> orientacoes) {
        return orientacoes.stream()
                .filter(this::filtrarPorTipo)
                .collect(Collectors.toList());
    }

    private boolean filtrarPorTipo(OrientacaoDto orientacao) {
        return tiposOrientacao.contains(orientacao.tipoOrientacao());
    }

    @Override
    public String toString() {
        return "Filtro de Tipo: " + tiposOrientacao;
    }

    public List<TipoOrientacao> getTiposOrientacao() {
        return tiposOrientacao;
    }

    public void setTiposOrientacao(List<TipoOrientacao> tiposOrientacao) {
        this.tiposOrientacao = tiposOrientacao;
    }

    // Adiciona um tipo ao filtro
    public void adicionarTipo(TipoOrientacao tipo) {
        if (!tiposOrientacao.contains(tipo)) {
            tiposOrientacao.add(tipo);
        }
    }

    // Limpa todos os filtros de tipos
    public void limparFiltros() {
        tiposOrientacao.clear();
    }
}
