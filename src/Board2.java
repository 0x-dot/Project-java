import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
 * @autore Giuseppe Giordano  
 * */

/**
 * Board
 * Abastract invariant:
 * la board è strettamente personale ogni utente ne può crearne una inserendo il prorpio nome utente e la proprio password
 * essa contiene una variabile di tipo Utente che contiene l'utente
 * una variabile di tipo Data che contiene la testa della lista Data
 * una variabile di tipo Category che contiene la Testa della lista Category
 * ed una password che deve essere uguale alla password dell'utente
 *
 * */
public class Board2<E extends Data> implements DataBoard<E> {

    private User utente;
    private Data headDato;
    private Category head;
    private String passw;
    /**
     * Costruttore della board2
     *
     * @param utente   utente valido
     * @param password password utente valido
     */

    Board2(User utente, String password) {
        this.utente = utente;
        this.passw = password;

    }
    /**
     * Controllo password corretta
     *
     * @param p la password da testare
     * @throws LoginException se la password non è uguale alla pass dell'utente
     */

    private void checkPassword(String p) throws LoginException {
        if (!this.passw.equals(p)) {
            throw new LoginException("password sbagliata!");
        }
    }
    /**
     * Controllo utente corretto
     *
     * @param u l'utente da testare di tipo User
     * @throws FormatException se l'utente non è stato creato
     */
    private void checkUser(User u) throws FormatException {
        if (!this.utente.getNome().equals(u.getNome())) {
            throw new FormatException("Utente non registrato");
        }


    }
    /**
     * Controllo categoria
     * @param data categoria da testare
     * @throws DuplicateException se la categoria è già presente
     */

    private void checkcategoria(String data) throws DuplicateException {
        Category curr = head;

        if (head == null)
            System.out.print("la lista è vuota");
        else
            while (curr != null) {
                if (curr.getCategoria().equals(data))
                    throw new DuplicateException("categoria già presente");
                curr = curr.getNext();
            }


    }

    /**
     * Crea una nuova categoria
     * Modifies Category head
     * @param category il nome della cateogria da creare
     * @param passw    la password dell'utente
     * @throws FormatException    se l'utente non è creato
     * @throws DuplicateException se la categorie è già presente
     * @throws LoginException     se la password inserita è sbagliata
     */
    @Override
    public void createCategory(String category, String passw) throws FormatException, DuplicateException, LoginException {
        this.checkUser(utente);
        this.checkPassword(passw);

        Category nuovonodo = new Category(category);


        if (head == null) {
            head = nuovonodo;

        } else {
            checkcategoria(category);
            Category last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(nuovonodo);
        }

    }
    /** inserisce un dato nella board
     * Modifies  Data headDato
     * @param category la categoria del dato
     * @param dato il dato da inserire
     * @param passw la password dell'utente
     * @throws FormatException se la password non è corretta
     * @throws DataNotFoundException se la categoria non è presente
     * @throws DuplicateException se il dato è già stato inserito in Board
     * @throws LoginException se l'utente non è corretto
     * */

    @Override
    public boolean put(String passw, E dato, String category) throws FormatException, LoginException, DuplicateException, DataNotFoundException {
        this.checkPassword(passw);
        this.checkUser(utente);
        boolean b = false;
        //Category curr = head;
        while (head != null) {
            if (head.getCategoria().equals(category))
                b = true;
            head = head.getNext();
        }
        if (!b) throw new DataNotFoundException("categoria non presente");

        Data curr = headDato;
        if (curr != null && curr.equals(dato))
            throw new DuplicateException("dato presente");


        Data nuovonodo = new Data(dato);

        if (headDato == null)

            headDato = nuovonodo;
        else {
            Data last = headDato;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(nuovonodo);

        }
        return false;



    }
    /**
     * Rimuove una nuova categoria
     * Modifies Category head
     * @param category il nome della cateogria da rimuovere
     * @param passw    la password dell'utente
     * @throws FormatException       se l'utente non è creato
     * @throws DataNotFoundException se la categoria non è stata trovata
     * @throws LoginException        se la password inserita è sbagliata
     */
    @Override
    public void removeCategory(String category, String passw) throws FormatException, DataNotFoundException, LoginException {

        this.checkUser(utente);
        this.checkPassword(passw);

        Category curr = head;
        Category prec = null;
        boolean b = false;
        //se la testa coincide con il dato cercato

        if (curr != null && curr.getCategoria().equals(category)) {
            head = curr.getNext();
            System.out.print(category + "trovato e cancellato" + "\n");
            b = true;
        }
        // se la chiave si trova dopo la testa;
        // Cerca la chiave da eliminare,
        // tiene traccia del nodo precedente
        // in quanto è necessario per modificare current.next

        while (curr != null && !curr.getCategoria().equals(category)) {
            // Se current non contiene la chiave
            // continua al nodo successivo

            prec = curr;

            curr = curr.getNext();
        }

        if (curr != null && !b) {
            // la chiave è in current
            // quindi Scolleghiamo current dalla lista
            prec.setNext(curr.getNext());
            prec = prec.getNext();
            System.out.print(category + "trovato e cancellato" + "\n");
        }

        if (curr == null) {
            throw new DataNotFoundException("categoria non trovata");

        }


    }

