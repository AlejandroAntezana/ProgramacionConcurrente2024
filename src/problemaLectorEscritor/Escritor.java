package problemaLectorEscritor;

public class Escritor implements Runnable{
    private int numero;
    private Administrador libro;

    public Escritor(int num, Administrador libro){
        this.numero = num;
        this.libro = libro;
    }

    @Override
    public void run() {
        while (libro.getCantidadPaginasEscritas() <= libro.getTotalPaginas()) {
            libro.empezarEscribir();
            System.out.println("Escritor " + numero + " esta escribiendo");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            libro.escribirPagina(); // ESTE METODO ESCRIBE ENTRE 1 Y 6 PAGINAS
            System.out.println("Escritor " + numero + " ha escrito una pagina - " + "Total de paginas escritas: " + libro.getCantidadPaginasEscritas() + " de " + libro.getTotalPaginas() + " paginas");
            libro.terminarEscribir();
        }
        if (libro.getCantidadPaginasEscritas() > libro.getTotalPaginas()) {
            System.out.println("Escritor " + numero + " ha terminado de escribir");
        }
    }
}
