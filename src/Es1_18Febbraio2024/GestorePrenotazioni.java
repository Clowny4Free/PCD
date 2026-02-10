package Es1_18Febbraio2024;

public interface GestorePrenotazioni {
    public boolean postiLiberi();

    public int numPostiLiberi();

    public Posto[] QualiPostiLiberi() throws NoPostiLiberi;

    public Posto prenota(Posto p);

    public Posto[] prenotaMolti(int n) throws NoPostiLiberi,TroppiPosti;
}
