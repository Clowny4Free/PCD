package Es5_23Novembre2022;

import java.rmi.RemoteException;

public class Utente extends Thread {
    private String nome;
    Contenitore contenitore;
    private String stringa;
    private int id;

    public Utente(String NOME, Contenitore contenitore, String stringa) {
        this.nome = NOME;
        this.contenitore = contenitore;
        this.stringa = stringa;
    }

    public void run() {
        try {
            System.out.println("Utente " + nome + ": inserisco la stringa " + stringa + " nel contenitore");
            contenitore.inserisci(stringa);

            id = 1;
            System.out.println("Stringa inserita");
            System.out.println("Recupero la stringa " + id + ", la stringa è: " + stringa);
            contenitore.recupera(id);
            System.out.println("Cancello la stringa " + id);
            contenitore.cancella(id);
            System.out.println("Stringa cancellata");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
