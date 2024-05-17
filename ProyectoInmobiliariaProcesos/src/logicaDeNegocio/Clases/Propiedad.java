package logicaDeNegocio.Clases;

public class Propiedad {
    private int idPropiedad;
    private String nombre;
    private int numeroDeHabitaciones;
    private int numeroDeBanios;
    private int numeroDePisos;
    private int antiguedad;
    private float metrosDeTerreno;
    private float precio;
    private String estadoPropiedad;
    private TipoPropiedad tipoDePropiedad;
    private Direccion direccion;
    private Propietario propietario;

    public Propiedad() {
    }    

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeHabitaciones() {
        return numeroDeHabitaciones;
    }

    public void setNumeroDeHabitaciones(int numeroDeHabitaciones) {
        this.numeroDeHabitaciones = numeroDeHabitaciones;
    }

    public int getNumeroDeBanios() {
        return numeroDeBanios;
    }

    public void setNumeroDeBanios(int numeroDeBanios) {
        this.numeroDeBanios = numeroDeBanios;
    }

    public int getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(int numeroDePisos) {
        this.numeroDePisos = numeroDePisos;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public float getMetrosDeTerreno() {
        return metrosDeTerreno;
    }

    public void setMetrosDeTerreno(float metrosDeTerreno) {
        this.metrosDeTerreno = metrosDeTerreno;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getEstadoPropiedad() {
        return estadoPropiedad;
    }

    public void setEstadoPropiedad(String estadoPropiedad) {
        this.estadoPropiedad = estadoPropiedad;
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
   
}
