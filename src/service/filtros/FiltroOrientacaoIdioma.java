package service.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Dominio.IdiomaOrientacao;
import dtos.OrientacaoDto;
import service.SessaoUsuario;

public class FiltroOrientacaoIdioma implements FiltroOrientacao<IdiomaOrientacao> {
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
	public List<IdiomaOrientacao> pegarFiltro() {
		return idiomasOrientacoes;
	}
	@Override
	public void adicionarFiltro(IdiomaOrientacao filtro) {
		idiomasOrientacoes.add(filtro);
	}


    private boolean filtrarPorIdioma(OrientacaoDto orientacao) {
        return idiomasOrientacoes.contains(orientacao.idiomaOrientacao());
    }

    public String pegarIdiomas() {
    	var idioma = SessaoUsuario.pegarIdioma();
        return idioma.pegarFiltroIdioma() + mostrarIdiomas(idioma.obterIdiomaOrientacao());
    }
    
    public String mostrarIdiomas(IdiomaOrientacao idiomaOrientacao) {
    	StringBuilder formatadoMostrarIdiomas = new StringBuilder();
    	idiomasOrientacoes.stream().forEach(i -> formatadoMostrarIdiomas.append(i.getNomePorIdioma(idiomaOrientacao) + ", "));
    	return formatadoMostrarIdiomas.toString();
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

	@Override
	public List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> orientacoes) {
        return orientacoes.stream()
                .filter(this::filtrarPorIdioma)
                .collect(Collectors.toList());
	}

}
