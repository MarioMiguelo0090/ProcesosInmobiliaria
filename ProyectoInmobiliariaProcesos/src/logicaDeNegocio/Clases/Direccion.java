package logicaDeNegocio.Clases;

import java.util.Objects;
import java.util.regex.Pattern;

public class Direccion {
    private int idDireccion;
    private String codigoPostal;
    private String calle;
    private String ciudad;
    private String colonia;
    private Ubicacion ubicacion;
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";

    public Direccion() {
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion)throws IllegalArgumentException {
        this.idDireccion = idDireccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)throws IllegalArgumentException {
        if(codigoPostal!=null&&Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(codigoPostal))){
            this.codigoPostal = codigoPostal;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle)throws IllegalArgumentException{
        if(calle!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(calle))){
            this.calle = calle;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) throws IllegalArgumentException{
        if(ciudad!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(ciudad))){
            this.ciudad = ciudad;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia)throws IllegalArgumentException {
        if(colonia!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(colonia))){
            this.colonia = colonia;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(codigoPostal, direccion.codigoPostal) &&
                Objects.equals(calle, direccion.calle) &&
                Objects.equals(ciudad, direccion.ciudad) &&
                Objects.equals(colonia, direccion.colonia) &&
                Objects.equals(ubicacion, direccion.ubicacion);
    }
}
