package problemaLectorEscritor;

public class TestLectorEscritor {
    public static void main(String[] args) {
        Thread[] lectores = new Thread[5];
        Thread[] escritores = new Thread[2];
        Administrador admin = new Administrador(40, 0);

        //inicio de los hilos lectores
        for (int i = 0; i < lectores.length; i++) {
            lectores[i] = new Thread(new Lector(i, admin));
            lectores[i].start();
        }
        //inicio de los hilos escritores
        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor(i, admin));
            escritores[i].start();
        }
    }
    //AGREGAR SEMAFORO QUE CONTROLE CUANDO HAY HOJAS ESCRITAS NUEVAS
}
