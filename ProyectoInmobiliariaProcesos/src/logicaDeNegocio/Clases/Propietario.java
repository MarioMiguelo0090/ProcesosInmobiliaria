package logicaDeNegocio.Clases;

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return Objects.equals(estadoPropietario, that.estadoPropietario) &&
               Objects.equals(usuario, that.usuario);
    }
    
}
