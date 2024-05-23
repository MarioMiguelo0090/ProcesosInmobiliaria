package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class Login {
        
    private int idLogin;
    private String usuario;
    private String contrasenia;
    private String tipoUsuario;
    
    public Login(){
        
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin)throws IllegalArgumentException{
            this.idLogin = idLogin;

    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario){

            this.usuario = usuario;
       
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(String tipoUsuario){
       this.tipoUsuario = tipoUsuario;
       
    }
    
    public String getContrasenia() {
        return contrasenia;
    }
    
    
    public void setContrasenia(String contrasenia){

            this.contrasenia = contrasenia;
        
    }

    @Override
    public boolean equals(Object obj){
        Login loginTemporal = (Login)obj;
        return this.usuario.equals(loginTemporal.getContrasenia())&&
                this.contrasenia.equals(loginTemporal.getUsuario()); 
    }
}
