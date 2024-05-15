package logicaDeNegocioPruebas;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DAOTipoPropiedadPrueba {
    @Test
    public void pruebRegistrarTipoPropiedadExitosa(){
        TipoPropiedad tipoPropiedad=new TipoPropiedad();
        tipoPropiedad.setTipo("Departamento");
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        int resultadoEsperado=1;
        int resultadoObtenido=daoTipoPropiedad.registrarTipoPropiedad(tipoPropiedad);                
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarTiposPropiedadExitosa(){
        TipoPropiedad tipoPropiedad=new TipoPropiedad();
        tipoPropiedad.setTipo("Departamento");
        List<TipoPropiedad> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(tipoPropiedad);
        List<TipoPropiedad> resultadoObtenido=new ArrayList<>();
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        resultadoObtenido=daoTipoPropiedad.consultarTiposPropiedad();
        assertEquals(resultadoEsperado,resultadoObtenido);                
    }
    
}
