package Es4_9Luglio2025;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImpiantoSemaforico extends Remote {
    void arrivoPrincipale(int id) throws InterruptedException, RemoteException;

    void arrivoSecondario(int id) throws InterruptedException, RemoteException;

    void rilascioIncrocio(int id) throws RemoteException;
}

