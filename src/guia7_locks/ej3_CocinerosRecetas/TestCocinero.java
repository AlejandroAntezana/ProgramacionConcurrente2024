package guia7_locks.ej3_CocinerosRecetas;


public class TestCocinero {
    public static void main(String[] args) {
        Administrador admin = new Administrador(7, 7, 7);
        Thread[] cocineros = new Thread[3];

        for (int i = 0; i < 3; i++) {
            cocineros[i] = new Thread(new Cocinero(admin, "carne", i));
            cocineros[i].start();
        }
    }
}
