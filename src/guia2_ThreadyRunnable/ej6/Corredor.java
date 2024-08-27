package guia2_ThreadyRunnable.ej6;

public class Corredor implements Runnable{
    private String nombre = "";
    private int distanciaRecorrida;

    public Corredor(String nombre){
        this.nombre = nombre;
        this.distanciaRecorrida = 0;
    }

    @Override
    public void run() {
        System.out.println("El corredor " + nombre + " Comenzo la carrera");
        try{
            int pasos=0;
            while (pasos <= 100){ //Reemplazar pasos por la variable distanciaRecorrida
                distanciaRecorrida += (int) (Math.random()*10);
                Thread.sleep(100);
                pasos++;
            }
        } catch (InterruptedException e) {
            System.out.println(nombre + " Se detuvo.");
        }
        if(distanciaRecorrida == 100){
            System.out.println(nombre + " El corredor Termino la carrera");
        }
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public String getNombre(){
        return this.nombre;
    }
}
