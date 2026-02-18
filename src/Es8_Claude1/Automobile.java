package Es8_Claude1;

import java.rmi.RemoteException;
import java.util.Random;

public class Automobile extends Thread {

    private int idAuto;
    ServizioPosteggio parcheggio;
    Random rnd = new Random();

    public Automobile(int id, ServizioPosteggio parcheggio) {
        this.idAuto = id;
        this.parcheggio = parcheggio;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(idAuto + ": provo a entrare nel parcheggio");
                try {
                    parcheggio.entra(idAuto);
                } catch (PostoNonDisponibile pnd) {
                    try {
                        parcheggio.attendiPosto(idAuto);
                    } catch (PostoDisponibile | InterruptedException pd) {
                        parcheggio.entra(idAuto);
                    }
                }
                Thread.sleep(rnd.nextInt(10000));
                System.out.println(idAuto + ": esco dal parcheggio");
                parcheggio.esci(idAuto);
            } catch (RemoteException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
