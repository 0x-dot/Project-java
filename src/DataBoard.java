import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
/**
 * @autore Giuseppe Giordano  
 * */
 /**
  * OVERVIEW
  * Contenitore di oggetti generici che estende il tipo di dato Data.
  *
  * Ogni dato presente nella Databoard è creato da un Utente in una bacheca personale
  * questo dato può far parte di una categoria e avere dei likes.
  * Il Dato deve contenere un Utente di tipo User che rispetta le specifiche della classe User ed un annuncio
  * non ci sono restrizioni per la variabile annuncio.
  * la variabile categoria se non viene settata rimane sempre vuota
  * Se si setta la categoria bisogna rispettare le specifiche della classe Category
  * il valore di like è la quantità di like assegnati al dato ed è un intero int.
  * l'Utente proprietario della bacheca può definire le proprie categorie
  * e stilare una lista di contatti a cui saranno visibili i dati per ogni tipologia di categoria
  * i dati condivisi saranno visualizzati dagli amici ma modificati solamente
  * dall'utente proprietario della bacheca
  *
  * Typical element: < {User},{Categories<{Data},{friend}>}/>

*/

 public interface DataBoard< E extends Data > {
     /**
      * aggiunge una categoria di dati
      * <p>
      * REQUIRES : category!=NULL && category∉{categorie}
      * passw !=NULL
      * passw = password utente
      * <p>
      * THROWS:   FormatException     se category è null e/o passw è null
      * DuplicateException se la categoria è già presente
      * LoginException     se la password inserita è sbagliata
      * <p>
      * MODIFIES: this
      * EFFECTS: Crea una nuova categoria di dati
      **/
     public void createCategory( String category, String passw )
             throws FormatException, DuplicateException, LoginException;

     /**
      * Rimuove una categoria di dati
      * <p>
      * REQUIRES : category!=NULL && category ∈ {categorie}
      * passw !=NULL
      * passw = password utente
      * <p>
      * THROWS:    DataNotFoundException se la categoria non è stata trovata
      * LoginException        se la password inserita è sbagliata
      * FormatException       se category e/p passw sono null
      * <p>
      * <p>
      * MODIFIES: this
      * <p>
      * EFFECTS: Rimuove una categoria presente nell'insieme Categorie
      */
     public void removeCategory( String category, String passw )
             throws FormatException, DataNotFoundException, LoginException;

     /**
      * Aggiunge un amico ad una categoria di dati
      * <p>
      * REQUIRES : category!=NULL && category ∈ {categorie}
      * passw !=NULL
      * passw = password utente
      * friend!=NULL
      * friend non deve essere contenuto nell'insieme friend dell'utente
      * <p>
      * THROWS:   DuplicateException se l'amico è già presente
      * DataNotFoundException se la categoria non è stata trovata
      * LoginException se la password non è uguale alla pass dell'utente
      * FormatException se la password è null e/o category è null e/o friend è null
      * <p>
      * MODIFIES: this
      * <p>
      * EFFECTS: Aggiunge un amico ad una categoria di dati.
      */
     public void addFriend( String category, String passw, String friend )
             throws FormatException, LoginException, DataNotFoundException, DuplicateException;

     /**
      * Rimuove un amico da una categoria di dati
      * <p>
      * REQUIRES : category!=NULL && category ∈ {categorie}
      * passw !=NULL
      * passw = password utente
      * friend!=NULL
      * friend deve essere contenuto nell'insieme friend dell'utente
      * <p>
      * THROWS :   DataNotFoundException se l'amico non è presente e/o la categoria non è presente
      * LoginException se la password non è uguale alla pass dell'utente
      * FormatException se la password è null e/o category è null e/o friend è null
      * <p>
      * MODIFIES : this
      * <p>
      * EFFECTS : Rimuove un amico da una categoria di dati
      */
     public void removeFriend( String category, String passw, String friend )
             throws FormatException, LoginException, DataNotFoundException;

     /* Setta categoria al dato*/
     public boolean SetCategoriaDato( String passw, Data dato, String category )
             throws FormatException, DuplicateException, DataNotFoundException, LoginException;

     /**
      * Inserisce un dato in bacheca
      * <p>
      * REQUIRES:  category!=NULL && category ∈ {categorie}
      * passw !=NULL
      * passw = password utente
      * dato !=NULL
      * dato non deve essere presente nella board dell'utente
      * <p>
      * THROWS :   DataNotFoundException se la categoria non è presente
      * DuplicateException se il dato è già stato inserito in board
      * LoginException se la password non è uguale alla pass dell'utente
      * FormatException se la password è null e/o category è null e/o dato è null
      * <p>
      * MODIFIES: this
      * <p>
      * EFFECTS:  Inserisce un dato nella board dell'utente e restituisce TRUE se l'inserimento ha avuto esito positivo ,altrimenti restituisce FALSE
      */
     public boolean put( String passw, E dato, String category )
             throws FormatException, LoginException, DuplicateException, DataNotFoundException;

     /**
      * Restituisce una copia del dato in bacheca
      * <p>
      * REQUIRES:  passw !=NULL
      * passw = password utente
      * dato !=NULL
      * dato deve essere presente nella board dell'utente
      * <p>
      * THROWS:    DataNotFoundException se il dato  non è presente in board
      * LoginException se la password non è uguale alla pass dell'utente
      * FormatException se la password è null e/o category è null e/o dato è null
      * <p>
      * EFFECTS:   restituisce il dato precedentemente inserito nella board dell'utente e ne restituisce una copia
      */
     public E get( String passw, E dato )
             throws FormatException, LoginException, DataNotFoundException;


     /**
      * Rimuove e restituisce un dato dalla bacheca
      * <p>
      * REQUIRES  passw !=NULL
      * passw = password utente
      * dato !=NULL
      * dato deve essere presente nella board dell'utente
      * <p>
      * THROWS:   DataNotFoundException se il dato non è presente in board
      * LoginException se la password non è uguale alla pass dell'utente
      * FormatException se la password è null e/o category è null e/o dato è null
      * <p>
      * EFFECTS: Cancella un dato precedentemente inserito nella board dell'utente  e ne restituisce una copia
      */
     public E remove( String passw, E dato )
             throws FormatException, LoginException, DataNotFoundException;


     /**
      * Crea la lista dei dati in bacheca di una determinata categoria
      * <p>
      * REQUIRES: category!=NULL && category ∈ {categorie}
      * passw !=NULL
      * passw = password utente
      * <p>
      * THROWS:   LoginException se la password non è uguale alla pass dell'utente
      * DataNotFoundException se la categoria non è presente
      * <p>
      * EFFECTS:  crea una lista dei dati associati alla categoria category
      */
     public List < E > getDataCategory( String passw, String category )
             throws DataNotFoundException, LoginException, FormatException;


     /**
      * Aggiunge un like a un dato
      * REQUIRES: friend!=NULL
      * friend deve appartenere all'insieme {amici}
      * dato!=NULL
      * dato deve appartenere all'insieme dei dati
      * <p>
      * THROWS:  DataNotFoundException l'amico non è presente in {amici}
      * FormatException se dato è null e/o friend è null
      * <p>
      * EFFECTS: inserisce un like a un dato presente nella board di un utente
      */
     public void insertLike( String friend, E dato )
             throws DataNotFoundException, FormatException;


     /**
      * Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di likes
      * <p>
      * REQUIRES: passw !=NULL
      * passw = password utente
      * <p>
      * THROWS:   LoginException se la password non è uguale alla pass dell'utente
      * FormatException se la password è null
      * <p>
      * EFFECTS:  Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di likes
      */
     public Iterator < E > getIterator( String passw )
             throws LoginException, FormatException;


     /**
      * Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi
      * REQUIRES: friend!=NULL
      * friend deve appartenere all'insieme {amici}
      * EFFECTS:  restituisce un iteratore che genera tutti i dati in bacheca condivisi con friend
      * <p>
      * THROWS:   DataNotFoundException se il dato non è presente
      * FormatException se la password è null
      */
     public Iterator < E > getFriendIterator( String friend ) throws DataNotFoundException, FormatException;


 }
