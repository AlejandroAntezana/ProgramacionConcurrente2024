package guia7_locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Administrador {
    private int totalComputadoras; //ej: 12
    private int totalLibros; // ej: 7
    private int computadorasOcupadas;
    private int librosOcupados;
    private final Lock mutex = new ReentrantLock();
    private final Condition hayComputadoras;
    private final Condition hayLibros;

    //Situaciones de espera activa
    /*1. Cuando un programador debe esperar por los dos recursos
    * 2. Cuando un programador accede a una computadora pero no hay libros disponibles
    * 3. Cuando accede a un libro pero no hay computadoras disponibles*/

    public Administrador(int cantidadDeComputadoras, int cantidadDeLibros) {
        this.totalComputadoras = cantidadDeComputadoras;
        this.totalLibros = cantidadDeLibros;
        this.computadorasOcupadas = 0;
        this.librosOcupados = 0;
        this.hayComputadoras = mutex.newCondition();
        this.hayLibros = mutex.newCondition();
    }

    public void empezarProgramacion(int id) {
        mutex.lock();
        try {
            while (computadorasOcupadas == totalComputadoras || librosOcupados == totalLibros) {
                System.out.println("Programador " + id + " esperando por recursos");
                hayComputadoras.await();
                hayLibros.await();
            }
            while (computadorasOcupadas == totalComputadoras) {
                System.out.println("Programador " + id + " esperando por computadoras");
                hayComputadoras.await();
            }
            while (librosOcupados == totalLibros) {
                System.out.println("Programador " + id + " esperando por libros");
                hayLibros.await();
            }
            computadorasOcupadas++;
            librosOcupados++;
            System.out.println("Programador " + id + " empezando a programar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
                mutex.unlock();
            }
        }

    public void terminarProgramacion(int id) {
        mutex.lock();
        try {
            computadorasOcupadas--;
            librosOcupados--;
            System.out.println("Programador " + id + " terminando de programar");
            hayComputadoras.signal();
            hayLibros.signal();
        } finally {
            mutex.unlock();
        }
    }

}
