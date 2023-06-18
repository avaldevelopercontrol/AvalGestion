package Models;

public class av_cliente {

    private int nId_Cliente;
    private String cCli_NroDoc;
    private String cCli_Nombre;
    private boolean bEstado;

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

    public int getnId_Cliente() {
        return nId_Cliente;
    }

    public void setnId_Cliente(int nId_Cliente) {
        this.nId_Cliente = nId_Cliente;
    }
    
}