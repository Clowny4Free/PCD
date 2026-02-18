package Es8_Claude1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException {

        Parcheggio parcheggio = new Parcheggio(10);

        Registry registro = LocateRegistry.createRegistry(1099);
        registro.rebind("Parcheggio", parcheggio);
        registro.rebind("Custode", parcheggio);
        System.out.println("Server Pronto");

    }
}
