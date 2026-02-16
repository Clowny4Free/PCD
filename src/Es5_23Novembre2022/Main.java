package Es5_23Novembre2022;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        ImplContenitore contenitore = new ImplContenitore(10);
        Utente u1 = new Utente("Gianni", contenitore, "Cipolla");

        u1.start();
    }
}
