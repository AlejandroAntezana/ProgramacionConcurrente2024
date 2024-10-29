package PC_LectorEscritor;

public class Lector implements Runnable{

    private Libro libro;
    public Lector (Libro unL){
        libro=unL;
    }

    @Override
    public void run() {
        try {
            while (true) {
                libro.empezarLeer();
                Thread.sleep((int) Math.random() * 500 + 500);//simula que lee
                libro.terminarLeer();
                // Verificar si el libro ya está completo y detener el lector
                if (libro.estaCompleto()) {
                    break;  // Salir del bucle si el libro está completo
                }
            }
        }catch(InterruptedException e){
            System.out.println("Fallo");
        }
    }
}
