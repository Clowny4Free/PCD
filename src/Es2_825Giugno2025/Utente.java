package Es2_825Giugno2025;

import java.rmi.RemoteException;

public class Utente extends Thread {
    private String nome;
    private Bene bene;
    private Acquisto funz;
    private int qta;

    public Utente(String n, Bene b, Acquisto f, int q) {
        this.nome = n;
        this.bene = b;
        this.funz = f;
        this.qta = q;
    }

    public void run() {
        try {
            System.out.println(nome + " sta provando a acquistare: " + bene);
            funz.acquistaBene(bene, qta);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
