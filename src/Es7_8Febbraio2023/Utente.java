package Es7_8Febbraio2023;

import java.io.Serializable;

public class Utente extends Thread implements Serializable {
    private int id;
    private int val1, val2;
    Contenitore contenitore;

    public Utente(int id, int val1, int val2, Contenitore contenitore){
        this.id = id;
        this.val1 = val1;
        this.val2 = val2;
        this.contenitore = contenitore;
    }

    public void run() {
        try {
            System.out.println(id + ": inserisco nel contenitore il valore: " + val1);
            contenitore.inserisci(val1);
            System.out.println(id + ": inserisco nel contenitore il valore: " + val2);
            contenitore.inserisci(val2);
            System.out.println(id + ": eseguo la somma: ");
            System.out.println(contenitore.somma());
            System.out.println(id + ": svuoto il contenitore ");
            contenitore.cancella();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
