package Es2_825Giugno2025;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Magazzino extends UnicastRemoteObject implements Acquisto, Magazziniere {
    private int numeroAlfa, numeroBeta, numeroGamma, qta;


    public Magazzino(int nAlfa, int nBeta, int nGamma) throws RemoteException {
        this.numeroAlfa = nAlfa;
        this.numeroBeta = nBeta;
        this.numeroGamma = nGamma;
    }

    public synchronized void acquistaBene(Bene b, int num) throws BeneNonDisponibile, RemoteException{
        if(b == Bene.alfa){
            if(num > numeroAlfa){
                throw new BeneNonDisponibile();
            }
            else numeroAlfa -= num;
        }
        else if(b == Bene.beta){
            if(num > numeroBeta){
                throw new BeneNonDisponibile();
            }
            else numeroBeta -= num;
        }
        else if(b == Bene.gamma){
            if(num > numeroGamma){
                throw new BeneNonDisponibile();
            }
            else numeroGamma -= num;
        }
    }

    public synchronized int attendiBene(Bene b, int num) throws BeneDisponibile, InterruptedException, RemoteException{
        if(b == Bene.alfa){
            if (num >= numeroAlfa) throw new BeneDisponibile();
            while(num <= numeroAlfa){
                System.out.println("Non ci sono abbastanza articoli in magazzino");
                wait();
            }
            qta = numeroAlfa;
        }
        else if(b == Bene.beta){
            if (num >= numeroBeta) throw new BeneDisponibile();
            while(num <= numeroBeta){
                System.out.println("Non ci sono abbastanza articoli in magazzino");
                wait();
            }
            qta = numeroBeta;
        }
        else if(b == Bene.gamma){
            if (num >= numeroGamma) throw new BeneDisponibile();
            while(num <= numeroGamma){
                System.out.println("Non ci sono abbastanza articoli in magazzino");
                wait();
            }
            qta = numeroGamma;
        }
        return qta;
    }

    public synchronized void aggiungiBene(Bene b, int num) throws RemoteException {
        if (b == Bene.alfa) {
            numeroAlfa += num;
            System.out.println("Alfa rifornito");
            notifyAll();
        }
        if (b == Bene.beta) {
            numeroBeta += num;
            System.out.println("Beta rifornito");
            notifyAll();
        }
        if (b == Bene.gamma) {
            numeroGamma += num;
            System.out.println("Gamma rifornito");
            notifyAll();
        }
    }

    public static void main(String[] args) {
        try{
            Magazzino mag = new Magazzino(10, 8, 5);
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("ServizioMagazzino", mag);
            System.out.println("Server pronto.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}