package Es2_825Giugno2025;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Magazzino extends UnicastRemoteObject implements Acquisto, Magazziniere {
    private int numeroAlfa, numeroBeta, numeroGamma;
    public int qta;
    private boolean turno;

    public Magazzino(int nAlfa, int nBeta, int nGamma) throws RemoteException {
        this.numeroAlfa = nAlfa;
        this.numeroBeta = nBeta;
        this.numeroGamma = nGamma;
    }

    public synchronized void acquistaBene(Bene b, int num) throws BeneNonDisponibile, RemoteException {
        if (b == Bene.alfa) {
            if (num > numeroAlfa) throw new BeneNonDisponibile();
            else {
                numeroAlfa -= num;
            }
        } else if (b == Bene.beta) {
            if (num > numeroBeta) throw new BeneNonDisponibile();
            else {
                numeroBeta -= num;
            }
        } else if (b == Bene.gamma) {
            if (num > numeroGamma) throw new BeneNonDisponibile();
            else {
                numeroGamma -= num;
            }
        }
    }

    public synchronized int attendiBene(Bene b, int num) throws BeneDisponibile, InterruptedException, RemoteException {
        while (true) {
            if (b == Bene.alfa) {
                while (num > numeroAlfa) {
                    wait();
                }
                qta = numeroAlfa;
                throw new BeneDisponibile();

            }
        } else if (b == Bene.beta) {
            while (num > numeroBeta) {
                wait();
            }
            qta = numeroAlfa;
            throw new BeneDisponibile();
        } else if (b == Bene.gamma) {
            while (num > numeroGamma) {
                wait();
            }
            qta = numeroGamma;
            throw new BeneDisponibile();
        }
    }
}

public synchronized void aggiungiBene(Bene b, int num) throws RemoteException {
    if (b == Bene.alfa) {
        numeroAlfa += num;
        notifyAll();
    }
    if (b == Bene.beta) {
        numeroBeta += num;
        notifyAll();
    }
    if (b == Bene.gamma) {
        numeroGamma += num;
        notifyAll();
    }
}
}
