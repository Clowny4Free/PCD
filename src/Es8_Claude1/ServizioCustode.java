package Es8_Claude1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServizioCustode extends Remote {

    void aggiungiPosti(int num) throws RemoteException;

    int postiLiberi() throws RemoteException;
}
