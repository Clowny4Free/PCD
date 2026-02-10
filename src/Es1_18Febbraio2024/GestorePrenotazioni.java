package Es1_18Febbraio2024;

public interface GestorePrenotazioni {
    boolean postiLiberi();

    int numPostiLiberi();

    Posto[] qualiPostiLiberi() throws NoPostiLiberi;

    Posto prenota(Posto p);

    Posto[] prenotaMolti(int n) throws NoPostiLiberi, TroppiPosti;

}
