package Es1_18Febbraio2024;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception{
        Registry registro = LocateRegistry.getRegistry();
        Gestore gestore = (Gestore) registro.lookup("GestoreTeatro");
        System.out.println("Posti liberi: " + gestore.numPostiLiberi());
        Posto p = new Posto ('A', 1, false);
        Posto prenotato = gestore.prenota(p);
        System.out.println("Prenotato: " + prenotato.getFila() + prenotato.getSedia());
        System.out.println("Posti liberi dopo prenotazione: " + gestore.numPostiLiberi());
    }
}
