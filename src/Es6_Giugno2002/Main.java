package Es6_Giugno2002;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Banca banca = new Banca();
        int ibanA, ibanB;
        Random rnd = new Random();

        ibanA = rnd.nextInt(10000);
        ibanB = rnd.nextInt(10000);

        IBAN iA = new IBAN(ibanA);
        IBAN iB = new IBAN(ibanB);

        banca.creaConto(iA, 0);
        banca.creaConto(iB, 0);

        Utente A = new Utente("A",iA, iB, banca);
        Utente B = new Utente("B", iB, iA, banca);

        A.start();
        B.start();
    }
}
