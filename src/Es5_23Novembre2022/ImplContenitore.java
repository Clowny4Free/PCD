package Es5_23Novembre2022;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ImplContenitore extends UnicastRemoteObject implements Contenitore {
    private String[] contenitore;
    int dim, sLiberi;
    int identificatore = 0;
    HashMap<Integer, Integer> mappa;

    public ImplContenitore(int dimensione) throws RemoteException {
        this.dim = dimensione;

        contenitore = new String[dimensione];
        sLiberi = dimensione;
        mappa = new HashMap<>();
    }

    public synchronized int inserisci(String s) throws Pieno, RemoteException {
        if (sLiberi == 0) {
            throw new Pieno();
        }
        for (int i = 0; i < contenitore.length; i++) {
            if (contenitore[i] == null) {
                contenitore[i] = s;
                sLiberi--;
                identificatore++;
                mappa.put(identificatore, i);
                break;
            }
        }
        return identificatore;
    }

    public synchronized String recupera(int n) throws Inesistente, RemoteException {
        if (!mappa.containsKey(n)){
            throw new Inesistente();
        }
        int pos = mappa.get(n);
        return contenitore[pos];
    }

    public synchronized void cancella(int n) throws Inesistente, RemoteException {
        if (!mappa.containsKey(n)){
            throw new Inesistente();
        }
        int pos = mappa.get(n);
        contenitore[pos] = null;
        mappa.remove(n);
        sLiberi++;
    }
}
