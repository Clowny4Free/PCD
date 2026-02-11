package Es1_18Febbraio2024;

import java.io.Serializable;

public class Posto implements Serializable {
    private static final long serialVersionUID = 1L;
    private char fila;
    private int sedia;
    private boolean occupato;

    public Posto (char f, int s, boolean o){
        this.fila = f;
        this.sedia = s;
        this.occupato = o;
    }

    public char getFila(){
        return fila;
    }

    public void setFila(char fila){
        this.fila = fila;
    }

    public int getSedia(){
        return sedia;
    }

    public boolean isOccupato(){
        return occupato;
    }

    public void setSedia(int sedia){
        this.sedia = sedia;
    }

    public void setOccupato(boolean occupato){
        this.occupato = occupato;
    }

    public String toString(){
        return (fila+String.valueOf(sedia));
    }
}
