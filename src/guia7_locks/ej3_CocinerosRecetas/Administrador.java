package guia7_locks.ej3_CocinerosRecetas;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Administrador {
    private int cantCarne;
    private int cantVegetales;
    private int cantPasta;
    private int carneDisponible;
    private int pastaDisponible;
    private int vegetalesDisponibles;
    private final Lock mutex = new ReentrantLock();
    private final Condition hayCarne;
    private final Condition hayVegetales;
    private final Condition hayPasta;

    public Administrador(int cantCarne, int cantVegetales, int cantPasta){
        this.cantCarne = cantCarne;
        this.cantPasta = cantPasta;
        this.cantVegetales = cantVegetales;
        this.carneDisponible = cantCarne;
        this.pastaDisponible = cantPasta;
        this.vegetalesDisponibles = cantVegetales;
        this.hayCarne = mutex.newCondition();
        this.hayPasta = mutex.newCondition();
        this.hayVegetales = mutex.newCondition();
    }

    public void comenzarCocinar(String asignacion){
        mutex.lock();
        try{
            switch(asignacion){
                case "carne": //Si mi asignacion es carne debo pedir pasta y vegetales
                    while(vegetalesDisponibles <= 0){
                        System.out.println("Esperando por vegetales");
                        hayVegetales.await();
                    }
                    vegetalesDisponibles--;
                    while(pastaDisponible <= 0){
                        System.out.println("Esperando por pasta");
                        hayPasta.await();
                    }
                    pastaDisponible--;
                    break;
                case "pasta":
                    while(carneDisponible <= 0){
                        System.out.println("Esperando por carne");
                        hayCarne.await();
                    }
                    carneDisponible--;
                    while (vegetalesDisponibles <= 0){
                        System.out.println("Esperando por vegetales");
                        hayVegetales.await();
                    }
                    vegetalesDisponibles--;
                    break;
                case "vegetales":
                    while(carneDisponible <= 0){
                        System.out.println("Esperando por carne");
                        hayCarne.await();
                    }
                    carneDisponible--;
                    while(pastaDisponible <= 0){
                        System.out.println("Esperando por pasta");
                        hayPasta.await();
                    }
                    pastaDisponible--;
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void terminarCocinar(String asignacion){
        mutex.lock();
        try{
            switch(asignacion){
                case "carne":
                    carneDisponible++;
                    hayCarne.signal();
                    break;
                case "pasta":
                    pastaDisponible++;
                    hayPasta.signal();
                    break;
                case "vegetales":
                    vegetalesDisponibles++;
                    hayVegetales.signal();
                    break;
            }
        } finally {
            mutex.unlock();
        }
    }




}
