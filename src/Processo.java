public class Processo extends Thread {
    private int id;
    private Processo gerente;
    private Semaforo semaforo;

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Processo getGerente() {
        return gerente;
    }

    public void setGerente(Processo gerente) {
        this.gerente = gerente;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String enviarSolicitacao() {
        return "Solicitação enviada pelo id " + this.id + " para o gerente " + this.gerente.id;
    }

    public String respostaSolicitacao(String soliticacao) {
        return "Solicitação recebida: " + soliticacao;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (this.gerente == null) {
                    System.out.println("Eleição");
                    this.semaforo.eleicao(this.id);
                }
                //sleep(2000);
                System.out.println(this.id);
                System.out.println(this.enviarSolicitacao());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            try {
                this.semaforo.eleicao(this.id);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
