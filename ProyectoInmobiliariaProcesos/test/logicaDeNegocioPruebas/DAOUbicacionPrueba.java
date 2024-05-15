package logicaDeNegocioPruebas;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.DAO.DAOUbicacion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DAOUbicacionPrueba {
    
    @Test
    public void pruebaRegistrarUbicacionExitosa(){
        Ubicacion ubicacion=new Ubicacion();
        ubicacion.setCiudad("Xalapa");
        ubicacion.setEstado("Veracruz");
        int resultadoEsperado=1;
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        int resultadoObtenido=daoUbicacion.registrarUbicacion(ubicacion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarUbicacionesExitosa(){
        Ubicacion ubicacion=new Ubicacion();
        ubicacion.setCiudad("Xalapa");
        ubicacion.setEstado("Veracruz");
        List<Ubicacion> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(ubicacion);
        List<Ubicacion> resultadoObtenido=new ArrayList<>();
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        resultadoObtenido=daoUbicacion.consultarUbicaciones();
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }
    
}
