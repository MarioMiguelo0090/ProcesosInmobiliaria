package logicaDeNegocio.Clases;

public class Cliente {
    private int idCliente;
    private String estadoCliente;   
    private float rangoDePrecioMinimo;
    private float rangoDePrecioMaximo;
    private float minimoMetrosCuadrados;
    private TipoPropiedad tipoPropiedad;
    private Ubicacion ubicacion;
    private Usuario usuario;    
    
    public Cliente() {
    }
        
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public float getRangoDePrecioMinimo() {
        return rangoDePrecioMinimo;
    }

    public void setRangoDePrecioMinimo(float rangoDePrecioMinimo) {
        this.rangoDePrecioMinimo = rangoDePrecioMinimo;
    }      

    public float getRangoDePrecioMaximo() {
        return rangoDePrecioMaximo;
    }

    public void setRangoDePrecioMaximo(float rangoDePrecioMaximo) {
        this.rangoDePrecioMaximo = rangoDePrecioMaximo;
    }

    public float getMinimoMetrosCuadrados() {
        return minimoMetrosCuadrados;
    }

    public void setMinimoMetrosCuadrados(float minimoMetrosCuadrados) {
        this.minimoMetrosCuadrados = minimoMetrosCuadrados;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }     
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Cliente)) {
            return false;            
        }
        Cliente clienteTemporal=(Cliente)obj;
        return idCliente==clienteTemporal.getIdCliente();                
    }
    
}
