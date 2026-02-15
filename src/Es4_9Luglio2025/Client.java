package Es4_9Luglio2025;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws RemoteException {
        Registry registro = LocateRegistry.getRegistry("localhost", 1099);
        try {
            ImpiantoSemaforico incrocio = (ImpiantoSemaforico) registro.lookup("Incrocio");

            Autovettura[] autovetture = new Autovettura[8];

            Random rnd = new Random();

            for (int i = 0; i < autovetture.length; i++) {
                int num = rnd.nextInt();
                autovetture[i] = new Autovettura(i, incrocio, num % 2 == 0);
            }

            for (int i = 0; i < autovetture.length; i++) {
                autovetture[i].start();
            }

        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
