package Dominio;

public interface Filtro<T extends Enum<?>> {
	
	public T converterStringParaEnum(String palavra);
}
