package PC_LectorEscritor;

public class Escritor implements Runnable {

    private final Libro libro;

    public Escritor(Libro unL) {
        libro = unL;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int cantPag = 0;
                int aux = 0;
                //empezar a escribir
                libro.empezarEscribir();
                //escribe
                aux = (int) (Math.random() * 500 + 500);
                Thread.sleep(aux);   //tiempo escribiendo entre 500 y 1000
                //terminar de escribir
                cantPag = aux / 500;   // si aux<1000, escribe 1 pag, sino escribe 2.
                libro.terminarEscribir(cantPag);
                // Verificar si el libro ya está completo y detener el lector
                if (libro.estaCompleto()) {
                    break;  // Salir del bucle si el libro está completo
                }
            }
        }catch (InterruptedException e) {

        }
    }
}
