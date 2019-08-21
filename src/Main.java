import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        ArrayList<Processo> processos = new ArrayList();
        Semaforo semaforo = new Semaforo();

        // toda vez que um processo é criado ele solicita a eleição e acaba virando o gerente
        // teria q pensar se iremos definir o gerente no Semaforo (compartilhado) ou algum outro lugar
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                Processo processo = new Processo();
                processos.add(processo);
                semaforo.setProcessos(processos);
                processo.setId(processos.size());
                processo.setSemaforo(semaforo);
                processo.start();
            }
        }, 0, 5000);


    }
}
