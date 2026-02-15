package Es3_25Gennaio2023;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Parcheggio extends UnicastRemoteObject implements GestioneParcheggio {
    private int nPiani, nStalli;
    private int nTot;
    private boolean[][] occupati; //se occupato = true, se libero = false
    private int nLiberi;

    public Parcheggio(int nP, int nS) throws RemoteException {
        this.nPiani = nP;
        this.nStalli = nS;

        nTot = nP * nS;
        occupati = new boolean[nP][nS];
        nLiberi = nTot;

        for (int i = 0; i < nP; i++) {
            for (int j = 0; j < nS; j++) {
                occupati[i][j] = false;
            }
        }
    }

    public synchronized void ingresso() throws RemoteException {
        while (nLiberi == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nLiberi--;
    }

    public synchronized void uscita() throws RemoteException{
        nLiberi++;
        notifyAll();
    }

    public synchronized void parcheggia(int piano) throws PianoInesistente, NoStalliLiberi, RemoteException {
        boolean preso = false;

        if (piano > nPiani || piano < 0) throw new PianoInesistente();

        for (int j = 0; j < nStalli; j++) {
            if (!occupati[piano][j]) {
                occupati[piano][j] = true;
                preso = true;
                break;
            }
        }
        if (!preso) {
            throw new NoStalliLiberi();
        }
    }

    public synchronized void partenza(int piano) throws PianoInesistente, RemoteException {

        if (piano > nPiani || piano < 0) throw new PianoInesistente();

        for (int j = 0; j < nStalli; j++) {
            if (occupati[piano][j]) {
                occupati[piano][j] = false;
                break;
            }
        }
    }

    public synchronized int postiLiberi() throws RemoteException{
        return nLiberi;
    }

    public synchronized int stalliLiberi(int piano) throws PianoInesistente, RemoteException{

        int sl = 0;

        if (piano > nPiani || piano < 0) throw new PianoInesistente();

        for (int j = 0; j < nStalli; j++) {
            if (!occupati[piano][j]){
                sl++;
            }
        }
        return sl;
    }
}
