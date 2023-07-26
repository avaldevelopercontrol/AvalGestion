package Models;

public class av_cliente {

    private int idUsuario;
    private int nId_Cliente;
    private String cCli_NroDoc;
    private String cCli_Nombre;
    private boolean bEstado;
    
    private String dFecIniProceso;
    private String dFecFinProceso;
    
    public String getdFecIniProceso() {
        return dFecIniProceso;
    }

    public void setdFecIniProceso(String dFecIniProceso) {
        this.dFecIniProceso = dFecIniProceso;
    }

    public String getdFecFinProceso() {
        return dFecFinProceso;
    }

    public void setdFecFinProceso(String dFecFinProceso) {
        this.dFecFinProceso = dFecFinProceso;
    }

    public boolean isbEstado() {
        return bEstado;
    }

    public void setbEstado(boolean bEstado) {
        this.bEstado = bEstado;
    }

    public String getcCli_Nombre() {
        return cCli_Nombre;
    }

    public void setcCli_Nombre(String cCli_Nombre) {
        this.cCli_Nombre = cCli_Nombre;
    }

    public String getcCli_NroDoc() {
        return cCli_NroDoc;
    }

    public void setcCli_NroDoc(String cCli_NroDoc) {
        this.cCli_NroDoc = cCli_NroDoc;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getnId_Cliente() {
        return nId_Cliente;
    }

    public void setnId_Cliente(int nId_Cliente) {
        this.nId_Cliente = nId_Cliente;
    }
    
}