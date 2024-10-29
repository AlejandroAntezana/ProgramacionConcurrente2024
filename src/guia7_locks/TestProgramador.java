package guia7_locks;

public class TestProgramador {
    public static void main(String[] args) {
        Administrador administrador = new Administrador(5, 7);
        Thread[] programadores = new Thread[20];

        for (int i = 0; i < programadores.length; i++) {
            programadores[i] = new Thread(new Programador(administrador, i));
            programadores[i].start();
        }


    }
}
