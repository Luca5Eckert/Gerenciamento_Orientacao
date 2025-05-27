package service.testes;

import aplication.implementacoes.InglesImplementacao;
import repositorio.UsuarioRepositorio;
import service.Validacoes;

public class ValidarEmailTeste {

	public static void main(String[] args) {
		
		var validacao = Validacoes.toEmail("lucas@dominio", new UsuarioRepositorio(), new InglesImplementacao());
	
		
		try {
			validacao.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
