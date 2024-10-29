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





}
