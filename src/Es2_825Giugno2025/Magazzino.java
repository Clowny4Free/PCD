package Es2_825Giugno2025;

import java.rmi.RemoteException;

public class Magazzino implements Acquisto, Magazziniere{
    private int numeroAlfa, numeroBeta, numeroGamma;
    public int qta;
    private boolean turno;

    public Magazzino(int nAlfa, int nBeta, int nGamma){
        this.numeroAlfa = nAlfa;
        this.numeroBeta = nBeta;
        this.numeroGamma = nGamma;
    }

    public synchronized void acquistaBene(Bene b, int num) throws BeneNonDisponibile, RemoteException{
        if(b == Bene.alfa){
            if(num > numeroAlfa) throw new BeneNonDisponibile();
            else{
                numeroAlfa -= num;
            }
        }
        else if (b == Bene.beta){
            if(num >= numeroBeta) throw new BeneNonDisponibile();
            else{
                numeroBeta -= num;
            }
        }
        else if(b == Bene.gamma){
            if(num >= numeroGamma) throw new BeneNonDisponibile();
            else{
                numeroGamma -= num;
            }
        }
    }

    public synchronized int attendiBene(Bene b, int num) throws BeneDisponibile, InterruptedException, RemoteException{
        while (true) {
            if (b == Bene.alfa) {
                if (num <= numeroAlfa) {
                    qta = numeroAlfa;
                    throw new BeneDisponibile();
                } else {
                    System.out.println("Attesa bene");
                    wait();
                    qta = numeroAlfa;
                    System.out.println("Bene disponibile" + qta);
                }
            } else if (b == Bene.beta) {
                if (num <= numeroBeta) {
                    qta = numeroBeta;
                    throw new BeneDisponibile();
                } else {
                    System.out.println("Attesa bene");
                    wait();
                    qta = numeroBeta;
                    System.out.println("Bene disponibile" + qta);

                }
            } else if (b == Bene.gamma) {
                if (num <= numeroGamma) {
                    qta = numeroGamma;
                    throw new BeneDisponibile();
                } else {
                    System.out.println("Attesa bene");
                    wait();
                    qta = numeroGamma;
                    System.out.println("Bene disponibile" + qta);

                }
            }
            return qta;
        }
    }

    public synchronized void aggiungiBene(Bene b, int num) throws RemoteException{
        if(b == Bene.alfa || num > numeroAlfa)
    }
}
