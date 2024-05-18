package logicaDeNegocio.Interfaces;

import logicaDeNegocio.Clases.Direccion;

public interface DireccionInterface {
    public int insertarDireccion(Direccion direccion);
    public int modificarCodigoPostal(Direccion direccion,String codigoPostal);
    public int modificarCalle(Direccion direccion, String calle);
    public int modificarEstado(Direccion direccion, String estado);
    public int modificarCiudad(Direccion direccion, String ciudad);
    public Direccion obtenerDireccionPorId(int idDireccion);
    public int modificarColonia(Direccion direccion, String colonia);
    public Direccion ObtenerUltimaDireccionRegistrada();
}
