package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class Ubicacion {
    private int idUbicacion;
    private String estado;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    
    public Ubicacion() {
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado)throws IllegalArgumentException {
        if(estado!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(estado))){
            this.estado = estado;
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
