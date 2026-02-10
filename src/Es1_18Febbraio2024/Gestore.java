package Es1_18Febbraio2024;

public class Gestore {
    private int nSedia, nFile;
    private int nLiberi;
    private Posto[][] teatro;

    public Gestore(int ns, int nf){
        nSedia = ns;
        nFile = nf;
        nLiberi = ns * nf;
        teatro = new Posto[nf][ns];

        for(int i = 0; i < nf; i++){
            for(int j = 0; j < ns; j++){
                teatro[i][j] = new Posto((char)('A' + i), j + 1, false);
            }
        }
    }

    public synchronized boolean postiLiebri(){
        return nLiberi == 0;
    }

    public synchronized int numPostiLiberi(){
        return nLiberi;
    }

    public synchronized Posto[] qualiPostiLiberi() throws NoPostiLiberi{
        if(nLiberi == 0) throw new NoPostiLiberi();
        Posto[] tmp = new Posto[nLiberi];
        int k = 0;
        for(int i = 0; i < nFile; i++){
            for(int j = 0; j < nSedia; j++){
                if (!teatro[i][j].isOccupato()){
                    tmp[k++] = teatro[i][j];
                }
            }
        }
        return tmp;
    }

    public synchronized Posto[] prenota(Posto p) throws NoPostiLiberi{
        int fila = (int)(p.getFila() - 'A');
        int sedia = p.getSedia() - 1;
        if(!teatro[fila][sedia].isOccupato()){
            teatro[fila][sedia].setOccupato(true);
            return teatro[fila][sedia];
        }
    }
}
