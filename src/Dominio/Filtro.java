package Dominio;

import java.util.List;

public interface Filtro<T extends Enum<?>> {
	
	public T converterStringParaEnum(String palavra);
	
	public List<T> pegarValores();
	
	public T pegarValor(int index);
	
	public String pegarValorSegundoIdioma(IdiomaOrientacao idioma, int index);
	
	public String pegarValorSegundoIdioma(IdiomaOrientacao idioma);
}
