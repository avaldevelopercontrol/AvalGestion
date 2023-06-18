package Models;

import java.util.Date;

public class gd_usuario {
    
    private int idUsuario;
    private String nombreUsuario;
    private String nombres;
    private String apellidos;
    private String claveUsuario;
    private boolean activo;
    private String usuarioCrea;
    private Date fechaCrea;
    private boolean esRoot = false;
    private int cantidadClientes = 0;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCantidadClientes() {
        return cantidadClientes;
    }

    public void setCantidadClientes(int cantidadClientes) {
        this.cantidadClientes = cantidadClientes;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public boolean isEsRoot() {
        return esRoot;
    }

    public void setEsRoot(boolean esRoot) {
        this.esRoot = esRoot;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }
    
}