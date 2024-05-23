package AccesoADatosPruebas;

import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class MajadorBaseDatosPruebas {
    
    @Test
    public void pruebaGetConexionExitosa() throws SQLException{
        ManejadorBaseDatos baseDeDatosPrueba = new ManejadorBaseDatos();
        Connection resultado = baseDeDatosPrueba.getConexion();
        assertNotNull(resultado);
        resultado.close();
    }
    
}
