package problemaLectorEscritor;

import java.util.concurrent.Semaphore;

public class Administrador {
    private Semaphore mutex_bloqueoContadorEscritores; //Semaforo binario basico
    private Semaphore mutex_bloqueoContadorLectores; //Semaforo binario basico
    private Semaphore mutex_bloqueoEscritor; //permite el paso de 1 Escritor o 1 (o mas) Lectores
    private Semaphore mutex_bloqueoLector;
    private Semaphore mutex_bloqueoPaginas;
    private Semaphore mutex_esperaEscritura;
    private int cantidad_lectores;//Contador de lectores en la Seccion Critica
    private int cantidad_escritores;//Contador de lectores en la Seccion Critica
    private int total_paginas;
    private int cantidad_paginas_escritas;

    public Administrador(int totalPaginas, int cantPaginas){
        this.cantidad_lectores = 0;
        this.cantidad_escritores = 0;
        this.mutex_bloqueoContadorEscritores = new Semaphore(1);
        this.mutex_bloqueoContadorLectores = new Semaphore(1); //este semaforo protege el acceso a los contadores de lectores y escritores
        this.mutex_bloqueoPaginas = new Semaphore(1); //este semaforo protege el acceso a las paginas escritas por los escritores
        this.mutex_bloqueoEscritor = new Semaphore(1); //este semaforo permite el paso de 1 escritor
        this.mutex_bloqueoLector = new Semaphore(1); //este semaforo permite el paso de 1 lector
        this.mutex_esperaEscritura = new Semaphore(1);
        this.total_paginas = totalPaginas;
        this.cantidad_paginas_escritas = cantPaginas;

    }

    public void empezarLeer() { //Metodo que se llama antes de leer
       try {
           //mutex_bloqueoLector.acquire(); //Bloquea el acceso a los lectores
           mutex_bloqueoContadorLectores.acquire(); //protege el acceso al contador de lectores
           cantidad_lectores++; //Aumenta el contador de lectores

           if(cantidad_lectores == 1){
                mutex_bloqueoEscritor.acquire(); //Si es el primer lector, bloquea el acceso a los escritores
               mutex_esperaEscritura.acquire();
           }

           mutex_bloqueoContadorLectores.release(); //Libera el acceso a la seccion critica
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

    public void terminarLeer() { //Metodo que se llama despues de leer
        try {
            mutex_bloqueoContadorLectores.acquire(); //protege el acceso al contador de lectores
            cantidad_lectores--; //Disminuye el contador de lectores

            if(cantidad_lectores == 0){
                mutex_bloqueoEscritor.release(); //Si es el ultimo lector, libera el acceso a los escritores
                mutex_esperaEscritura.release();
            }
            //mutex_bloqueoLector.release(); //Libera el acceso a los lectores
            mutex_bloqueoContadorLectores.release(); //Libera el acceso a la seccion critica
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void empezarEscribir() { //Metodo que se llama antes de escribir
        try {
            mutex_bloqueoEscritor.acquire();
            mutex_bloqueoLector.acquire();
            mutex_bloqueoContadorEscritores.acquire(); //protege el acceso al contador de escritores
            cantidad_escritores++; //Aumenta el contador de escritores

            if(cantidad_escritores == 1){
                //mutex_bloqueoEscritor.acquire(); //Si es el primer escritor, bloquea el acceso a los lectores
                //mutex_bloqueoLector.acquire();
                mutex_bloqueoPaginas.acquire(); //Bloquea el acceso a las paginas escritas por los escritores
                mutex_esperaEscritura.acquire();
            }
            mutex_bloqueoContadorEscritores.release(); //Libera el acceso a los escritores
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void terminarEscribir() {
        //Metodo que se llama despues de escribir
        try {
            mutex_bloqueoContadorEscritores.acquire(); //protege el acceso al contador de escritores
            cantidad_escritores--; //Disminuye el contador de escritores

            if(cantidad_escritores == 0){
                mutex_bloqueoLector.release(); //Si es el ultimo escritor, libera el acceso a los lectores
                mutex_esperaEscritura.release();
            }
            mutex_bloqueoContadorEscritores.release(); //Libera el acceso a los escritores
            mutex_bloqueoPaginas.release(); //Libera el acceso a las paginas escritas por los escritores
            mutex_bloqueoEscritor.release(); //Libera el acceso a los escritores

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void escribirPagina() {
        //Metodo que simula la escritura de una pagina
        /*int paginas = 1;
        paginas = (int) (Math.random() * 5 + 1); //Simula la cantidad de paginas que escribira el escritor entre 1 y 6*/
        cantidad_paginas_escritas ++;
    }

    public int getCantidadPaginasEscritas(){
        return cantidad_paginas_escritas;
    }

    public int getTotalPaginas(){
        return total_paginas;
    }

    public boolean finalizado() {
        return cantidad_paginas_escritas >= total_paginas;
    }


}
