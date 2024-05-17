package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Propiedad;

public interface PropiedadInterface {
    public int registrarPropiedad(Propiedad propiedad);
    public int modificarNombrePropiedad(Propiedad propiedad, String nombre);
    public int modificarNumeroHabitacionesPropiedad(Propiedad propiedad, int numeroHabitaciones);
    public int modificarNumeroDeBaniosPropiedad(Propiedad propiedad, int numeroDeBanios);
    public int modificarNumeroDePisosPropiedad(Propiedad propiedad, int numeroDePisos);
    public int modificarAntiguedadPropiedad(Propiedad propiedad, int antiguedad);
    public int modificarMetrosDeTerreno(Propiedad propiedad, float metrosDeTerreno);
    public int modificarPrecioTerreno(Propiedad propiedad, float precio);
    public int moodificarEstadoPopiedad(Propiedad propiedad, String estadoPropiedad);
    public List<Propiedad> consultarPropiedades();
    public List<Propiedad> consultarPropiedadPorTipo(int tipoPropiedad);
    public List<Propiedad> consultarPropiedadPorUbicacion(String ubicacion);
}
