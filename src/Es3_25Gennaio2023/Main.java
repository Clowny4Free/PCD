package Es3_25Gennaio2023;

import java.rmi.RemoteException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws RemoteException {
        Parcheggio parcheggio = new Parcheggio(3, 5);
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
