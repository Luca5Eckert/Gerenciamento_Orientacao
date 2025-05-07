package service.filtros;

import java.util.List;
import dtos.OrientacaoDto;;

public interface FiltroOrientacao {
	List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> orientacoes);
}
