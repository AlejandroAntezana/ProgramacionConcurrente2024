package guia2_ThreadyRunnable.ej1;

public class testeoRecurso {
    public static void main(String[] args) {
        Cliente juan = new Cliente();
        juan.setName("juan Lopez");
        Cliente ines = new Cliente();
        ines.setName("Ines Garcia");
        juan.start();
        ines.start();
    }
}
