package Dao;

import Models.av_cartera;
import Models.gd_gestioncartera;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class gd_gestioncarteraDAO {
    
    private Connection con;
    
    public gd_gestioncarteraDAO(Connection con) {
        this.con = con;
    }
    
    public List<av_cartera> listarCarteras(av_cartera car) throws Exception {
        
        List<av_cartera> carteras = new ArrayList<av_cartera>();
        av_cartera cart = new av_cartera();
        
        ResultSet rs = null;
        String sql=" exec sp_Carteras_Usuario_Listar '"+car.getIdUsuario()+"'";

        Statement statement = con.createStatement();

        try {
            rs = statement.executeQuery(sql);
            if (rs.next() == true) { 
                while (rs.next() == true) {
                    cart = new av_cartera();
                    cart.setnId_Cartera(rs.getInt("nId_Cartera"));
                    cart.setcCar_Nombre(rs.getString("cCar_Nombre"));
                    carteras.add(cart);
                }
                rs.close();
                statement.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
        }
        
        return carteras;
    }
    
    public List<gd_gestioncartera> listarGestionCarteras(gd_gestioncartera gescarSearch) throws Exception {
        
        List<gd_gestioncartera> gestionCarteras = new ArrayList<gd_gestioncartera>();
        gd_gestioncartera gescar = new gd_gestioncartera();
        
        ResultSet rs = null;
        String sql=" exec sp_BuscarGestionCarteras '" + gescarSearch.getnId_Cliente() 
                + "','" + gescarSearch.getnId_Cartera()
                + "','" + gescarSearch.getcTipoBusqueda()
                + "','" + gescarSearch.getcPers_CodCliente()
                + "','" + gescarSearch.getcPers_RUC()
                + "','" + gescarSearch.getcPers_DNI()
                + "'";

        Statement statement = con.createStatement();

        try {
            rs = statement.executeQuery(sql);
            if (rs.next() == true) { 
                while (rs.next() == true) {
                    gescar = new gd_gestioncartera();
                    gescar.setnId_Cliente(rs.getInt("nId_Cliente"));
                    gescar.setnId_Cartera(rs.getInt("nId_Cartera"));
                    gescar.setcCar_Nombre(rs.getString("cCar_Nombre"));
                    gescar.setnId_PersDeudor(rs.getInt("nId_PersDeudor"));
                    gescar.setcPers_CodCliente(rs.getString("cPers_CodCliente"));
                    gescar.setcPers_RUC(rs.getString("cPers_RUC"));
                    gescar.setcPers_DNI(rs.getString("cPers_DNI"));
                    gescar.setcPers_Nombres(rs.getString("cPers_Nombres"));
                    gescar.setnDoc_ImpTotal(rs.getDouble("nDoc_ImpTotal"));
                    gescar.setnId_OpeCodOut(rs.getInt("nId_OpeCodOut"));
                    gescar.setcNombre_OpeCodCliOut(rs.getString("cNombre_OpeCodCliOut"));
                    gescar.setdDocCobOpe_FecIni(rs.getDate("dDocCobOpe_FecIni"));
                    gestionCarteras.add(gescar);
                }
                rs.close();
                statement.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
        }
        
        return gestionCarteras;
    }
    
}