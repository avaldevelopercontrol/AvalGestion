package Dao;

import Models.av_DocxCobrarOpe;
import Util._util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class av_DocxCobrarOpeDAO {
    
    private Connection con;
    
    public av_DocxCobrarOpeDAO(Connection con) {
        this.con = con;
    }
    
    public List<av_DocxCobrarOpe> listarGestionTipificacion(av_DocxCobrarOpe docxCobrarOpe) throws Exception { 
        
        List<av_DocxCobrarOpe> lstGestionTipificacion = new ArrayList<av_DocxCobrarOpe>();
        av_DocxCobrarOpe cobrarOpe = new av_DocxCobrarOpe();
        
        ResultSet rs = null;
        
        String sql=" exec sp_GestionesXTipificacion " + docxCobrarOpe.getnId_Cliente() 
                + "," + docxCobrarOpe.getnId_Cartera()
                + "," + docxCobrarOpe.getnId_OpeCodOut()
                + ",'" + _util.getFechaServidor(docxCobrarOpe.getdDocCobOpe_FecIni())
                + "','" + _util.getFechaServidor(docxCobrarOpe.getdDocCobOpe_FecFin())
                + "'";
        
        Statement statement = con.createStatement();
        
        try {
            rs = statement.executeQuery(sql);
            
            while (rs.next() == true) {
                cobrarOpe = new av_DocxCobrarOpe();
                cobrarOpe.setnId_OpeCodOut(rs.getInt("nId_OpeCodOut"));
                cobrarOpe.setcNombre_OpeCodCliOut(rs.getString("cNombre_OpeCodCliOut"));
                cobrarOpe.setnId_OpeCodOutNp2(rs.getInt("nId_OpeCodOutNp2"));
                cobrarOpe.setcNombre_OpeCodCliOutN2(rs.getString("cNombre_OpeCodCliOutN2"));
                cobrarOpe.setnId_UsuOpe(rs.getInt("nId_UsuOpe"));
                cobrarOpe.setcUsr_Nombres(rs.getString("cUsr_Nombres"));
                cobrarOpe.setdDocCobOpe_FecIni(rs.getString("dDocCobOpe_FecIni"));
                cobrarOpe.setdDocCobOpe_FecFin(rs.getString("dDocCobOpe_FecFin"));
                cobrarOpe.setnId_DocxCobrar(rs.getInt("nId_DocxCobrar"));
                cobrarOpe.setcDoc_Numero(rs.getString("cDoc_Numero"));
                cobrarOpe.setnDoc_ImpSaldo(rs.getDouble("nDoc_ImpSaldo"));
                cobrarOpe.setDuracionSegundo(rs.getInt("DuracionSegundo"));
                cobrarOpe.setcPers_Nombres(rs.getString("cPers_Nombres"));
                lstGestionTipificacion.add(cobrarOpe);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return lstGestionTipificacion;
    }
    
}