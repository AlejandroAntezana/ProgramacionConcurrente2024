package guia2_ThreadyRunnable.ej1;

public class Recurso {
    static void uso(){
        Thread t = Thread.currentThread();
        System.out.println("En Recurso: soy: " + t.getName());
    }
}
