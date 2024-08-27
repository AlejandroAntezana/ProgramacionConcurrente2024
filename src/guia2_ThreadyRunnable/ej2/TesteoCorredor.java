package guia2_ThreadyRunnable.ej2;

import javax.swing.table.TableRowSorter;

public class TesteoCorredor {
    public static void main(String[] args) {
        Corredor[] corredores = new Corredor[4];
        Thread[] hilos = new Thread[4];

        for (int i = 0; i < 4; i++) {
            corredores[i] = new Corredor("Corredor " + (i + 1));
            hilos[i] = new Thread(corredores[i]);
            hilos[i].start();
        }

        try {
            for (int i = 0; i < 4; i++) {
                hilos[i].join(); // Esperar a que todos los corredores terminen
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Encontrar al corredor que hizo la mayor distancia
        Corredor ganador = corredores[0];
        for (int i = 1; i < 4; i++) {
            System.out.println("Distancia Recorrida del Corredor " + corredores[i] + " es: "
                    + corredores[i].getDistanciaRecorrida() + " mts.");
            if (corredores[i].getDistanciaRecorrida() > ganador.getDistanciaRecorrida()) {
                ganador = corredores[i];
            }
        }

        System.out.println("El ganador es " + ganador.getNombre() + " con una distancia de "
                + ganador.getDistanciaRecorrida() + " metros.");
    }
}
