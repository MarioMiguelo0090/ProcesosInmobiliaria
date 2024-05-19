package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class Ubicacion {
    private int idUbicacion;
    private String estado;    
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public Ubicacion() {
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idUbicacion))){
            this.idUbicacion = idUbicacion;
        }else{
            throw new IllegalArgumentException();
        } 
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if(estado!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estado.trim())&&estado.trim().length()<=150){
            this.estado = estado.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }  
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Ubicacion)) {
            return false;
        }
        Ubicacion ubicacionTemporal=(Ubicacion)obj;
        return estado.equals(ubicacionTemporal.getEstado());                
    }
        
}
