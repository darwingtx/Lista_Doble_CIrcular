package proyectolistacircular;

public class Nodo {

    // Atributos
    public float Dato;
    public Nodo LigaAnt, LigaSig;

    public Nodo(float Dato) {
        this.Dato = Dato;
        LigaAnt = null;
        LigaSig = null;
    }

    public Nodo() {
        Dato = 0;
        LigaAnt = null;
        LigaSig = null;
    }

    public float getDato() {
        return Dato;
    }

    public void setDato(float dato) {
        Dato = dato;
    }

    public Nodo getLigaAnt() {
        return LigaAnt;
    }

    public void setLigaAnt(Nodo liga) {
        LigaAnt = liga;
    }

    public Nodo getLigaSig() {
        return LigaSig;
    }

    public void setLigaSig(Nodo ligaSig) {
        LigaSig = ligaSig;
    }
}