    /**Aggiunge un amico ad una categoria
     * Modifies Arraylist Amici in Category
     * @param category una categoria creata in precedenza
     * @param passw la password dell'utente
     * @param friend L'amico da inserire nell'arraylist presente in Category
     * @throws DataNotFoundException se la categoria non è stata trovata
     * @throws FormatException se la password non è corretta
     * @throws LoginException se la password non è corretta */
    @Override
    public void addFriend(String category, String passw, String friend) throws FormatException, DataNotFoundException, LoginException {
        this.checkPassword(passw);
        this.checkUser(utente);

        Category curr = head;
        boolean b=false;

        if (curr.getCategoria().equals(category)) {
            if (curr.getAmici().contains(friend)){throw new DataNotFoundException("amico gia presente");}

            else {curr.getAmici().add(friend);

            }
            b = true;


        }
        if(!b)throw new DataNotFoundException("categoria non presente")  ;
    }



    /**Rimuove un amico da una categoria
     * Modifies Arraylist Amici in Category
     * @param category una categoria creata in precedenza
     * @param passw la password dell'utente
     * @param friend L'amico da rimuovere dall'Arraylist presente in Category
     * @throws DataNotFoundException se l'amico non è presente o la categoria non è presente
     * @throws FormatException se la password non è corretta
     * @throws LoginException se la password non è corretta */
    @Override
    public void removeFriend ( String category, String passw, String friend ) throws FormatException, DataNotFoundException, LoginException {
        this.checkUser(utente);
        this.checkPassword(passw);
        Category curr = head;
        boolean b=false;

        if (curr.getCategoria().equals(category)) {
            if (curr.getAmici().contains(friend))
                curr.getAmici().remove(friend);
            else {
                throw new DataNotFoundException("amico non presente");
            }
            b = true;


        }
        if(!b)throw new DataNotFoundException("categoria non trovata")  ;
    }

