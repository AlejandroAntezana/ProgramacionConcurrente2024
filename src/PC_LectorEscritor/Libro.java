package PC_LectorEscritor;

import java.util.concurrent.Semaphore;

/* class Libro {
    private int nLectores,nEscritores, cantPagEscritas,totalPag;
    private Semaphore mutex1,mutex2,lectores,escritores;

    public Libro (int cantL,int cantE, int totalPaginas){
        mutex1 = new Semaphore(1);
        mutex2 = new Semaphore(1);
        lectores = new Semaphore(cantE);
        escritores = new Semaphore(cantL);
        this.nLectores = 0;
        this.nEscritores = 0;
        this.totalPag = totalPaginas;
    }

    public void empezarLeer() throws InterruptedException{
        lectores.acquire();
        mutex1.acquire();
        System.out.println("Empieza a Leer");
        nLectores++;
        if (nLectores==1){
            escritores.acquire();
        }
        mutex1.release();
        lectores.release();
    }

    public void terminarLeer() throws InterruptedException{
        mutex1.acquire();
        nLectores--;
        System.out.println("Termina de Leer");
        if (nLectores==0){
            escritores.release();
        }
        mutex1.release();
    }

    public void empezarEscribir() throws InterruptedException{
        mutex2.acquire();
        System.out.println("Empieza a Escribir");
        nEscritores++;
        if (nEscritores==1){
            lectores.acquire();
        }
        mutex2.release();
        escritores.release();
    }

    public void terminarEscribir(int nuevasPag) throws InterruptedException{
        escritores.release();
        mutex2.acquire();
        nEscritores--;
        cantPagEscritas += nuevasPag;
        System.out.println("Termina de Escribir");
        if (nEscritores==0){
            lectores.release();
        }
        mutex2.release();
    }


} */
public class Libro {
    private int nLectores, nEscritores, cantPagEscritas, totalPag;
    private Semaphore mutexLectores, mutexEscritores, escritores, turno;

    public Libro(int cantL, int totalPaginas) {
        mutexLectores = new Semaphore(cantL);  // Controla el acceso a nLectores
        mutexEscritores = new Semaphore(1);  // Controla el acceso a nEscritores
        escritores = new Semaphore(1);  // Permite solo un escritor
        turno = new Semaphore(1);  // Controla el turno para evitar la hambruna
        this.nLectores = 0;
        this.nEscritores = 0;
        this.totalPag = totalPaginas;
    }

    public void empezarLeer() throws InterruptedException {
        turno.acquire();  // Respetar el turno
        mutexLectores.acquire();  // Controla el acceso a nLectores
        nLectores++;
        if (nLectores == 1) {
            escritores.acquire();  // El primer lector bloquea a los escritores
        }
        mutexLectores.release();
        turno.release();  // Libera el turno para otros
        System.out.println("Empieza a Leer");
    }

    public void terminarLeer() throws InterruptedException {
        mutexLectores.acquire();
        nLectores--;
        System.out.println("Termina de Leer");
        if (nLectores == 0) {
            escritores.release();  // El último lector desbloquea a los escritores
        }
        mutexLectores.release();
    }

    public void empezarEscribir() throws InterruptedException {
        turno.acquire();  // Respetar el turno
        escritores.acquire();  // Solo un escritor puede escribir
        System.out.println("Empieza a Escribir");
    }

    public void terminarEscribir(int nuevasPag) throws InterruptedException {
        cantPagEscritas += nuevasPag;
        System.out.println("Termina de Escribir. Páginas escritas: " + cantPagEscritas + "/" + totalPag);

        // Condición de salida: si ya se han escrito todas las páginas, los escritores terminan
        if (cantPagEscritas >= totalPag) {
            System.out.println("Se han escrito todas las páginas del libro.");
            System.exit(0);  // Detiene la ejecución del programa
        }

        // Proteger el acceso a la variable compartida
        mutexEscritores.acquire();
        nEscritores--;  // El escritor actual ha terminado
        if (nEscritores == 0) {
            // Si no hay más escritores activos, permitir que los lectores puedan leer
            mutexLectores.release();
        }
        mutexEscritores.release();
        escritores.release();  // Libera el semáforo de escritores
        turno.release();  // Libera el turno para otros
    }


    public boolean estaCompleto() {
        // Este método revisa si ya se han escrito todas las páginas
        return cantPagEscritas >= totalPag;
    }
}

