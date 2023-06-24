package Models;

import java.util.Date;

public class gd_gestiondeudor {
    
    private int nId_DocxCobrarOpe;
    private Date dDocCobOpe_FecIni;
    private int nId_OpeCodOut;
    private Date dFechCompromisoPago;
    private int nId_DocxCobrar;
    private int tip_gestion;
    private int nid_UsuOpe;
    private Number monto_comp;
    private Number monto_compDolares;
    private String cDocOpeCobOut_Descr;
    private int nId_OpeCodOutNp2;
    private String cNomTipoGestion;

    public String getcNomTipoGestion() {
        return cNomTipoGestion;
    }

    public void setcNomTipoGestion(String cNomTipoGestion) {
        this.cNomTipoGestion = cNomTipoGestion;
    }
    
    public String getcDocOpeCobOut_Descr() {
        return cDocOpeCobOut_Descr;
    }

    public void setcDocOpeCobOut_Descr(String cDocOpeCobOut_Descr) {
        this.cDocOpeCobOut_Descr = cDocOpeCobOut_Descr;
    }

    public Date getdDocCobOpe_FecIni() {
        return dDocCobOpe_FecIni;
    }

    public void setdDocCobOpe_FecIni(Date dDocCobOpe_FecIni) {
        this.dDocCobOpe_FecIni = dDocCobOpe_FecIni;
    }

    public Date getdFechCompromisoPago() {
        return dFechCompromisoPago;
    }

    public void setdFechCompromisoPago(Date dFechCompromisoPago) {
        this.dFechCompromisoPago = dFechCompromisoPago;
    }

    public Number getMonto_comp() {
        return monto_comp;
    }

    public void setMonto_comp(Number monto_comp) {
        this.monto_comp = monto_comp;
    }

    public Number getMonto_compDolares() {
        return monto_compDolares;
    }

    public void setMonto_compDolares(Number monto_compDolares) {
        this.monto_compDolares = monto_compDolares;
    }

    public int getnId_DocxCobrar() {
        return nId_DocxCobrar;
    }

    public void setnId_DocxCobrar(int nId_DocxCobrar) {
        this.nId_DocxCobrar = nId_DocxCobrar;
    }

    public int getnId_DocxCobrarOpe() {
        return nId_DocxCobrarOpe;
    }

    public void setnId_DocxCobrarOpe(int nId_DocxCobrarOpe) {
        this.nId_DocxCobrarOpe = nId_DocxCobrarOpe;
    }

    public int getnId_OpeCodOut() {
        return nId_OpeCodOut;
    }

    public void setnId_OpeCodOut(int nId_OpeCodOut) {
        this.nId_OpeCodOut = nId_OpeCodOut;
    }

    public int getnId_OpeCodOutNp2() {
        return nId_OpeCodOutNp2;
    }

    public void setnId_OpeCodOutNp2(int nId_OpeCodOutNp2) {
        this.nId_OpeCodOutNp2 = nId_OpeCodOutNp2;
    }

    public int getNid_UsuOpe() {
        return nid_UsuOpe;
    }

    public void setNid_UsuOpe(int nid_UsuOpe) {
        this.nid_UsuOpe = nid_UsuOpe;
    }

    public int getTip_gestion() {
        return tip_gestion;
    }

    public void setTip_gestion(int tip_gestion) {
        this.tip_gestion = tip_gestion;
    }
    
}