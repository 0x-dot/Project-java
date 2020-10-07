/**
 * @autore Giuseppe Giordano
 * */

public class User {
    private String Nome;
    private String Password;
    /**
     * Inizializza User
     * @param nome Nome User
     * @param password password User
     * @throws FormatException lancia un'eccezione se il nome o la password non rispettano il formato
     * */

    User( String nome, String password ) throws FormatException {

        this.Nome = nome;
        checkNameUser ( nome );

        this.Password = password;
        checkPass ( password );
    }
    /** Ritorna il nome dell'utente
     * @return ritorna il nome dell'utente
     * */

    String getNome() {
        return Nome;
    }
    /** Ritorna la password dell'utente
     * @return ritorna la password dell'utente
     * */

    public String getPassword() {
        return Password;
    }


    /**trasforma in stringa */
    @Override
    public String toString() {
        return "Nome: " + Nome + "\nPassword: " + Password + "\n";
    }
    /**Controlla se il nome inserito sia valido
     * @param n La stringa da controllare
     * @throws FormatException lancia un'eccezione se il nome inserito non è corretto
     * */
    private void checkNameUser( String n ) throws FormatException {
        if ( ! (n.matches ( "^[a-z0-9]{3,15}$" )) ) //
            throw new FormatException ( " il nome utente deve essere formato da soli caratteri alfanumerici di lungezza min 3 e max 15" );

    }
    /**Controlla se il nome inserito sia valido
     * @param p La stringa da controllare
     * @throws FormatException lancia un'eccezione se la password inserita non è corretta
     * */

    private void checkPass( String p ) throws FormatException {
        if ( ! (p.matches ( "^[a-z0-9_-]{3,15}$" )) )
            throw new FormatException ( "la password deve essere formata da soli caratteri alfanumerici più  _ e – di lungezza min 3 e max 15" );

    }
}
