package Models;

import java.util.Date;

public class gd_gestioncartera {

    private int nId_Cliente;
    private int nId_Cartera;
    private String cCar_Nombre;
    private int nId_PersDeudor;
    private String cPers_CodCliente;
    private String cPers_RUC;
    private String cPers_DNI;
    private String cPers_Nombres;
    private Number nDoc_ImpTotal;
    private int nId_OpeCodOut;
    private String cNombre_OpeCodCliOut;
    private Date dDocCobOpe_FecIni;
    private String cTipoBusqueda;

    public String getcTipoBusqueda() {
        return cTipoBusqueda;
    }

    public void setcTipoBusqueda(String cTipoBusqueda) {
        this.cTipoBusqueda = cTipoBusqueda;
    }

    public String getcCar_Nombre() {
        return cCar_Nombre;
    }

    public void setcCar_Nombre(String cCar_Nombre) {
        this.cCar_Nombre = cCar_Nombre;
    }

    public String getcNombre_OpeCodCliOut() {
        return cNombre_OpeCodCliOut;
    }

    public void setcNombre_OpeCodCliOut(String cNombre_OpeCodCliOut) {
        this.cNombre_OpeCodCliOut = cNombre_OpeCodCliOut;
    }

    public String getcPers_CodCliente() {
        return cPers_CodCliente;
    }

    public void setcPers_CodCliente(String cPers_CodCliente) {
        this.cPers_CodCliente = cPers_CodCliente;
    }

    public String getcPers_DNI() {
        return cPers_DNI;
    }

    public void setcPers_DNI(String cPers_DNI) {
        this.cPers_DNI = cPers_DNI;
    }

    public String getcPers_Nombres() {
        return cPers_Nombres;
    }

    public void setcPers_Nombres(String cPers_Nombres) {
        this.cPers_Nombres = cPers_Nombres;
    }

    public String getcPers_RUC() {
        return cPers_RUC;
    }

    public void setcPers_RUC(String cPers_RUC) {
        this.cPers_RUC = cPers_RUC;
    }

    public Date getdDocCobOpe_FecIni() {
        return dDocCobOpe_FecIni;
    }

    public void setdDocCobOpe_FecIni(Date dDocCobOpe_FecIni) {
        this.dDocCobOpe_FecIni = dDocCobOpe_FecIni;
    }

    public Number getnDoc_ImpTotal() {
        return nDoc_ImpTotal;
    }

    public void setnDoc_ImpTotal(Number nDoc_ImpTotal) {
        this.nDoc_ImpTotal = nDoc_ImpTotal;
    }

    public int getnId_Cartera() {
        return nId_Cartera;
    }

    public void setnId_Cartera(int nId_Cartera) {
        this.nId_Cartera = nId_Cartera;
    }

    public int getnId_Cliente() {
        return nId_Cliente;
    }

    public void setnId_Cliente(int nId_Cliente) {
        this.nId_Cliente = nId_Cliente;
    }

    public int getnId_OpeCodOut() {
        return nId_OpeCodOut;
    }

    public void setnId_OpeCodOut(int nId_OpeCodOut) {
        this.nId_OpeCodOut = nId_OpeCodOut;
    }

    public int getnId_PersDeudor() {
        return nId_PersDeudor;
    }

    public void setnId_PersDeudor(int nId_PersDeudor) {
        this.nId_PersDeudor = nId_PersDeudor;
    }
    
}