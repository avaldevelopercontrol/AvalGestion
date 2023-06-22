package Dao;

import Models.gd_tipobusqueda;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public class gd_tipobusquedaDAO {
    
    private Connection con;
    
    public gd_tipobusquedaDAO(Connection con) {
        this.con = con;
    }
    
    public List<gd_tipobusqueda> listarTipoBusqueda() throws Exception {
    
        List<gd_tipobusqueda> lsttipBus = new ArrayList<gd_tipobusqueda>();
        gd_tipobusqueda tipBus = new gd_tipobusqueda();
        
        ResultSet rs = null;
        String sql=" exec sp_TipoBusqueda_Listar ";
        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            while (rs.next() == true) {
                tipBus = new gd_tipobusqueda();
                tipBus.setcTipoBusqueda(rs.getString("cTipoBusqueda"));
                tipBus.setcTipoBusquedaNombre(rs.getString("cTipoBusquedaNombre"));
                lsttipBus.add(tipBus);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return lsttipBus;
    }
    
}