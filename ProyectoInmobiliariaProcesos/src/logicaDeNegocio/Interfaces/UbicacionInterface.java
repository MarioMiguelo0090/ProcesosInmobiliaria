package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;

public interface UbicacionInterface {
    public int registrarUbicacion(Ubicacion ubicacion);
    public List<Ubicacion> consultarUbicaciones();
    public Ubicacion consultarUbicacionPorID(int idUbicacion);
    public Ubicacion consultarUbicacionPorEstado(String estado);
}
