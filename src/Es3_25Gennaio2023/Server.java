package Es3_25Gennaio2023;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Parcheggio parcheggio = new Parcheggio(3, 5);

        Registry registro = LocateRegistry.createRegistry(1099);
        registro.rebind("Parcheggio", parcheggio);
            System.out.println("Server pronto");

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
