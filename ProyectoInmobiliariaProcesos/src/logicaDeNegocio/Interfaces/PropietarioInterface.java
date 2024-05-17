package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Propietario;


public interface PropietarioInterface {
    public int agregarNuevoPropietario(Propietario propietario);
    public int cambiarEstadoPropietario(Propietario propietario, String estado);
    public int modificarNombrePropietario(Propietario propietario, String nombre);
    public int modificarApellidoPaternoPropietario(Propietario propietario, String apellidoPaterno);
    public int modificarApellidoMaternoPropietario(Propietario propietario, String apellidoMaterno);
    public int modificarCorreoPropietario(Propietario propietario, String correo);
    public int modificarRFCPropietario(Propietario propietario, String rfc);
    public List<Propietario> consultarPropietarios();
    public List<Propietario> consultarPropietariosPorEstado(String estadoProfesor);
}
