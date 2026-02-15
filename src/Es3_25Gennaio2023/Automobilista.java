package Es3_25Gennaio2023;

import java.rmi.RemoteException;

public class Automobilista extends Thread{

    private int id;
    private GestioneParcheggio parcheggio;
    private int pian;

    public Automobilista(int ID, GestioneParcheggio park, int piano){
        this.id = ID;
        this.parcheggio = park;
        this.pian = piano;
    }

    public void run() {
        try {
            parcheggio.ingresso();
            System.out.println("ID: " + id + ":entro nel parcheggio");
            parcheggio.parcheggia(pian);
            System.out.println("ID: " + id + ":parcheggio al piano: " + pian);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            parcheggio.partenza(pian);
            System.out.println("ID: " + id + ":me ne vado dal piano: " + pian);
            parcheggio.uscita();
            System.out.println("ID: " + id + "esco dal parcheggio");
            int postiLiberi = parcheggio.postiLiberi();
            System.out.println("Posti liberi nel parcheggio: " + postiLiberi);
            int stalliLiberi = parcheggio.stalliLiberi(pian);
            System.out.println("Stalli liberi al piano" + pian + ": " + stalliLiberi);
        }catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
