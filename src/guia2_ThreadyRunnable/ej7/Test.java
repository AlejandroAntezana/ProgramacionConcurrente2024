package guia2_ThreadyRunnable.ej7;

public class Test {
    public static void main(String[] args) {
        //esperarXsegundos2(5);
        long timeStamp = System.currentTimeMillis();
        System.out.println((System.currentTimeMillis() - timeStamp) / 1000);
        esperarXsegundos2(5);
        System.out.println((System.currentTimeMillis() - timeStamp) / 1000);
        ;
    }

    public static void esperarXsegundos2(int valor){
        long timeStamp = System.currentTimeMillis();
        long initialTime = (System.currentTimeMillis() - timeStamp) / 1000;
        long finalTime = initialTime + valor;

        while(initialTime < finalTime){
            initialTime = (System.currentTimeMillis() - timeStamp) / 1000;
            System.out.println(initialTime);
        }

    }
}
