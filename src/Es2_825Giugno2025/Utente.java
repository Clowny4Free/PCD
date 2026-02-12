package Es2_825Giugno2025;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Utente extends Thread {

    private int id;
    private Bene bene;
    private int qta;
    Acquisto magazzino;

    public Utente(int ID, Bene b, int num, Acquisto mag) {
        this.id = ID;
        this.bene = b;
        this.qta = num;
        this.magazzino = mag;
    }

    public void run() {
        boolean acquistato = false;
        try {
            while (acquistato != false) {
                try{
                    magazzino.acquistaBene(bene, qta);
                    acquistato = true;
                } catch (BeneNonDisponibile bn){
                    System.out.println("Il prodotto non è disponibile");
                    try {
                        magazzino.attendiBene(bene, qta);
                    } catch (BeneDisponibile bd) {
                    }catch (InterruptedException ie){
                        return;
                    }
                }

            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);

            //provo a comprare, se riesce bene, se non riesce attendo l'acquisto e successivamente riprovoù
        }
    }

    public static void main(String[] args) {
        Acquisto magazzino = null;

        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            magazzino = (Acquisto) registro.lookup("ServizioMagazzino");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Amministratore admin = new Amministratore((Magazziniere) magazzino);

        Utente u1 = new Utente(1, Bene.beta, 4, magazzino);
        Utente u2 = new Utente(2, Bene.alfa, 12, magazzino);
        Utente u3 = new Utente(3, Bene.gamma, 21, magazzino);

        u1.start();
        u2.start();
        u3.start();
        admin.start();
    }
}
