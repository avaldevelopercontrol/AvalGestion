package Dao;

import Models.av_cartera;
import Models.av_cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class av_clienteDAO {
    
    private Connection con;
    
    public av_clienteDAO(Connection con) {
        this.con = con;
    }
    
    public List<av_cliente> listarClientes() throws Exception {
        
        List<av_cliente> clientes = new ArrayList<av_cliente>();
        av_cliente cli;
        
        ResultSet rs = null;
        String sql=" exec sp_ClientesListar ";
        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            while (rs.next() == true) {
                cli = new av_cliente();
                cli.setnId_Cliente(rs.getInt("nId_Cliente"));
                cli.setcCli_NroDoc(rs.getString("cCli_NroDoc"));
                cli.setcCli_Nombre(rs.getString("cCli_Nombre"));
                cli.setbEstado(rs.getBoolean("bEstado"));
                clientes.add(cli);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return clientes;
    }
    
    public av_cliente getClientexCartera(av_cartera car) throws Exception {
        av_cliente cliGet = null;
        ResultSet rs = null;
        
        String sql=" exec sp_ObtenerClienteXCartera '"+car.getnId_Cartera()+"'";
        
        Statement statement = con.createStatement();
        rs = statement.executeQuery(sql);
        if (rs.next() == true) { 
            cliGet = new av_cliente();
            cliGet.setnId_Cliente(rs.getInt("nId_Cliente"));
            cliGet.setcCli_Nombre(rs.getString("cCli_Nombre"));
            cliGet.setdFecIniProceso(rs.getString("dFecIniProceso").substring(0, 10));
            cliGet.setdFecFinProceso(rs.getString("dFecFinProceso").substring(0, 10));
        }
        rs.close();
        statement.close();
        
        return cliGet;
    }
    
}