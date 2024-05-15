package logicaDeNegocio.Clases;

public class Ubicacion {
    private int idUbicacion;
    private String estado;
    private String ciudad;

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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }     
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Ubicacion)) {
            return false;
        }
        Ubicacion ubicacionTemporal=(Ubicacion)obj;
        return ciudad.equals(ubicacionTemporal.getCiudad())&&
                estado.equals(ubicacionTemporal.getEstado());                
    }
        
}
