package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.Clases.Direccion;
import logicaDeNegocio.Clases.Propiedad;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.TipoPropiedad;


public final class PropiedadAuxiliar {
    
    private static PropiedadAuxiliar instancia;
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

    private PropiedadAuxiliar(Propiedad instancia){
        setIdPropiedad(instancia.getIdPropiedad());
        setNombre(instancia.getNombre());
        setNumeroDeHabitaciones(instancia.getNumeroDeHabitaciones());
        setNumeroDeBanios(instancia.getNumeroDeBanios());
        setNumeroDePisos(instancia.getNumeroDeBanios());
        setAntiguedad(instancia.getAntiguedad());
        setMetrosDeTerreno(instancia.getMetrosDeTerreno());
        setPrecio(instancia.getPrecio());
        setEstadoPropiedad(instancia.getEstadoPropiedad());
        setTipoDePropiedad(instancia.getTipoDePropiedad());
        setDireccion(instancia.getDireccion());
        setPropietario(instancia.getPropietario());
    }
    
    public static PropiedadAuxiliar getInstancia() {
        return instancia;
    }

    public static void setInstancia(Propiedad instancia) {
        PropiedadAuxiliar.instancia = new PropiedadAuxiliar(instancia);
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    private void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeHabitaciones() {
        return numeroDeHabitaciones;
    }

    private void setNumeroDeHabitaciones(int numeroDeHabitaciones) {
        this.numeroDeHabitaciones = numeroDeHabitaciones;
    }

    public int getNumeroDeBanios() {
        return numeroDeBanios;
    }

    private void setNumeroDeBanios(int numeroDeBanios) {
        this.numeroDeBanios = numeroDeBanios;
    }

    public int getNumeroDePisos() {
        return numeroDePisos;
    }

    private void setNumeroDePisos(int numeroDePisos) {
        this.numeroDePisos = numeroDePisos;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    private void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public float getMetrosDeTerreno() {
        return metrosDeTerreno;
    }

    private void setMetrosDeTerreno(float metrosDeTerreno) {
        this.metrosDeTerreno = metrosDeTerreno;
    }

    public String getPrecio() {
        return precio;
    }

    private void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEstadoPropiedad() {
        return estadoPropiedad;
    }

    private void setEstadoPropiedad(String estadoPropiedad) {
        this.estadoPropiedad = estadoPropiedad;
    }

    public TipoPropiedad getTipoDePropiedad() {
        return tipoDePropiedad;
    }

    private void setTipoDePropiedad(TipoPropiedad tipoDePropiedad) {
        this.tipoDePropiedad = tipoDePropiedad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    private void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    private void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    
}
