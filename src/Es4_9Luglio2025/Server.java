package Es4_9Luglio2025;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException {
        Incrocio incrocio = new Incrocio();
        Registry registro = LocateRegistry.createRegistry(1099);
        registro.rebind("Incrocio", incrocio);
        System.out.println("Server pronto");
    }
}
