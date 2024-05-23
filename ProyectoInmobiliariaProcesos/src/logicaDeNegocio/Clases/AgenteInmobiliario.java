package logicaDeNegocio.Clases;

public class AgenteInmobiliario extends Usuario{


    private int idAgente;
    private String estadoAgente;
    
    public int getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }
       
    public String getEstadoAgente() {
        return estadoAgente;
    }

    public void setEstadoAgente(String estadoAgente) {
        this.estadoAgente = estadoAgente;
    }
    
}
