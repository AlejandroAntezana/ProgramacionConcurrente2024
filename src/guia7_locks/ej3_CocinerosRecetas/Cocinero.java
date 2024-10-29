package guia7_locks.ej3_CocinerosRecetas;

public class Cocinero implements Runnable{
    private String asignacion; //Puede estar encargado de 1 de los 3 platos
    private final Administrador admin;
    private int id;

    public Cocinero(Administrador admin, String asignacion, int id){
        this.admin = admin;
        this.asignacion = asignacion;
        this.id = id;
    }

    public void run() {
        /*Un cocinero no consume ingredientes para preparar el plato que tiene asignado, consume los que le faltan.*/
        //sout --> intenta cocinar
        //empezar a cocinar()
        //aca deberia pedir lock y preguntar si hay ingredientes disponibles.
        //si consigue ingredientes prepara el plato
        //sleep() -->simula cocinar el plato
        //terminar de cocinar() libera lock (?
        while (true) {
            System.out.println("El cocinero " + id + " intenta cocinar " + asignacion);
            admin.comenzarCocinar(asignacion);
            try {
                System.out.println("El cocinero " + id + " comienza a cocinar " + asignacion);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("El cocinero " + id + " termina de cocinar " + asignacion);
            admin.terminarCocinar(asignacion);
        }
    }


}
