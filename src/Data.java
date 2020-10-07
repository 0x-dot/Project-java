import kotlin.text.UStringsKt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Iterator;
/**
 *Overview per Data
 * La Classe data Rappresenta il dato creato da un User in una bacheca.
 * questo dato può far parte di una categoria e avere dei like.
 * Abstract invariant:
 * Il Dato deve contenere un Utente di tipo User che rispetta le specifiche della classe User ed un annuncio
 * non ci sono restrizioni per la variabile annuncio.
 * la variabile categoria se non viene settata rimane sempre vuota
 * Se si setta la categoria bisogna rispettare le specifiche della classe Category
 * il valore di like è la quantità di like assegnati al dato ed è un intero int
 *
 *
 * Abstraction Function(utente,annunci)=<utente,annuncio>
 * il Dato deve contenere un Utente di tipo User che rispetta le specifiche della classe User ed un annuncio
 *  non ci sono restrizioni per la variabile annuncio.
 *  */



public class Data{
    private String categoria; //variabile non prende valore di categoria perchè non è stata assegnata
    private String annuncio;
    private User utente;
    private int like;
    private Data next;


/** Inizializza il tipo Data
 * @param utente Un utente valido
 * @param annuncio annuncio da inserire*/

   public Data(User utente, String annuncio){
        this.utente=utente;
        this.annuncio=annuncio;
        this.next=null;
        this.like=0;


    }

    /** Copia costruttore */

    public Data (Data E){
       this.categoria=E.getCategoria();
       this.annuncio=E.getAnnuncio();
        this.utente=E.getUtente();
        this.like=E.getLike();

  }

    /** return  ritorna il dato successivo (Board2)*/

    public Data getNext() {
        return next;
    }
    /** @param next viene settato come successivo (Board2) */
    public void setNext(Data next) {
        this.next = next;
    }
    /** @return ritorna l'utente */
    public User getUtente(){return utente;}



    /** @return ritorna il nome dell'utente  */
    public String getNomeUtente() {
        return utente.getNome();
    }
    /** @return ritorna l'annuncio  */

    public String getAnnuncio() {
        return annuncio;
    }
    /** @return ritorna la categoria del dato */
    public String getCategoria() {
       return categoria;
   }

   // public int sizeCategoria(){return categoria};

    /** @return ritorna i like */
   public int getLike() {
        return like;
    }


    /** @param  categoria viene settata come categoria */
    public void setCategoria(String categoria) {
      this.categoria = categoria;
  }
    /** @return ritorna una rappresentazione testuale del dato */
    public String display(){return this.toString();}

    /**trasforma in stringa */
    public String toString(){
        if(this.getCategoria()==null)
            return " " + this.getNomeUtente() + "::" + this.getAnnuncio();
            return " "+this.getAnnuncio()+" fa parte della categoria::"+ this.getCategoria()+" "+"  like : "+ this.getLike();

    }
    /**
     * aggiunge like al dato creato*/

    public void addLike(){
     like++;


    }

}