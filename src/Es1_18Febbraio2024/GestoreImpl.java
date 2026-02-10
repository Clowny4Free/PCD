package Es1_18Febbraio2024;

public class GestoreImpl implements GestorePrenotazioni{
    private int nPosti;
    private int nFile;
    private int nLiberi;
    private Posto[][] teatro;

    public GestoreImpl(int np, int nf){
        this.nLiberi = np;
        this.nFile = nf;
        this.nLiberi = np*nf;
        this.teatro = new Posto[nf][np];
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < np; j++){
                teatro[i][j]=new Posto((char)('A'+i), j + 1, true);
            };
        }
    }

    public synchronized boolean postiLiberi(){
        return nLiberi != 0;
    }

    public synchronized int numPostiLiberi(){
        return nLiberi;
    }

    public synchronized Posto[] QualiPostiLiberi() throws NoPostiLiberi{
        if(nLiberi == 0) throw new NoPostiLiberi();
        Posto[] tmp = new Posto[nLiberi]
    }
}
