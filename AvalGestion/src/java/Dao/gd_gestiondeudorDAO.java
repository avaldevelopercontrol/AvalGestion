package Dao;

import Models.gd_gestiondeudor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class gd_gestiondeudorDAO {

    private Connection con;
    
    public gd_gestiondeudorDAO(Connection con) {
        this.con = con;
    }
    
    public List<gd_gestiondeudor> listarGestionDeudores(
                                                        Integer nId_Cliente, 
                                                        Integer nId_Cartera,
                                                        String cTipoBusqueda,
                                                        String cPers_CodCliente,
                                                        String cPers_RUC,
                                                        String cPers_DNI) throws Exception {
        
        List<gd_gestiondeudor> gestionDeudores = new ArrayList<gd_gestiondeudor>();
        gd_gestiondeudor gesdeu = new gd_gestiondeudor();
        
        ResultSet rs = null;
        String sql=" exec sp_BuscarGestionDeudor " + nId_Cliente
                + "," + nId_Cartera
                + ",'" + cTipoBusqueda
                + "','" + cPers_CodCliente
                + "','" + cPers_RUC
                + "','" + cPers_DNI
                + "'";

        Statement statement = con.createStatement();

        try {
            rs = statement.executeQuery(sql);
            
            while (rs.next() == true) {
                gesdeu = new gd_gestiondeudor();
                gesdeu.setnId_DocxCobrarOpe(rs.getInt("nId_DocxCobrarOpe"));
                gesdeu.setdDocCobOpe_FecIni(rs.getDate("dDocCobOpe_FecIni"));
                gesdeu.setnId_OpeCodOut(rs.getInt("nId_OpeCodOut"));
                gesdeu.setdFechCompromisoPago(rs.getDate("dFechCompromisoPago"));
                gesdeu.setnId_DocxCobrar(rs.getInt("nId_DocxCobrar"));
                gesdeu.setTip_gestion(rs.getInt("tip_gestion"));
                gesdeu.setNid_UsuOpe(rs.getInt("nid_UsuOpe"));
                gesdeu.setMonto_comp(rs.getDouble("monto_comp"));
                gesdeu.setMonto_compDolares(rs.getDouble("monto_compDolares"));
                gesdeu.setcDocOpeCobOut_Descr(rs.getString("cDocOpeCobOut_Descr"));
                gesdeu.setnId_OpeCodOutNp2(rs.getInt("nId_OpeCodOutNp2"));
                gesdeu.setcNomTipoGestion(rs.getString("cNomTipoGestion"));
                gestionDeudores.add(gesdeu);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        
        return gestionDeudores;
    }
    
}