package Models;

public class av_cartera {
    
    private int idUsuario;
    private int nId_Cartera;
    private String cCar_Nombre;

    public String getcCar_Nombre() {
        return cCar_Nombre;
    }

    public void setcCar_Nombre(String cCar_Nombre) {
        this.cCar_Nombre = cCar_Nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getnId_Cartera() {
        return nId_Cartera;
    }

    public void setnId_Cartera(int nId_Cartera) {
        this.nId_Cartera = nId_Cartera;
    }
    
}