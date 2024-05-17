package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Interfaces.TipoPropiedadInterface;

public class DAOTipoPropiedad implements TipoPropiedadInterface {

    public static final ManejadorBaseDatos BASE_DE_DATOS = new ManejadorBaseDatos();
    private Connection conexion;

    @Override
    public int registrarTipoPropiedad(TipoPropiedad tipoPropiedad) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("Insert into TipoPropiedad (tipos) values (?);");
            declaracion.setString(1, tipoPropiedad.getTipo());
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoPropiedad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;
    }

    @Override
    public List<TipoPropiedad> consultarTiposPropiedad() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<TipoPropiedad> tiposPropiedad = new ArrayList<>();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("Select * from tipoPropiedad;");
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                TipoPropiedad tipoPropiedad = new TipoPropiedad();
                tipoPropiedad.setIdTipoPropiedad(resultado.getInt("idTipoPropiedad"));
                tipoPropiedad.setTipo(resultado.getString("tipos"));
                tiposPropiedad.add(tipoPropiedad);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoPropiedad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiposPropiedad;
    }

    public TipoPropiedad consultarTiposPropiedadPorID(int idTipoDePropiedad) {
        PreparedStatement declaracion;
        ResultSet resultado;
        TipoPropiedad tipoPropiedad = new TipoPropiedad();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("Select * from tipoPropiedad where idTipoPropiedad = ?;");
            declaracion.setInt(1, idTipoDePropiedad);
            resultado = declaracion.executeQuery();
            tipoPropiedad.setIdTipoPropiedad(resultado.getInt("idTipoPropiedad"));
            tipoPropiedad.setTipo(resultado.getString("tipos"));
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoPropiedad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipoPropiedad;
    }
}
