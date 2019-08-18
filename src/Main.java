import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Processo> processos = new ArrayList();

        Processo processo1 = new Processo();
        processo1.setId(1);

        Processo processo2 = new Processo();
        processo2.setId(2);

        Processo processo3 = new Processo();
        processo3.setId(3);

        Processo processo4 = new Processo();
        processo4.setId(4);

        Semaforo semaforo = new Semaforo();

        processo1.setSemaforo(semaforo);
        processo2.setSemaforo(semaforo);
        processo3.setSemaforo(semaforo);
        processo4.setSemaforo(semaforo);

        processos.add(processo1);
        processos.add(processo2);
        processos.add(processo3);
        processos.add(processo4);
        semaforo.setProcessos(processos);

        processo3.start();
        processo1.start();
        processo2.start();
        processo4.start();


    }
}
