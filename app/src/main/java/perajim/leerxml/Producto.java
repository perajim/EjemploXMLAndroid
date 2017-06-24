package perajim.leerxml;

import java.text.NumberFormat;

/**
 * Created by peraj on 14/06/2017.
 */

public class Producto {
    String nombre;
    int cantidad;



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nombre + "\n("+nf.format(cantidad)+")";
    }
}
