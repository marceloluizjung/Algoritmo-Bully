import java.util.ArrayList;

public class Semaforo {
    private ArrayList<Processo> processos = new ArrayList();
    private boolean isEleicao = false;
    private int idEleicao;

    public ArrayList<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(ArrayList<Processo> processos) {
        this.processos = processos;
    }

    private synchronized void stop() throws InterruptedException {
        for (Processo p : this.processos) {
            if (this.idEleicao != p.getId()) {
                p.wait();
            }
        }
    }

    public synchronized void eleicao(int id) throws InterruptedException {

        if (!this.isEleicao) {
            this.isEleicao = true;
            this.idEleicao = id;
            //this.stop();
            Processo processo = this.processos.get(this.processos.size() - 1);
            for (Processo p : this.processos) {
                if (p.getId() > processo.getId()) {
                    processo = p;
                }
            }

            try {
                notifyAll();
                processo.enviarSolicitacao();
            } catch (NullPointerException n) {
                this.eleicao(id);
            }

            for (Processo p : this.processos) {
                p.setGerente(processo);
            }
            this.isEleicao = false;
            notifyAll();
            return;
        }
    }
}
