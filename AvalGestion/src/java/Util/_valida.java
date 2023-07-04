package Util;

public class _valida {
    
    public _valida() {
    }
    
    public static boolean esNumero(String valor) {

        return esNumeroDecimal(valor) || esNumeroEntero(valor);
    }

    public static boolean esNumeroDecimal(String valor) {
        try {
            if (valor == null || valor.trim().equalsIgnoreCase("nan")) {
                return false;
            }
            Double.parseDouble(valor.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean esNumeroEntero(String valor) {
        try {
            if (valor == null || valor.trim().equalsIgnoreCase("nan")) {
                return false;
            }
            Long.parseLong(valor.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean esEmail(String valor) {
        if (valor == null || valor.trim().length() == 0) {
            return false;
        }
        String s = valor.trim();
        String[] t = s.split("@");
        if (t == null || t.length != 2) {
            return false;
        }
        int posPunto = s.indexOf('.');
        if (posPunto < 0) {
            return false;
        }
        if (s.indexOf('@') + 1 >= posPunto) {
            return false;
        }
        return true;
    }

    public boolean tieneLetras(String valor) {
        if (valor == null || valor.trim().length() == 0) {
            return false;
        }
        for (int i = 0; i < valor.length(); i++) {
            if (Character.isLetter(valor.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean _esNumeroEntero(String valor, long izq, long der) {
        try {
            if (valor == null || valor.trim().equalsIgnoreCase("nan")) {
                throw new NumberFormatException();
            }
            long valorLong = Long.parseLong(valor.trim());
            return izq <= valorLong && valorLong <= der;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
}