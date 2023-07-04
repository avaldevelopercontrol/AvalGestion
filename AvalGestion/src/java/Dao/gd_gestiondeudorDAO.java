package Dao;

import Models.av_DocxCobrarOpe;
import Models.gd_gestiondeudor;
import Util._util;
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
                                                        String cPers_DNI,
                                                        String dDocCobOpe_FecIni,
                                                        String dDocCobOpe_FecFin) throws Exception {
        
        List<gd_gestiondeudor> gestionDeudores = new ArrayList<gd_gestiondeudor>();
        gd_gestiondeudor gesdeu = new gd_gestiondeudor();
        
        ResultSet rs = null;
        String sql=" exec sp_BuscarGestionDeudor " + nId_Cliente
                + "," + nId_Cartera
                + ",'" + cTipoBusqueda
                + "','" + cPers_CodCliente
                + "','" + cPers_RUC
                + "','" + cPers_DNI
                + "','" + _util.getFechaServidor(dDocCobOpe_FecIni)
                + "','" + _util.getFechaServidor(dDocCobOpe_FecFin)
                + "'";

        Statement statement = con.createStatement();

        try {
            rs = statement.executeQuery(sql);
            
            while (rs.next() == true) {
                gesdeu = new gd_gestiondeudor();
                gesdeu.setnId_DocxCobrarOpe(rs.getInt("nId_DocxCobrarOpe"));
                gesdeu.setdDocCobOpe_FecIni(rs.getString("dDocCobOpe_FecIni"));
                gesdeu.setnId_OpeCodOut(rs.getInt("nId_OpeCodOut"));
                gesdeu.setdFechCompromisoPago(rs.getDate("dFechCompromisoPago"));
                gesdeu.setnId_DocxCobrar(rs.getInt("nId_DocxCobrar"));
                gesdeu.setTip_gestion(rs.getInt("tip_gestion"));
                gesdeu.setcNomTipoGestion(rs.getString("cNomTipoGestion"));
                gesdeu.setNid_UsuOpe(rs.getInt("nid_UsuOpe"));
                gesdeu.setcUsr_Nombres(rs.getString("cUsr_Nombres"));
                gesdeu.setMonto_comp(rs.getDouble("monto_comp"));
                gesdeu.setMonto_compDolares(rs.getDouble("monto_compDolares"));
                gesdeu.setcDocOpeCobOut_Descr(rs.getString("cDocOpeCobOut_Descr"));
                gesdeu.setnId_OpeCodOutNp2(rs.getInt("nId_OpeCodOutNp2"));
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
    
    public List<av_DocxCobrarOpe> getContactoNoContacto (
                                                        Integer nId_Cliente, 
                                                        Integer nId_Cartera,
                                                        String cTipoBusqueda,
                                                        String cPers_CodCliente,
                                                        String cPers_RUC,
                                                        String cPers_DNI,
                                                        String dDocCobOpe_FecIni,
                                                        String dDocCobOpe_FecFin) throws Exception {
    
        List<av_DocxCobrarOpe> lst = new ArrayList<av_DocxCobrarOpe>();
        av_DocxCobrarOpe be = new av_DocxCobrarOpe();
        
        ResultSet rs = null;
        String sql=" exec sp_ResultadoContactoNoContacto " + nId_Cliente
                + "," + nId_Cartera
                + ",'" + cTipoBusqueda
                + "','" + cPers_CodCliente
                + "','" + cPers_RUC
                + "','" + cPers_DNI
                + "','" + _util.getFechaServidor(dDocCobOpe_FecIni)
                + "','" + _util.getFechaServidor(dDocCobOpe_FecFin)
                + "'";

        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            
            while (rs.next() == true) {
                be = new av_DocxCobrarOpe();
                be.setcNombre_OpeCodCliOut(rs.getString("cNombre_OpeCodCliOut"));
                be.setNroContactabilidad(rs.getInt("NroContactabilidad"));
                lst.add(be);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        
        return lst;
    }
    
}