package logicaDeNegocio.Clases;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Cliente {
    private int idCliente;
    private String estadoCliente;   
    private BigDecimal rangoDePrecioMinimo;
    private BigDecimal rangoDePrecioMaximo;
    private BigDecimal minimoMetrosCuadrados;
    private String ciudad;
    private TipoPropiedad tipoPropiedad;
    private Ubicacion ubicacion;
    private Usuario usuario;    
    private static final String SOLO_NUMEROS_FLOTANTES_PATTERN = "-?\\d*(\\.\\d+)?([eE][+-]?\\d+)?";
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public Cliente() {
    }
        
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idCliente))){
            this.idCliente = idCliente;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public BigDecimal getRangoDePrecioMinimo() {
        return rangoDePrecioMinimo;
    }

    public void setRangoDePrecioMinimo(BigDecimal rangoDePrecioMinimo) {        
        if(Pattern.matches(SOLO_NUMEROS_FLOTANTES_PATTERN,String.valueOf(rangoDePrecioMinimo))){
            this.rangoDePrecioMinimo = rangoDePrecioMinimo;
        }else{
            throw new IllegalArgumentException();
        }
    }      

    public BigDecimal getRangoDePrecioMaximo() {
        return rangoDePrecioMaximo;
    }

    public void setRangoDePrecioMaximo(BigDecimal rangoDePrecioMaximo) {
        if(Pattern.matches(SOLO_NUMEROS_FLOTANTES_PATTERN, String.valueOf(rangoDePrecioMaximo))){
            this.rangoDePrecioMaximo = rangoDePrecioMaximo;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public BigDecimal getMinimoMetrosCuadrados() {
        return minimoMetrosCuadrados;
    }

    public void setMinimoMetrosCuadrados(BigDecimal minimoMetrosCuadrados) {
        if(Pattern.matches(SOLO_NUMEROS_FLOTANTES_PATTERN, String.valueOf(minimoMetrosCuadrados))){
            this.minimoMetrosCuadrados = minimoMetrosCuadrados;
        }else{
            throw new IllegalArgumentException();
        }
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        if(ciudad!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, ciudad.trim())&&ciudad.trim().length()<=100){
            this.ciudad = ciudad.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
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
