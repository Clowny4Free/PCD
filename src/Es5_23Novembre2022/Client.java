package Es5_23Novembre2022;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException {

        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            Contenitore contenitore = null;

            contenitore = (Contenitore) registro.lookup("Operazione");


            Utente u1 = new Utente("Gianni", contenitore, "Cipolla");

            u1.start();
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
