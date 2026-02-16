package Es5_23Novembre2022;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Contenitore extends Remote {
    int inserisci(String s) throws Pieno, RemoteException;

    String recupera(int n) throws Inesistente, RemoteException;

    void cancella(int n) throws Inesistente, RemoteException;
}
