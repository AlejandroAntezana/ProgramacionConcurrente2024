package problemaLectorEscritor;

public class Lector implements Runnable{
    private int numero;
    private Administrador libro;
    private int paginasLeidas = 0;

    public Lector(int num, Administrador libro){
        this.numero = num;
        this.libro = libro;
    }

    @Override
    public void run() {//implementa la simulacion de acceder y leer un libro;
        //int paginas = (int) (Math.random() * 5 + 1); //Simula la cantidad de paginas que leera el lector entre 1 y 6
        while(getPaginasLeidas() < libro.getTotalPaginas()) { // mientras no haya leido todo el libro sigue leyendo
            /*Al momento de poder leer, debe tomar la cpu para acceder al libro. Luego, al tener el libro, debe
            * decidir si puede leer (pq hay paginas escritas) o no (no hay paginas nuevas)*/
            System.out.println("El lector " + numero + " está intentando leer");
            libro.empezarLeer();
            if(libro.getCantidadPaginasEscritas() > getPaginasLeidas()){
                //simulo el tiempo de lectura y añado las paginas leidas al contador
                //System.out.println("El Lector " + numero + " está intentando leer una pagina");
                try {
                    Thread.sleep(1000); //Simula el tiempo que tarda en leer las paginas (1 seg por pagina)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                leerPaginas(); //Añade las paginas leidas al contador
                System.out.println("Lector " + numero + " ha leido una pagina");
                System.out.println("Lector " + numero + " ha leido " + getPaginasLeidas() + " paginas de " + libro.getTotalPaginas() + " paginas");
                //libro.terminarLeer();
            }else {
                System.out.println("Lector " + numero + " no puede leer porque no hay paginas nuevas");
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                libro.terminarLeer();
            }
            libro.terminarLeer();
        }
    }

    public void leerPaginas() { //metodo que añade las paginas leidas al contador
        paginasLeidas ++;
    }

    public int getPaginasLeidas(){
        return paginasLeidas;
    }
}
