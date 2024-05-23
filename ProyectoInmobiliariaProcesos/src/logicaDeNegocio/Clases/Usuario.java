package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private String RFC;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+)*$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String RFC_PATTERN = "^[A-ZÑ&]{3,4}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[A-Z\\d]{3}$";

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idUsuario))){
            this.idUsuario = idUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombre.trim())&&nombre.trim().length()<=100){
            this.nombre = nombre.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        if(apellidoPaterno!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, apellidoPaterno.trim())&&apellidoPaterno.trim().length()<=100){
            this.apellidoPaterno = apellidoPaterno.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        if(apellidoMaterno!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, apellidoMaterno.trim())&&apellidoMaterno.trim().length()<=100){
            this.apellidoMaterno = apellidoMaterno.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(telefono))){
            this.telefono = telefono;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo!=null&&Pattern.matches(EMAIL_PATTERN, correo.trim())&&correo.trim().length()<=100){
            this.correo = correo.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        if(RFC!=null&&Pattern.matches(RFC_PATTERN, RFC.trim())&&RFC.trim().length()<=13){
            this.RFC = RFC.trim().replaceAll("\\s+", " ");            
        }else{
            throw new IllegalArgumentException();            
        }
    }            
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Usuario)) {
            return false;
        }
        Usuario usuarioTemporal=(Usuario)obj;
        return correo.equals(usuarioTemporal.getCorreo())&&
                RFC.equals(usuarioTemporal.getRFC());        
    }
        
}
