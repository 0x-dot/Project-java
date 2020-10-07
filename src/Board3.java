import java.util.*;
/**
 * @autore Giuseppe Giordano 
 * */

/* Board
        * Abastract invariant:
        * la board è strettamente personale ogni utente ne può crearne una inserendo il proprio nome utente e la propria password.
        * la board contiene un Arraylist di tipo Category che contiene amici, dati e categorie create dall'utente.
        * ed una password che deve essere uguale alla password dell'utente.
        *
        * Typical element : <utente ,passw,Cat{<categoria , amici{} , dati{}>}>

        * AF ( Cat , passw )= passw , Cat{Cat.get(i).getCategoria(),Cat.get(i).getDati(),Cat.get(i).getAmici(),Cat.get(i).getAnnuncio(),
        *
        * IR : Cat!=null && passw!=null &&
        *       forall 0<= i < Cat.size()  == >  Cat.get(i) != null
        *       forall 0<= i < Cat.size()  == >  Cat.get(i).getCategoria() != null
        *       forall 0<= i < Cat.size()  == >  Cat.get(i).getDati() != null
        *       forall 0<= i < Cat.size()  == >  Cat.get(i).getAmici() != null
        *       forall 0<= i < Cat.size()  == >  Cat.get(i).getAnnuncio() != null
        *
        *  */
public class Board3<E extends Data> implements DataBoard <E> {


    private String passw;
    private ArrayList <Category <E>> Cat;

    /**
     * Costruttore della board
     *
     * @param utente   utente valido
     * @param password password utente valido
     */

    Board3( User utente, String password ) throws FormatException {
        if ( utente == null ) throw new FormatException ( "utente non valido" );
        if ( password == null ) throw new FormatException ( "password non valida" );
        this.passw = password;
        this.Cat = new ArrayList <> ( );
    }


    /**
     * Controllo password corretta
     *
     * @param p la password da testare
     * @throws LoginException  se la password non è uguale alla pass dell'utente
     * @throws FormatException se la p è null
     */
    private void checkPassword( String p ) throws LoginException, FormatException {
        if ( ! this.passw.equals ( p ) ) {
            throw new LoginException ( "password sbagliata!" );
        }
        if ( p == null ) throw new FormatException ( "password sbagliata! " );
    }

    /**
     * Controllo categoria
     *
     * @param c categoria da testare
     * @throws DuplicateException se la categoria è già presente
     */


