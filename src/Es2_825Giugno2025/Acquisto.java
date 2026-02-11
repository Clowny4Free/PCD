package Es2_825Giugno2025;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Acquisto extends Remote {

    void acquistaBene(Bene b, int num) throws BeneNonDisponibile, RemoteException;

    int attendiBene(Bene b, int num) throws BeneDisponibile, InterruptedException, RemoteException;
}
