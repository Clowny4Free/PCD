package Es8_Claude1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServizioPosteggio extends Remote {
    void entra(int idAuto) throws PostoNonDisponibile, RemoteException;

    void esci(int idAuto) throws RemoteException;

    void attendiPosto(int idAuto) throws PostoDisponibile, InterruptedException, RemoteException;
 }
