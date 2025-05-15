package service.formatacao;

public class FormatacaoFiltro {

    public String formatarFiltrosAtivados() {
        StringBuilder formatado = new StringBuilder();
        int numero = 1;


        return formatado.length() > 0 ? formatado.toString() : "Nenhum filtro ativo.\n";
    }
	
}
