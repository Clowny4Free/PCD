package Es6_Giugno2002;

public class Utente extends Thread {

    IBAN ibanA, ibanB;
    SistemaBancaInterface banca;
    private String ruolo;

    public Utente(String ruolo, IBAN iban1, IBAN iban2, SistemaBancaInterface banca) {
        this.ruolo = ruolo;
        this.ibanA = iban1;
        this.ibanB = iban2;
        this.banca = banca;
    }


    public void run() {
        try {
            if (ruolo.equals("A")) {
                System.out.println("Il saldo dell'utente A è: ");
                banca.saldo(ibanA);
                banca.versamento(ibanA, 110);
                System.out.println("Il saldo dell'utente A è: ");
                banca.saldo(ibanA);
                banca.trasferimento(ibanA, ibanB, 100);
                System.out.println("Trasferimento di " + 100 + " a B eseguito");
                System.out.println("Il saldo dell'utente A è: ");
                banca.saldo(ibanA);
            }
            if (ruolo.equals("B")) {
                System.out.println("Il saldo dell'utente B è: ");
                banca.saldo(ibanB);
                System.out.println("Attendo il trasferimento da A");
                banca.attendiTrasferimento(ibanB);
                System.out.println("Prelevo 100");
                banca.prelievo(ibanB, 50);
                System.out.println("Il saldo dell'utente B è: ");
                banca.saldo(ibanB);
            }
        }catch (Exception e){
            System.err.println("Errore " + e.getMessage());
            e.printStackTrace();
        }
    }
}

