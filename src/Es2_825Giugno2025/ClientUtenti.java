package Es2_825Giugno2025;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientUtenti {
    public static void main(String[] args) {
        try {
            Acquisto magazzino = (Acquisto) Naming.lookup("rmi://localhost/Magazzino");

            Utente Mario = new Utente("Mario ", Bene.alfa, magazzino, 11);
            Utente Paolo = new Utente("Paolo ", Bene.beta, magazzino, 7);
            Utente Matteo = new Utente("Matteo ", Bene.gamma, magazzino, 5);

            Mario.start();
            Paolo.start();
            Matteo.start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
