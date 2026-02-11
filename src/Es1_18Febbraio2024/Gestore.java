package Es1_18Febbraio2024;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Gestore extends UnicastRemoteObject implements GestorePrenotazioni {
    private int nSedia, nFile;
    private int nLiberi;
    private Posto[][] teatro;

    public static void main(String[] args) throws Exception{
        Gestore gestore = new Gestore(20, 26);
        Registry registro = LocateRegistry.createRegistry(1099);
        registro.rebind("GestoreTeatro", gestore);
        System.out.println("Server pronto");
    }

    public Gestore(int ns, int nf) throws RemoteException{
        nSedia = ns;
        nFile = nf;
        nLiberi = ns * nf;
        teatro = new Posto[nf][ns];

        for(int i = 0; i < nf; i++){
            for(int j = 0; j < ns; j++){
                teatro[i][j] = new Posto((char)('A' + i), j + 1, false);
            }
        }
    }

    public synchronized boolean postiLiberi()  throws RemoteException {
        return nLiberi > 0;
    }

    public synchronized int numPostiLiberi() throws RemoteException{
        return nLiberi;
    }

    public synchronized Posto[] qualiPostiLiberi() throws NoPostiLiberi, RemoteException{
        if(nLiberi == 0) throw new NoPostiLiberi();
        Posto[] tmp = new Posto[nLiberi];
        int k = 0;
        for(int i = 0; i < nFile; i++){
            for(int j = 0; j < nSedia; j++){
                if (!teatro[i][j].isOccupato()){
                    tmp[k++] = teatro[i][j];
                }
            }
        }
        return tmp;
    }

    public synchronized Posto prenota(Posto p) throws NoPostiLiberi, RemoteException{
        int fila = (int)(p.getFila() - 'A');
        int sedia = p.getSedia() - 1;
        if(!teatro[fila][sedia].isOccupato()){
            teatro[fila][sedia].setOccupato(true);
            nLiberi--;
            return teatro[fila][sedia];
        }
        else throw new NoPostiLiberi();
    }

    private int trovaposto(int n, int f){
        for(int i=0; i<nSedia-n; i++){
            boolean trovato = false;
            for(int j=i; j<i+n;j++){
                if(teatro[f][j].isOccupato()){
                    trovato = false;
                    break;
                }
                else trovato = true;
            }
            if(trovato){
                return i;
            }
        }
        return -1;
    }

    private int trovafila(int n){
        for(int i=0; i<nFile; i++){
            if(trovaposto(n,i)!= -1){
                return i;
            }
        }
        return -1;
    }

    public synchronized Posto[] prenotaMolti(int n) throws NoPostiLiberi, TroppiPosti, RemoteException{
        if(n < 2 || n > 5) throw new TroppiPosti();
        Posto[] tmp = new Posto[n];
        int fila = trovafila(n);
        if(fila == -1) throw new TroppiPosti();
        int primoposto = trovaposto(n, fila);
        for(int i = 0; i < n; i++) {
            teatro[fila][primoposto + i].setOccupato(true);
            tmp[i] = teatro[fila][primoposto + i];
        }
        return tmp;
    }
}
