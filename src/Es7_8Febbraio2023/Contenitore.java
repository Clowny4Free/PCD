package Es7_8Febbraio2023;

public interface Contenitore {

    public void inserisci(int n) throws ContenitorePieno;

    public int somma() throws ContenitoreVuoto;

    public void cancella() throws ContenitoreVuoto;
}
