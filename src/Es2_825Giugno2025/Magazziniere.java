package Es2_825Giugno2025;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Magazziniere  extends Remote {

    void aggiungiBene(Bene b, int num) throws RemoteException;
}
