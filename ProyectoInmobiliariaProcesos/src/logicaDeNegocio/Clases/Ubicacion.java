package logicaDeNegocio.Clases;

public class Ubicacion {
    private int idUbicacion;
    private String estado;

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
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Ubicacion)) {
            return false;
        }
        Ubicacion ubicacionTemporal=(Ubicacion)obj;
        return estado.equals(ubicacionTemporal.getEstado());                
    }
        
}
