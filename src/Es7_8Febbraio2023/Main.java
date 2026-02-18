package Es7_8Febbraio2023;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        ImplContenitore contenitore = new ImplContenitore(100);
        Random rnd = new Random();

        int valore1, valore2;

        Utente u1 = new Utente(1, valore1 = rnd.nextInt(1000), valore2 = rnd.nextInt(1000), contenitore);
        Utente u2 = new Utente(2, valore1 = rnd.nextInt(1000), valore2 = rnd.nextInt(1000), contenitore);
        Utente u3 = new Utente(3, valore1 = rnd.nextInt(1000), valore2 = rnd.nextInt(1000), contenitore);

        u1.start();
        u2.start();
        u3.start();
    }
}
