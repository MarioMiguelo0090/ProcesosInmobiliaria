package logicaDeNegocio.Clases;

public class Propietario {
    private int idPropietario;
    private String estadoPropietario;
    private Usuario usuario;

    public Propietario() {
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getEstadoPropietario() {
        return estadoPropietario;
    }

    public void setEstadoPropietario(String estadoPropietario) {
        this.estadoPropietario = estadoPropietario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
