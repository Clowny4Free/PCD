package Es5_23Novembre2022;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException {

        ImplContenitore implcontenitore = new ImplContenitore(10);

        Registry registro = LocateRegistry.createRegistry(1099);
        registro.rebind("Operazione", implcontenitore);
        System.out.println("Server Pronto");
    }
}
