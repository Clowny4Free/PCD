package Es1_18Febbraio2024;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestorePrenotazioni extends Remote {
    boolean postiLiberi() throws RemoteException;

    int numPostiLiberi() throws RemoteException;

    Posto[] qualiPostiLiberi() throws NoPostiLiberi, RemoteException;

    Posto prenota(Posto p) throws RemoteException;

    Posto[] prenotaMolti(int n) throws NoPostiLiberi, TroppiPosti, RemoteException;

}