package Dao;

import Encrypt.Encrypt;
import Models.gd_usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class gd_usuarioDAO {
    
    private Connection con;
    
    public gd_usuarioDAO(Connection con) {
        this.con = con;
    }
    
    public gd_usuario identificar(gd_usuario user) throws SQLException, Exception {
        gd_usuario usu = null;
        ResultSet rs = null;
        
        Encrypt encrypt = new Encrypt();
        
        String sql=" exec sp_ValidarUsuarioGestion '"+user.getNombreUsuario()+"','"+encrypt.Encrypt(user.getClaveUsuario())+"'";
        
        Statement statement = con.createStatement();
        rs = statement.executeQuery(sql);
        if (rs.next() == true) { 
            usu = new gd_usuario();
            usu.setIdUsuario(rs.getInt("idUsuario"));
            usu.setNombreUsuario(rs.getString("nombreUsuario"));
            usu.setNombres(rs.getString("nombres"));
            usu.setApellidos(rs.getString("apellidos"));
            usu.setClaveUsuario(rs.getString("claveUsuario"));
            usu.setActivo(rs.getBoolean("activo"));
            usu.setUsuarioCrea(rs.getString("usuarioCrea"));
            usu.setFechaCrea(rs.getDate("fechaCrea"));
            usu.setEsRoot(rs.getBoolean("esRoot"));
        }
        rs.close();
        statement.close();
        
        return usu;
    }
    
    public List<gd_usuario> listarUsuarios() throws Exception {
        
        List<gd_usuario> usuarios = new ArrayList<gd_usuario>();
        gd_usuario usu;
        
        ResultSet rs = null;
        String sql=" exec sp_UsuariosListar ";
        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            while (rs.next() == true) {
                usu = new gd_usuario();
                usu.setIdUsuario(rs.getInt("idUsuario"));
                usu.setNombreUsuario(rs.getString("nombreUsuario"));
                usu.setNombres(rs.getString("nombres"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setActivo(rs.getBoolean("activo"));
                usu.setEsRoot(rs.getBoolean("esRoot"));
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
    
    public void registrarUsuarios (gd_usuario usuario) throws Exception {
        String strSQL;
        
        CallableStatement callableStatement = null;
        
        strSQL = "{call sp_UsuarioRegistrar (?,?,?,?,?,?,?)}";
        callableStatement = con.prepareCall(strSQL);
        
        callableStatement.setString(1, usuario.getNombreUsuario());
        callableStatement.setString(2, usuario.getNombres());
        callableStatement.setString(3, usuario.getApellidos());
        callableStatement.setString(4, usuario.getClaveUsuario());
        callableStatement.setBoolean(5, usuario.isActivo());
        callableStatement.setBoolean(6, usuario.isEsRoot());
        callableStatement.setString(7, usuario.getUsuarioCrea());
        
        callableStatement.executeUpdate();
        callableStatement.close();        
    }
    
    public gd_usuario leerUsuario(gd_usuario usu) throws Exception {
        gd_usuario usus = null;
        ResultSet rs = null;
        
        String sql=" exec sp_UsuarioObtener '"+usu.getIdUsuario()+"'";
        
        Statement statement = con.createStatement();
        rs = statement.executeQuery(sql);
        if (rs.next() == true) { 
            usus = new gd_usuario();
            usus.setIdUsuario(rs.getInt("idUsuario"));
            usus.setNombreUsuario(rs.getString("nombreUsuario"));
            usus.setNombres(rs.getString("nombres"));
            usus.setApellidos(rs.getString("apellidos"));
            usus.setClaveUsuario(rs.getString("claveUsuario"));
            usus.setActivo(rs.getBoolean("activo"));
            usus.setEsRoot(rs.getBoolean("esRoot"));
        }
        rs.close();
        statement.close();
        
        return usus;
    }
    
    public void actualizarUsuarios(gd_usuario usu) throws Exception{
        String strSQL;
        
        CallableStatement callableStatement = null;
        
        strSQL = "{call sp_UsuarioActualizar (?,?,?,?,?,?,?,?)}";
        callableStatement = con.prepareCall(strSQL);
        
        callableStatement.setInt(1, usu.getIdUsuario());
        callableStatement.setString(2, usu.getNombreUsuario());
        callableStatement.setString(3, usu.getNombres());
        callableStatement.setString(4, usu.getApellidos());
        callableStatement.setString(5, usu.getClaveUsuario());
        callableStatement.setBoolean(6, usu.isActivo());
        callableStatement.setBoolean(7, usu.isEsRoot());
        callableStatement.setString(8, usu.getUsuarioCrea());
        
        callableStatement.executeUpdate();
        callableStatement.close();
    }
    
}