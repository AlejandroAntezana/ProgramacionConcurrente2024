package guia2_ThreadyRunnable.ej7;

public class Cliente {
    private String nombre;
    private int[] carroCompra;

    public Cliente(String nombre, int[] arrayProductos){
        this.nombre = nombre;
        this.carroCompra = arrayProductos;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int[] getCarroCompra(){
        return this.carroCompra;
    }





}
