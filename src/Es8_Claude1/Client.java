package Es8_Claude1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {

        ServizioPosteggio parcheggio;
        ServizioCustode custode;
        int nA = 15;

        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            parcheggio = (ServizioPosteggio) registro.lookup("Parcheggio");
            custode = (ServizioCustode) registro.lookup("Custode");

            System.out.println("Client connesso correttamente!");

            Automobile a1 = new Automobile(1, parcheggio);
            Automobile a2 = new Automobile(2, parcheggio);
            Automobile a3 = new Automobile(3, parcheggio);
            Automobile a4 = new Automobile(4, parcheggio);
            Automobile a5 = new Automobile(5, parcheggio);
            Automobile a6 = new Automobile(6, parcheggio);
            Automobile a7 = new Automobile(7, parcheggio);
            Automobile a8 = new Automobile(8, parcheggio);
            Automobile a9 = new Automobile(9, parcheggio);
            Automobile a10 = new Automobile(10, parcheggio);
            Automobile a11 = new Automobile(11, parcheggio);
            Automobile a12 = new Automobile(12, parcheggio);
            Automobile a13 = new Automobile(13, parcheggio);
            Automobile a14 = new Automobile(14, parcheggio);
            Automobile a15 = new Automobile(15, parcheggio);


            Custode c1 = new Custode(10, custode);

            a1.start();
            a2.start();
            a3.start();
            a4.start();
            a5.start();
            a6.start();
            a7.start();
            a8.start();
            a9.start();
            a10.start();
            a11.start();
            a12.start();
            a13.start();
            a14.start();
            a15.start();

            c1.start();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