    @Override
    public boolean SetCategoriaDato ( String passw, Data dato, String category ) throws FormatException, DuplicateException, DataNotFoundException, LoginException {
        Category curr = head;
        // Data d=new Data(dato);

        this.checkUser ( utente );
        this.checkPassword ( passw );

        if ( dato == null )
            System.out.print ( "il dato preso è nullo" );
        if ( dato.getCategoria ( ) != null )
            throw new DuplicateException ( "categoria già settata nel dato prima bisogna rimuovere" + "\n" );


        while ( curr != null ) {
            if ( dato.getCategoria ( ) == null && curr.getCategoria ( ).equals ( category ) ) {
                dato.setCategoria ( category );
                System.out.print ( dato.display ( ) + "\n" );
                //System.out.print(" categoria "+category+" settata al dato "+dato.getNomeUtente()+"\n");
            }
            curr = curr.getNext ( );
        }

        if ( dato.getCategoria ( ) == null )
            throw new DataNotFoundException ( "categoria non presente " );

        return false;
    }
    /** prende un dato precedentemente inserito nella Board e ne restituisce una copia
     * @param dato il dato da prendere
     * @param passw la password dell'utente
     * @throws FormatException se la password non è corretta
     * @throws DataNotFoundException se il dato  non è presente in board
     * @throws LoginException se l'utente non è corretto
     * */
    @Override
    public E get ( String passw, E dato ) throws FormatException, DataNotFoundException, LoginException {
        this.checkUser ( utente );
        this.checkPassword ( passw );

        Data curr = headDato;

        E CopiaDato = null;

        if ( headDato == null )
            throw new DataNotFoundException ( "dato non present" );

        else {
            while ( curr != null ) {
                if ( curr.getAnnuncio ( ).equals ( dato.getAnnuncio ( ) ) ) {
                    CopiaDato = dato;
                    break;
                }
                curr = curr.getNext ( );
            }
            if ( CopiaDato == null )
                throw new DataNotFoundException ( "il dato non è presente " );
        }

        return CopiaDato;
    }
    /**Cancella un dato precedentemente inserito nella board e ne restituisce una copia
     * Modifies Data headDato
     * @param dato il dato da cancellare
     * @param passw la password dell'utente
     * @throws FormatException se la password non è corretta
     * @throws DataNotFoundException se il dato  non è presente in board
     * @throws LoginException se l'utente non è corretto
     * */
    @Override
    public E remove ( String passw, E dato ) throws FormatException, DataNotFoundException, LoginException {
        this.checkUser ( utente );
        this.checkPassword ( passw );

        Data curr = headDato;
        Data prec = null;
        boolean b = false;
        //se la testa coincide con il dato cercato
        //i dati devono contenere annunci diversi.
        if ( curr != null && curr.getAnnuncio ( ).equals ( dato.getAnnuncio ( ) ) ) {
            headDato = curr.getNext ( );
            System.out.print ( dato + "trovato e cancellato" + "\n" );
            b = true;
        }
        // se la chiave si trova dopo la testa;
        // Cerca la chiave da eliminare,
        // tiene traccia del nodo precedente
        // in quanto è necessario per modificare current.next

        while ( curr != null && ! curr.getAnnuncio ( ).equals ( dato.getAnnuncio ( ) ) ) {
            // Se current non contiene la chiave
            // continua al nodo successivo

            prec = curr;

            curr = curr.getNext ( );
        }

        if ( curr != null && ! b ) {
            // la chiave è in current
            // quindi Scolleghiamo current dalla lista
            prec.setNext ( curr.getNext ( ) );
            prec = prec.getNext ( );
            System.out.print ( dato + "trovato e cancellato" + "\n" );
        }

        if ( curr == null ) {
            throw new DataNotFoundException ( dato + "non è stato trovato" + "\n" );

        }


        return dato;
    }
    /** Crea la lista dei dati in bacheca di una determinata categoria
     * @param category categoria da cercare
     * @param passw la password dell'utente
     * */
    @Override
    public List getDataCategory ( String passw, String category ) {
        Data curr = headDato;

        ArrayList lista = new ArrayList ( );
        while ( curr != null ) {
            if ( curr.getCategoria ( ) != null && curr.getCategoria ( ).equals ( category ) )
                lista.add ( curr );
            curr = curr.getNext ( );
        }

        return lista;
    }
    /** Aggiunge un like ad un dato
     * @param dato dato presente in board
     * @param friend l'amico che inserisce il like
     * @throws DataNotFoundException amico non presente */
    @Override
    public void insertLike ( String friend, E dato ) throws DataNotFoundException {
        Data curr = headDato;
        Category current = head;
        int index = - 1;
        boolean b = false;

        if ( current.getAmici ( ).indexOf ( friend ) != index ) {
            b = true;
        }
        if ( headDato == null ) {
            throw new DataNotFoundException ( "dato non presente" );
        } else {

            while ( curr != null ) {
                if ( ! curr.getAnnuncio ( ).equals ( dato.getAnnuncio ( ) ) && b ) {
                    dato.addLike ( );
                }
                curr = curr.getNext ( );
                b = false;
            }

        }

    }

    /** Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
     * @param passw la password dell'utente
     * @throws LoginException se la password è errata
     * @throws FormatException se l'utente non è presente*/

    @Override
 
    public Iterator getIterator ( String passw ) throws LoginException {
     
    }

    /**Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi
     * @param friend un amico con cui hai condiviso Dati della board*/
    @Override
    public Iterator getFriendIterator ( String friend ) {
 


}
