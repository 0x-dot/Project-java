import kotlin.sequences.USequencesKt;

import java.util.ArrayList;
import java.util.List;

/**
 * @autore Giuseppe Giordano 
 * */

public class Category< E extends Data > {
    private String categoria;
    private Category next;
    private ArrayList < String > amici;
    private ArrayList < E > dati;


    /**
     * Inizializza Categoria
     *
     * @param c Stringa Categoria
     *          inizzializza il puntatore next a null
     *          e crea un Arraylist di amici
     */
    Category ( String c ) {
        this.categoria = c;
        this.next = null;
        this.amici = new ArrayList <> ( );
        this.dati = new ArrayList <> ( );


    }

    /**
     * @return ritorna la categoria
     */
    String getCategoria () {
        return categoria;
    }

    /**
     * @return Arraylist Dati
     */
    public ArrayList < E > getDati () {
        return this.dati;
    }

    public void addAmico ( String amico ) {
        amici.add ( amico );
    }

    /**
     * @return ritorna lista di amici
     */
    public ArrayList getAmici () {
        return amici;
    }

    public String getAnnuncio ( E dato ) {
        return dato.getAnnuncio ( );
    }

    /**
     * @return ritorna elemento successivo al nodo della lista Category (Board2);
     */
    public Category getNext () {
        return next;
    }

    /**
     * @param next elemento da settare come nodo successivo della lista Category (Board2);
     */

    void setNext ( Category next ) {
        this.next = next;
    }


}
