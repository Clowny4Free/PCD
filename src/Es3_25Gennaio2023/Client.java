package Es3_25Gennaio2023;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registro = LocateRegistry.getRegistry("localhost", 1099);
        GestioneParcheggio parcheggio = (GestioneParcheggio) registro.lookup("Parcheggio");

        Automobilista[] automobilisti = new Automobilista[20];

        Random rnd = new Random();

        for (int i = 0; i < automobilisti.length; i++) {
            int piano = rnd.nextInt(4);
            automobilisti[i] = new Automobilista(i, parcheggio, piano);
        }

        for (int i = 0; i < automobilisti.length; i++) {
            automobilisti[i].start();
        }
    }
}
