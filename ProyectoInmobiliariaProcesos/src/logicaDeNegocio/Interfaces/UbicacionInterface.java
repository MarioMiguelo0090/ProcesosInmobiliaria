package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Ubicacion;

public interface UbicacionInterface {
    public int registrarUbicacion(Ubicacion ubicacion);
    public List<Ubicacion> consultarUbicaciones();   
    public int consultarIdUbicacionPorEstado(String estado);
    public Ubicacion obtenerUbicacionPorId(int idUbicacion);
}
