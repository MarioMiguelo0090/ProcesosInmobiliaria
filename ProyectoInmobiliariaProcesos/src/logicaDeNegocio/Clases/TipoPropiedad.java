package logicaDeNegocio.Clases;

public class TipoPropiedad {
    private int idTipoPropiedad;
    private String tipo;

    public TipoPropiedad() {
    }            
    
    public int getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(int idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }        
    
    public boolean equals(Object obj){
        if(!(obj instanceof TipoPropiedad)) {
            return false;
        } 
        TipoPropiedad tipoPropiedadTemporal=(TipoPropiedad)obj;
        return tipo.equals(tipoPropiedadTemporal.getTipo());
    }
         
}
