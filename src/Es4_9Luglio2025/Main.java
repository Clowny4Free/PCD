package Es4_9Luglio2025;

import java.rmi.RemoteException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws RemoteException {
        Incrocio incrocio = new Incrocio();
        Autovettura[] autovetture = new Autovettura[8];

        Random rnd = new Random();

        for (int i = 0; i < autovetture.length; i++) {
            int num = rnd.nextInt();
            autovetture[i] = new Autovettura(i, incrocio, num % 2 == 0);
        }

        for (int i = 0; i < autovetture.length; i++) {
            autovetture[i].start();
        }
    }
}
