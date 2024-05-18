package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class TipoPropiedad {
    private int idTipoPropiedad;
    private String tipo;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";

    public TipoPropiedad() {
    }            
    
    public int getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(int idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo)throws IllegalArgumentException {
        if(tipo!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(tipo))){
            this.tipo = tipo;
        }else{
            throw new IllegalArgumentException();
        }
    }        
    
    public boolean equals(Object obj){
        if(!(obj instanceof TipoPropiedad)) {
            return false;
        } 
        TipoPropiedad tipoPropiedadTemporal=(TipoPropiedad)obj;
        return tipo.equals(tipoPropiedadTemporal.getTipo());
    }
         
}
