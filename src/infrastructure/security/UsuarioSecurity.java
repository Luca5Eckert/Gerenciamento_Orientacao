package infrastructure.security;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioSecurity {

	public String criptogramarSenha(String senha) {
		 return BCrypt.hashpw(senha, BCrypt.gensalt(12));	
	}
	
	public boolean verificarSenha(String senha, String salt) {
	       return BCrypt.checkpw(senha, salt);
	}
	
}
