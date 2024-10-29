package guia7_locks;

public class Programador implements Runnable {
    private final Administrador administrador;
    private final int id;

    public Programador(Administrador administrador, int id) {
        this.administrador = administrador;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Programador " + id + " va a intentar programar");
        administrador.empezarProgramacion(id);
        try {
            System.out.println("Programador " + id + " programando");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        administrador.terminarProgramacion(id);
        System.out.println("Programador " + id + " terminó de programar");
    }
}