    private void checkCategory( String c ) throws DuplicateException {

        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getCategoria ( ).contains ( c ) ) {
                throw new DuplicateException ( "categoria già presente" );
            }
        }

    }

    /**
     * Crea una nuova categoria
     *
     * @param category il nome della categoria da creare
     * @param passw    la password dell'utente
     * @throws FormatException    se category è null e/o passw è null
     * @throws DuplicateException se la categorie è già presente
     * @throws LoginException     se la password inserita è sbagliata
     */


    @Override
    public void createCategory( String category, String passw ) throws FormatException, DuplicateException, LoginException {
        this.checkPassword ( passw );
        this.checkCategory ( category );
        if ( category == null ) throw new FormatException ( "categoria non valida " );

        Category tmpC = new Category ( category );
        Cat.add ( tmpC );
        Cat.add ( new Category ( category ) );
    }

    /**
     * Rimuove una nuova categoria
     *
     * @param category il nome della categoria da rimuovere
     * @param passw    la password dell'utente
     * @throws FormatException       se category e/p passw sono null
     * @throws DataNotFoundException se la categoria non è stata trovata
     * @throws LoginException        se la password inserita è sbagliata
     */

    @Override
    public void removeCategory( String category, String passw ) throws FormatException, DataNotFoundException, LoginException {
        this.checkPassword ( passw );
        if ( category == null ) throw new FormatException ( "categoria non valida" );
        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).equals ( category ) ) {
                Cat.remove ( category );

            } else {
                throw new DataNotFoundException ( "categoria non trovata" );
            }
        }
    }

    /**
     * Aggiunge un amico ad una categoria
     *
     * @param category una categoria creata in precedenza
     * @param passw    la password dell'utente
     * @param friend   l'amico da inserire
     * @throws DuplicateException    se l'amico è già presente
     * @throws DataNotFoundException se la categoria non è stata trovata
     * @throws FormatException       se la password è null e/o category è null e/o friend è null
     * @throws LoginException        se la password non è corretta
     */

    @Override
    public void addFriend( String category, String passw, String friend ) throws FormatException, LoginException, DataNotFoundException, DuplicateException {
        this.checkPassword ( passw );
        if ( category == null ) throw new FormatException ( "categoria non valida" );
        if ( friend == null ) throw new FormatException ( "stringa friend non valida" );
        boolean b = false;

        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getCategoria ( ).equals ( category ) ) {
                if ( Cat.get ( i ).getAmici ( ).contains ( friend ) ) {
                    throw new DuplicateException ( "amico già presente" );
                } else {
                    Cat.get ( i ).addAmico ( friend );
                   // System.out.print ( Cat.get ( i ).getAmici ( ) );
                }
                b = true;
            }
        }
        if ( ! b ) throw new DataNotFoundException ( "categoria non presente" );
    }

    /**
     * Rimuove un amico da una categoria
     *
     * @param category una categoria creata in precedenza
     * @param passw    la password dell'utente
     * @param friend   l'amico da rimuovere
     * @throws DataNotFoundException se l'amico non è presente o la categoria non è presente
     * @throws FormatException       se la password è null e/o category è null e/o friend è null
     * @throws LoginException        se la password non è corretta
     */

    @Override
    public void removeFriend( String category, String passw, String friend ) throws FormatException, LoginException, DataNotFoundException {
        checkPassword ( passw );
        if ( category == null ) throw new FormatException ( "categoria non valida" );
        if ( friend == null ) throw new FormatException ( "stringa friend non valida" );
        boolean b = false;
        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getCategoria ( ).equals ( category ) ) {
                if ( Cat.get ( i ).getAmici ( ).contains ( friend ) )
                    Cat.get ( i ).getAmici ( ).remove ( friend );
                else {
                    throw new DataNotFoundException ( "amico non presente" );
                }
                b = true;
            }

        }
        if ( ! b ) throw new DataNotFoundException ( "categoria non presente" );
    }

    @Override
    public boolean SetCategoriaDato( String passw, Data dato, String category ) throws FormatException, DuplicateException, DataNotFoundException, LoginException {
        return false;
    }

    /**
     * inserisce un dato nella board
     *
     * @param category la categoria del dato
     * @param dato     il dato da inserire
     * @param passw    la password dell'utente
     * @throws FormatException       se la password è null e/o category è null e/o dato è null
     * @throws DataNotFoundException se la categoria non è presente
     * @throws DuplicateException    se il dato è già stato inserito in board
     * @throws LoginException        se la password non è corretta
     */

    @Override
    public boolean put( String passw, E dato, String category ) throws FormatException, LoginException, DuplicateException, DataNotFoundException {
        this.checkPassword ( passw );
        if ( category == null ) throw new FormatException ( "categoria non valida" );
        if ( dato == null ) throw new FormatException ( "dato npn valido" );
        boolean b = false;
        int j = 0;
        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getCategoria ( ).equals ( category ) ) {
                b = true;
            }
            if ( Cat.get ( i ).getDati ( ).contains ( dato ) ) throw new DuplicateException ( "dato presente" );
            else {
                Cat.get ( i ).getDati ( ).add ( dato );
            }
        }
        if ( ! b ) throw new DataNotFoundException ( "categoria non presente" );

        return false;
    }

    /**
     * Prende un dato precedentemente inserito nella board e ne restituisce una copia
     *
     * @param dato  il dato da prendere
     * @param passw la password dell'utente
     * @throws FormatException       se la password è null e/o category è null e/o dato è null
     * @throws DataNotFoundException se il dato  non è presente in board
     * @throws LoginException        se la password non è corretta
     */

    @Override
    public E get( String passw, E dato ) throws FormatException, LoginException, DataNotFoundException {
        this.checkPassword ( passw );
        if ( dato == null ) throw new FormatException ( "dato non valido" );
        E copiadato = null;

        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getDati ( ).contains ( dato ) ) {
                copiadato = dato;
            } else {
                throw new DataNotFoundException ( "dato non trovato" );
            }
        }
        return copiadato;
    }

    /**
     * Cancella un dato precedentemente inserito nella board e ne restituisce una copia
     *
     * @param dato  il dato da cancellare
     * @param passw la password dell'utente
     * @throws FormatException       se la password è null e/o category è null e/o dato è null
     * @throws DataNotFoundException se il dato  non è presente in board
     * @throws LoginException        se la password non è corretta
     */

    @Override
    public E remove( String passw, E dato ) throws FormatException, LoginException, DataNotFoundException {

        this.checkPassword ( passw );
        if ( dato == null ) throw new FormatException ( "dato non valido" );

        E copiadato = null;
        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getDati ( ).contains ( dato ) ) {
                copiadato = dato;
                Cat.get ( i ).getDati ( ).remove ( dato );
            } else {
                throw new DataNotFoundException ( "dato non trovato" );
            }
        }

        return copiadato;
    }

    /**
     * Crea la lista dei dati in bacheca di una determinata categoria
     *
     * @param category categoria da cercare
     * @param passw    la password dell'utente
     * @throws FormatException       se la password è null e/o category è null
     * @throws LoginException        controllo password
     * @throws DataNotFoundException se la categoria non è presente
     */

    @Override
    public List <E> getDataCategory( String passw, String category ) throws DataNotFoundException, LoginException, FormatException {
        checkPassword ( passw );
        if ( category == null ) throw new FormatException ( "categoria non valida" );
        if ( ! Cat.equals ( category ) )
            throw new DataNotFoundException ( "categoria non presente" );

        List <E> lista = null;
        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getCategoria ( ).equals ( category ) )
                lista = Cat.get ( i ).getDati ( );

        }
        return lista;
    }

    /**
     * Aggiunge un like a un dato
     *
     * @param dato   dato presente in board
     * @param friend l'amico che inserisce il like
     * @throws DataNotFoundException se amico / dato  non è stato trovato
     * @throws FormatException       se dato è null e/o friend è null
     */

    @Override
    public void insertLike( String friend, E dato ) throws DataNotFoundException, FormatException {
        if ( dato == null ) throw new FormatException ( "dato invalido" );
        if ( friend == null ) throw new FormatException ( "stringa friend non valida" );
        boolean d = false;
        boolean f = false;
        boolean x = false;

        for ( Category catt : Cat ) {

            if ( catt.getAmici ( ).contains ( friend ) )
                f = true;
            if ( catt.getAnnuncio ( dato ).contains ( dato.getAnnuncio ( ) ) )
                d = true;

            if ( catt.getAmici ( ).contains ( friend ) && catt.getAnnuncio ( dato ).contains ( dato.getAnnuncio ( ) ) && x == false ) {
                dato.addLike ( );
                x = true;
            }

        }

        if ( ! d ) throw new DataNotFoundException ( "dato non presente" );
        if ( ! f ) throw new DataNotFoundException ( "amico non presente" );


    }

    /**
     * Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
     *
     * @param passw la password dell'utente
     * @throws LoginException  se la password è errata
     * @throws FormatException se passw è null
     */

    @Override
    public Iterator <E> getIterator( String passw ) throws LoginException, FormatException {
        checkPassword ( passw );
        List <E> lista = new ArrayList <E> ( );
        for ( int i = 1 ; i < Cat.size ( ) ; i++ ) {
            lista.addAll ( Cat.get ( i ).getDati ( ) );
        }

        lista = Collections.unmodifiableList ( lista );
        return lista.iterator ( );
    }

    /**
     * Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi
     *
     * @param friend un amico con cui hai condiviso Dati della board
     * @throws DataNotFoundException se il dato non è presente
     * @throws FormatException       se friend è null
     */


    @Override
    public Iterator <E> getFriendIterator( String friend ) throws DataNotFoundException, FormatException {          
        if ( friend == null ) throw new FormatException ( "Stringa friend invalida" );
        List <E> lista = new ArrayList <E> ( );

        for ( int i = 0 ; i < Cat.size ( ) ; i++ ) {
            if ( Cat.get ( i ).getAmici ( ).contains ( friend ) )
                lista.addAll ( Cat.get ( i ).getDati ( ) );
        }
        if ( lista.isEmpty ( ) ) throw new DataNotFoundException ( "nessun dato presente" );
        lista = Collections.unmodifiableList ( lista );

        return lista.iterator ( );
    }
}
