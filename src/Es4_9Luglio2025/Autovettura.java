package Es4_9Luglio2025;

import java.rmi.RemoteException;

public class Autovettura extends Thread {

    private int id;
    ImpiantoSemaforico incrocio;
    private boolean strada; //se true principale, altrimenti secondario

    public Autovettura(int ID, ImpiantoSemaforico incrocio, boolean strada) {
        this.id = ID;
        this.incrocio = incrocio;
        this.strada = strada;
    }

    public void run() {
        try {
            if (strada) {
                incrocio.arrivoPrincipale(id);
                System.out.println("Brum-brum " + id + "in arrivo sulla strada principale");
                incrocio.rilascioIncrocio(id);
                System.out.println("Brum-brum " + id + "ha attraversato principale");
            }else{
                incrocio.arrivoSecondario(id);
                System.out.println("Brum-brum " + id + "in arrivo sulla strada secondaria");
                incrocio.rilascioIncrocio(id);
                System.out.println("Brum-brum " + id + "ha attraversato secondaria");
            }

        } catch (InterruptedException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
