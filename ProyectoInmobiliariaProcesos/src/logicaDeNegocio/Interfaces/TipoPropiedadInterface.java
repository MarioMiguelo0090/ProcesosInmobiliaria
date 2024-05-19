package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.TipoPropiedad;

public interface TipoPropiedadInterface {
    public int registrarTipoPropiedad(TipoPropiedad tipoPropiedad);
    public List<TipoPropiedad> consultarTiposPropiedad();  
    public int consultarIdPropiedadPorTipo(String tipo);
    public TipoPropiedad obtenerTipoPropiedadPorId(int idTipoPropiedad);
}
