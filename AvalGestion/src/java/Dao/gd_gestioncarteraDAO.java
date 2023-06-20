package Dao;

import Models.av_cartera;
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
    
}