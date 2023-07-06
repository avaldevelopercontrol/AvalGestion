package Models;

public class av_DocxCobrarOpe {
    
    private String cSigla_OpeCodCliOut;
    private int NroContactabilidad;
    private String cNombre_OpeCodCliOut;
    
    private int nId_Cliente;
    private int nId_Cartera;
    private int nId_OpeCodOut;
    private int nId_OpeCodOutNp2;
    private String cNombre_OpeCodCliOutN2;
    private int nId_UsuOpe;
    private String cUsr_Nombres;
    private String dDocCobOpe_FecIni;
    private String dDocCobOpe_FecFin;
    private int nId_DocxCobrar;
    private String cDoc_Numero;
    private Number nDoc_ImpSaldo;

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
    
    public String getcDoc_Numero() {
        return cDoc_Numero;
    }

    public void setcDoc_Numero(String cDoc_Numero) {
        this.cDoc_Numero = cDoc_Numero;
    }

    public String getcNombre_OpeCodCliOutN2() {
        return cNombre_OpeCodCliOutN2;
    }

    public void setcNombre_OpeCodCliOutN2(String cNombre_OpeCodCliOutN2) {
        this.cNombre_OpeCodCliOutN2 = cNombre_OpeCodCliOutN2;
    }

    public String getcUsr_Nombres() {
        return cUsr_Nombres;
    }

    public void setcUsr_Nombres(String cUsr_Nombres) {
        this.cUsr_Nombres = cUsr_Nombres;
    }

    public String getdDocCobOpe_FecFin() {
        return dDocCobOpe_FecFin;
    }

    public void setdDocCobOpe_FecFin(String dDocCobOpe_FecFin) {
        this.dDocCobOpe_FecFin = dDocCobOpe_FecFin;
    }

    public String getdDocCobOpe_FecIni() {
        return dDocCobOpe_FecIni;
    }

    public void setdDocCobOpe_FecIni(String dDocCobOpe_FecIni) {
        this.dDocCobOpe_FecIni = dDocCobOpe_FecIni;
    }

    public Number getnDoc_ImpSaldo() {
        return nDoc_ImpSaldo;
    }

    public void setnDoc_ImpSaldo(Number nDoc_ImpSaldo) {
        this.nDoc_ImpSaldo = nDoc_ImpSaldo;
    }

    public int getnId_DocxCobrar() {
        return nId_DocxCobrar;
    }

    public void setnId_DocxCobrar(int nId_DocxCobrar) {
        this.nId_DocxCobrar = nId_DocxCobrar;
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

    public int getnId_UsuOpe() {
        return nId_UsuOpe;
    }

    public void setnId_UsuOpe(int nId_UsuOpe) {
        this.nId_UsuOpe = nId_UsuOpe;
    }
    
    public String getcNombre_OpeCodCliOut() {
        return cNombre_OpeCodCliOut;
    }

    public void setcNombre_OpeCodCliOut(String cNombre_OpeCodCliOut) {
        this.cNombre_OpeCodCliOut = cNombre_OpeCodCliOut;
    }
    
    public int getNroContactabilidad() {
        return NroContactabilidad;
    }

    public void setNroContactabilidad(int NroContactabilidad) {
        this.NroContactabilidad = NroContactabilidad;
    }

    public String getcSigla_OpeCodCliOut() {
        return cSigla_OpeCodCliOut;
    }

    public void setcSigla_OpeCodCliOut(String cSigla_OpeCodCliOut) {
        this.cSigla_OpeCodCliOut = cSigla_OpeCodCliOut;
    }
    
}