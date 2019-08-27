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

    public String response(String soliticacao) {
        return "Solicitação recebida: " + soliticacao;
    }

    public boolean testeSaudeInstancia() {
        return true;
    }

    @Override
    public void run() {
        while (true) {
            try {

                if (this.gerente == null && !this.semaforo.getGerente().testeSaudeInstancia()) {
                    System.out.println("Eleição " + this.id);
                    this.semaforo.eleicao(this.id);
                } else if (this.gerente == null) {
                    this.setGerente(this.semaforo.getGerente());
                } else if (!this.semaforo.isEleicao() && this.semaforo.isProcessVerify()) {
                    if (this.semaforo.requestGerente(this)) {
                    } else {
                        this.semaforo.eleicao(this.id);
                    }
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
}
