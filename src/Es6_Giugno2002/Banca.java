package Es6_Giugno2002;

import java.util.HashMap;

public class Banca implements SistemaBancaInterface{

    private int saldo, qta;
    HashMap<IBAN, Integer> contoCorrente = new HashMap<>();

    public synchronized void creaConto(IBAN iban, int saldoIniziale) {
        contoCorrente.put(iban, saldoIniziale);
    }

    public synchronized int saldo(IBAN idcc) throws ContoInesistente {
        if (!contoCorrente.containsKey(idcc)){
            throw new ContoInesistente("Conto Inesistente");
        }
        return contoCorrente.get(idcc);
    }

    public synchronized void versamento(IBAN idcc, int s) throws ContoInesistente, SommaNegativa {
        int s1;
        if (!contoCorrente.containsKey(idcc)){
            throw new ContoInesistente("Conto Inesistente");
        }
        if (s <= 0){
            throw new SommaNegativa("Impossibile fare versamento negativo");
        }
        s1 = contoCorrente.get(idcc);
        contoCorrente.put(idcc, s1 + s);
    }

    public synchronized void prelievo(IBAN idcc, int s) throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente {
        int s1;
        if (!contoCorrente.containsKey(idcc)){
            throw new ContoInesistente("Conto Inesistente");
        }
        if (s <= 0) {
            throw new SommaNegativa("Impossibile fare versamento negativo");
        }
        if (contoCorrente.get(idcc) < s){
            throw new DisponibilitaInsufficiente("Disponibilità insufficiente");
        }
        s1 = contoCorrente.get(idcc);
        contoCorrente.put(idcc, s1 - s);
    }

    public synchronized void trasferimento(IBAN idccFrom, IBAN idccTo, int s) throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente {
        int sa1, sa2;
        if (!contoCorrente.containsKey(idccFrom) || !contoCorrente.containsKey(idccTo)){
            throw new ContoInesistente("Conto Inesistente");
        }
        if (s <= 0) {
            throw new SommaNegativa("Impossibile fare versamento negativo");
        }
        if (contoCorrente.get(idccFrom) < s){
            throw new DisponibilitaInsufficiente("Disponibilità insufficiente");
        }
        sa1 = contoCorrente.get(idccFrom);
        contoCorrente.put(idccFrom, sa1 - s);
        sa2 = contoCorrente.get(idccTo);
        contoCorrente.put(idccTo, sa2 + s);
    }

    public synchronized boolean attendiTrasferimento(IBAN idcc) throws ContoInesistente {
        boolean arrivato;
        int saldo1;
        int saldo2;
        if (!contoCorrente.containsKey(idcc)){
            throw new ContoInesistente("Conto Inesistente");
        }
        saldo1 = contoCorrente.get(idcc);
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         saldo2 = contoCorrente.get(idcc);
        if (saldo2 > saldo1){
            System.out.println("Trasferimento arrivato");
            return true;
        } else{
            System.out.println("Trasferimento non arrivato");
            return false;
        }
    }

}
