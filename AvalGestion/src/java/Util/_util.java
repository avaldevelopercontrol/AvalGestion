package Util;

public class _util {
    
    public static String getFechaServidor(String fecha) {

        String wfecha = null;
        fecha = toUpperBlank(fecha);
        fecha = fecha.replace("-", "/");

        if (fecha.length() != 10) {
            return wfecha;
        }
        
        String elem1[] = fecha.split("/");
        if (elem1.length != 3) {
            return wfecha;
        }
        if (!_valida.esNumeroEntero(elem1[0])) {
            return wfecha;
        }
        if (!_valida.esNumeroEntero(elem1[1])) {
            return wfecha;
        }
        if (!_valida.esNumeroEntero(elem1[2])) {
            return wfecha;
        } else {
            wfecha = elem1[2] + "-" + elem1[1] + "-" + elem1[0];
            return wfecha;
        }
    }
    
    public static String toUpperBlank(String valor) {
        if (valor == null) {
            valor = "";
        }
        valor = valor.trim();
        valor = valor.toUpperCase();
        return valor;
    }
    
}
