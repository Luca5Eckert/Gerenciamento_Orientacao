package service;

public class ValidarUsuario implements Validacao {

    private String usuarioParaValidar;

    public ValidarUsuario(String usuarioParaValidar) {
        this.usuarioParaValidar = usuarioParaValidar;
    }

    @Override
    public boolean validar() {
        return !comecaComEspaco() && !excedeLimiteCaracteres();
    }

    private boolean comecaComEspaco() {
        return usuarioParaValidar != null && usuarioParaValidar.startsWith(" ");
    }

    private boolean excedeLimiteCaracteres() {
        return usuarioParaValidar != null && usuarioParaValidar.length() > 15;
    }

}
