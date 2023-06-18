package Models;

import java.util.Date;

public class gd_usuariocliente {

    private int idUsuarioCliente;
    private int idUsuario;
    private String nombreUsuario;
    private gd_usuario gd_Usuario;
    private av_cliente av_Cliente;
    private boolean activo;
    private String usuarioCrea;
    private Date fechaCrea;
    private int CantidadClientes;

    public int getCantidadClientes() {
        return CantidadClientes;
    }

    public void setCantidadClientes(int CantidadClientes) {
        this.CantidadClientes = CantidadClientes;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public av_cliente getAv_Cliente() {
        return av_Cliente;
    }

    public void setAv_Cliente(av_cliente av_Cliente) {
        this.av_Cliente = av_Cliente;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public gd_usuario getGd_Usuario() {
        return gd_Usuario;
    }

    public void setGd_Usuario(gd_usuario gd_Usuario) {
        this.gd_Usuario = gd_Usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuarioCliente() {
        return idUsuarioCliente;
    }

    public void setIdUsuarioCliente(int idUsuarioCliente) {
        this.idUsuarioCliente = idUsuarioCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }
    
}