package Es2_825Giugno2025;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Amministratore extends Thread{
    Magazziniere magazziniere;

    public Amministratore(Magazziniere mg){
        this.magazziniere = mg;
    }

    public void run(){
        try {
            System.out.println("Aggiungo a alfa");
            magazziniere.aggiungiBene(Bene.alfa, 5);
            System.out.println("Aggiungo a beta");
            magazziniere.aggiungiBene(Bene.beta, 3);
            System.out.println("Aggiungo a gamma");
            magazziniere.aggiungiBene(Bene.gamma, 7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}