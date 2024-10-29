import PC_LectorEscritor.Escritor;
import PC_LectorEscritor.Lector;
import PC_LectorEscritor.Libro;

public class Main {
    public static void main(String[] args) {
        Thread [] lectores = new Thread[10];
        Thread [] escritores = new Thread[5];
        Libro nuevoLibro = new Libro(3, 100);


        for (int i = 0; i < lectores.length; i++) {
           lectores[i]= new Thread(new Lector(nuevoLibro));
           lectores[i].start();
        }

        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor(nuevoLibro));
            escritores[i].start();
        }


    }
}