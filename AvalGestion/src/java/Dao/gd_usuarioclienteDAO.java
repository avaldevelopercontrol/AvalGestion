package Dao;

import Models.av_cliente;
import Models.gd_usuario;
import Models.gd_usuariocliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class gd_usuarioclienteDAO {

    private Connection con;
    
    public gd_usuarioclienteDAO(Connection con) {
        this.con = con;
    }
    
    public List<gd_usuario> listarUsuarioClientes() throws Exception {
        
        List<gd_usuario> usuarios = new ArrayList<gd_usuario>();
        gd_usuario usu;
        
        ResultSet rs = null;
        String sql=" exec sp_UsuarioClienteListar ";
        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            while (rs.next() == true) {
                usu = new gd_usuario();
                usu.setIdUsuario(rs.getInt("idUsuario"));
                usu.setNombreUsuario(rs.getString("nombreUsuario"));
                usu.setCantidadClientes(rs.getInt("CantidadClientes"));
                usuarios.add(usu);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return usuarios;
    }
    
    public gd_usuariocliente leerUsuario(gd_usuariocliente usu) throws Exception {
        gd_usuariocliente usus = null;
        ResultSet rs = null;
        
        String sql=" exec sp_UsuarioObtener '"+usu.getIdUsuario()+"'";
        
        Statement statement = con.createStatement();
        rs = statement.executeQuery(sql);
        if (rs.next() == true) { 
            usus = new gd_usuariocliente();
            usus.setIdUsuario(rs.getInt("idUsuario"));
            usus.setNombreUsuario(rs.getString("nombreUsuario"));
        }
        rs.close();
        statement.close();
        
        return usus;
    }
    
    public List<av_cliente> llenarComboClientes(gd_usuario usu) throws Exception {
        
        List<av_cliente> clientes = new ArrayList<av_cliente>();
        av_cliente cli = new av_cliente();
        
        ResultSet rs = null;
        String sql=" exec sp_ClienteUsuarioCliente '"+usu.getIdUsuario()+"'";

        Statement statement = con.createStatement();

        try {
            rs = statement.executeQuery(sql);
            if (rs.next() == true) { 
                while (rs.next() == true) {
                    cli = new av_cliente();
                    cli.setnId_Cliente(rs.getInt("nId_Cliente"));
                    cli.setcCli_Nombre(rs.getString("cCli_Nombre"));
                    clientes.add(cli);
                }
                rs.close();
                statement.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
        }
        
        return clientes;
    }
    
    public List<gd_usuariocliente> ListarUsuarioClientes(gd_usuario usu) throws Exception {
        
        
        List<gd_usuariocliente> usuarioClientes = new ArrayList<gd_usuariocliente>();
        
        gd_usuariocliente usucli;
        gd_usuario usus;
        av_cliente cli;
        ResultSet rs = null;
        
        String sql=" exec sp_ClienteUsuarioUsuario '"+usu.getIdUsuario()+"'";
        
        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            
            while (rs.next() == true) {
                usucli = new gd_usuariocliente();
                usucli.setIdUsuarioCliente(rs.getInt("idUsuarioCliente"));
                usus = new gd_usuario();
                usus.setIdUsuario(rs.getInt("idUsuario"));
                usucli.setGd_Usuario(usus);
                cli = new av_cliente();
                cli.setnId_Cliente(rs.getInt("nId_Cliente"));
                cli.setcCli_Nombre(rs.getString("cCli_Nombre"));
                usucli.setAv_Cliente(cli);
                usucli.setActivo(rs.getBoolean("activo"));
                usuarioClientes.add(usucli);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return usuarioClientes;
    }
    
    public void registrarUsuarioCliente(gd_usuariocliente usuariocliente) throws Exception {
        String strSQL;
        
        CallableStatement callableStatement = null;
        
        strSQL = "{call sp_UsuarioClienteInsertar (?,?,?,?)}";
        callableStatement = con.prepareCall(strSQL);
        
        callableStatement.setInt(1, usuariocliente.getGd_Usuario().getIdUsuario());
        callableStatement.setInt(2, usuariocliente.getAv_Cliente().getnId_Cliente());
        callableStatement.setBoolean(3, usuariocliente.isActivo());
        callableStatement.setString(4, usuariocliente.getUsuarioCrea());
        
        callableStatement.executeUpdate();
        callableStatement.close();
    }
    
    public void cambiarVigencia(gd_usuariocliente usucli) throws Exception {
    
        String strSQL;
        
        CallableStatement callableStatement = null;
        
        strSQL = "{call sp_UsuarioClienteActivoInactivo (?,?,?)}";
        callableStatement = con.prepareCall(strSQL);
        
        callableStatement.setInt(1, usucli.getIdUsuarioCliente());
        callableStatement.setBoolean(2, usucli.isActivo());
        callableStatement.setString(3, usucli.getUsuarioCrea());
        
        callableStatement.executeUpdate();
        callableStatement.close();
        
    }
    
}