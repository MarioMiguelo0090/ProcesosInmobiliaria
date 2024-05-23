package logicaDeNegocio.Clases;


public final class LoginSingleton {
    private static LoginSingleton instance;
    private int idUsuario;

    private LoginSingleton() {
        
    }

    public static LoginSingleton getInstance() {
        if (instance == null) {
            instance = new LoginSingleton();
        }
        return instance;
    }
    
    public void borrarInstancia() {
        idUsuario = -1;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
