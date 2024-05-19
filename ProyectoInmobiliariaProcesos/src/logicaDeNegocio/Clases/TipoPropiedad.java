package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class TipoPropiedad {
    private int idTipoPropiedad;
    private String tipo;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public TipoPropiedad() {
    }            
    
    public int getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(int idTipoPropiedad) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idTipoPropiedad))){
            this.idTipoPropiedad = idTipoPropiedad;
        }else{
            throw new IllegalArgumentException();
        }        
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(tipo!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, tipo.trim())&&tipo.trim().length()<=150){
            this.tipo = tipo.trim().replaceAll("\\s+", " ");
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
