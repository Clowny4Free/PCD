package Es8_Claude1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Parcheggio extends UnicastRemoteObject implements ServizioPosteggio, ServizioCustode {

    public int nPosti, nLiberi;
    public int[] parcheggio; //se è 0 è libero, se != 0 è occupato;

    public Parcheggio(int numPosti) throws RemoteException {
        this.nPosti = numPosti;

        parcheggio = new int[nPosti];
        for (int i = 0; i < parcheggio.length; i++) {
            parcheggio[i] = 0;
        }
        nLiberi = nPosti;
    }

    public synchronized void aggiungiPosti(int num) throws RemoteException {
        int[] nuovo = new int[nPosti + num];
        for (int i = 0; i < parcheggio.length; i++) {
            nuovo[i] = parcheggio[i];  // copio le auto già parcheggiate
        }
        parcheggio = nuovo;
        nPosti += num;
        nLiberi += num;
        notifyAll();
    }

    public synchronized int postiLiberi() throws RemoteException {
        return nLiberi;
    }

    public synchronized void entra(int idAuto) throws PostoNonDisponibile, RemoteException {
        if (nLiberi == 0) {
            throw new PostoNonDisponibile("Nessun posto disponibile");
        }
        for (int i = 0; i < parcheggio.length; i++) {
            if (parcheggio[i] == 0) {
                parcheggio[i] = idAuto;
                nLiberi--;
                System.out.printf("Auto parcheggiata");
                break;
            }
        }
    }

    public synchronized void esci(int idAuto) throws RemoteException {
        for (int i = 0; i < parcheggio.length; i++) {
            if (parcheggio[i] == idAuto) {
                parcheggio[i] = 0;
                System.out.println("Auto ha lasciato il parcheggio");
                nLiberi++;
                notifyAll();
                break;
            }
        }

    }

    public synchronized void attendiPosto(int idAuto) throws PostoDisponibile, InterruptedException, RemoteException {
        while (nLiberi == 0) {
            wait();
        }
        throw new PostoDisponibile("Posto disponibile");

    }
}
