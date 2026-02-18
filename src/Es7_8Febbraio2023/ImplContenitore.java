package Es7_8Febbraio2023;

public class ImplContenitore implements Contenitore{

    private int dim;
    private Integer[] contenitore;
    private int nLiberi;

    public ImplContenitore(int dimensione){
        this.dim = dimensione;

        contenitore = new Integer[dimensione];
        nLiberi = contenitore.length;
    }

    public synchronized void inserisci(int n) throws ContenitorePieno {
        if (nLiberi == 0){
            throw new ContenitorePieno("Il contenitore è pieno");
        }
        for (int i = 0; i < contenitore.length; i++) {
            if (contenitore[i] == null){
                contenitore[i] = n;
                nLiberi--;
                break;
            }
        }
    }

    public synchronized int somma() throws ContenitoreVuoto {
        int sum = 0;
        if (nLiberi == contenitore.length){
            throw new ContenitoreVuoto("Il contenitore è vuoto");
        }
        for (int i = 0; i < contenitore.length; i++) {
            if (contenitore[i] != null) {
                sum += contenitore[i];
            }
        }
        return sum;
    }

    public synchronized void cancella() throws ContenitoreVuoto {
        if (nLiberi == contenitore.length){
            throw new ContenitoreVuoto("Il contenitore è vuoto");
        }
        for (int i = 0; i < contenitore.length; i++) {
            if (contenitore[i] != null) {
                contenitore[i] = null;
                nLiberi++;
            }
        }
        System.out.println("Tutti gli elementi del contenitore sono stati cancellati");
    }

}
