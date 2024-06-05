package logicaDeNegocio.Clases;

import java.util.Objects;
import java.util.regex.Pattern;

public class Propiedad {
    private int idPropiedad;
    private String nombre;
    private int numeroDeHabitaciones;
    private int numeroDeBanios;
    private int numeroDePisos;
    private int antiguedad;
    private float metrosDeTerreno;
    private String precio;
    private String estadoPropiedad;
    private TipoPropiedad tipoDePropiedad;
    private Direccion direccion;
    private Propietario propietario;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String SOLO_NUMEROS_FLOTANTES_PATTERN = "-?\\d*(\\.\\d*)?";

    public Propiedad() {
    }    

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre)throws IllegalArgumentException {
        if(nombre!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombre)){
            this.nombre = nombre;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getNumeroDeHabitaciones() {
        return numeroDeHabitaciones;
    }

    public void setNumeroDeHabitaciones(int numeroDeHabitaciones)throws IllegalArgumentException {
        if(numeroDeHabitaciones>0&&Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(numeroDeHabitaciones))){
            this.numeroDeHabitaciones = numeroDeHabitaciones;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getNumeroDeBanios() {
        return numeroDeBanios;
    }

    public void setNumeroDeBanios(int numeroDeBanios)throws IllegalArgumentException {
        if(numeroDeBanios>0&&Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(numeroDeBanios))){
            this.numeroDeBanios = numeroDeBanios;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(int numeroDePisos) throws IllegalArgumentException{
        if(numeroDeBanios>0&&Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(numeroDeBanios))){
            this.numeroDePisos = numeroDePisos;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad)throws IllegalArgumentException {
        if(antiguedad>=0&&Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(antiguedad))){
            this.antiguedad = antiguedad;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public float getMetrosDeTerreno() {
        return metrosDeTerreno;
    }

    public void setMetrosDeTerreno(float metrosDeTerreno) throws IllegalArgumentException{
        if(metrosDeTerreno>=0&&Pattern.matches(SOLO_NUMEROS_FLOTANTES_PATTERN, String.valueOf(metrosDeTerreno))){
            this.metrosDeTerreno = metrosDeTerreno;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio)throws IllegalArgumentException {
        if(precio.length()>0&&precio.length()<=15&&Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(precio))){
            this.precio = precio;
        }else{
          throw new IllegalArgumentException();
        }
    }

    public String getEstadoPropiedad() {
        return estadoPropiedad;
    }

    public void setEstadoPropiedad(String estadoPropiedad)throws IllegalArgumentException {
        if(estadoPropiedad!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estadoPropiedad)){
            this.estadoPropiedad = estadoPropiedad;
        }else{
          throw new IllegalArgumentException();
        }
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public TipoPropiedad getTipoDePropiedad() {
        return tipoDePropiedad;
    }

    public void setTipoDePropiedad(TipoPropiedad tipoDePropiedad) {
        this.tipoDePropiedad = tipoDePropiedad;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propiedad propiedad = (Propiedad) o;
        return idPropiedad == propiedad.idPropiedad &&
                numeroDeHabitaciones == propiedad.numeroDeHabitaciones &&
                numeroDeBanios == propiedad.numeroDeBanios &&
                numeroDePisos == propiedad.numeroDePisos &&
                antiguedad == propiedad.antiguedad &&
                Float.compare(propiedad.metrosDeTerreno, metrosDeTerreno) == 0 &&
                Objects.equals(precio, propiedad.precio)&&
                Objects.equals(nombre, propiedad.nombre) &&
                Objects.equals(estadoPropiedad, propiedad.estadoPropiedad)&&
                Objects.equals(tipoDePropiedad, propiedad.tipoDePropiedad) &&
                Objects.equals(direccion, propiedad.direccion) &&
                Objects.equals(propietario, propiedad.propietario);
    }
}
