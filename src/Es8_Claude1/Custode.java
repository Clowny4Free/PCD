package Es8_Claude1;

import java.rmi.RemoteException;

public class Custode extends Thread {

    private int idCustode;
    ServizioCustode controllo;
    int nPosti = 2;

    public Custode(int idCustode, ServizioCustode controllo) {
        this.idCustode = idCustode;
        this.controllo = controllo;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println(idCustode + ": aggiungo 2 posti");
                controllo.aggiungiPosti(nPosti);
                System.out.println("I posti liberi sono:");
                System.out.println(controllo.postiLiberi());
            } catch (RemoteException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
